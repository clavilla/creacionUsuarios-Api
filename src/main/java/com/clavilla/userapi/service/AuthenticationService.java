package com.clavilla.userapi.service;

import com.clavilla.userapi.exception.EmailAlreadyExistsException;
import com.clavilla.userapi.mapper.UserMapper;
import com.clavilla.userapi.model.dto.UserRequestDto;
import com.clavilla.userapi.model.dto.UserResponseDto;
import com.clavilla.userapi.model.entity.User;
import com.clavilla.userapi.repository.UserRepository;
import com.clavilla.userapi.utils.JwtUtils;
import com.clavilla.userapi.utils.ServiceUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;

    public AuthenticationService(UserRepository userRepository, UserMapper userMapper, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtUtils = jwtUtils;
    }

    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        User user = userMapper.convertToEntity(userRequestDto);

        if (!ServiceUtils.isValidPassword(user.getPassword())) {
            throw new IllegalArgumentException("Password does not meet the requirements");
        }

        if (!ServiceUtils.isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is not valid");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("The email already exist in database");
        }
        user.setCreated(LocalDate.now());
        user.setModified(LocalDate.now());
        user.setLastLogin(LocalDate.now());
        user.setPassword(ServiceUtils.encodePassword(user.getPassword()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        user.setToken(jwtUtils.createToken(authentication));
        user.setActive(true);
        return userMapper.convertToDto(userRepository.save(user));
    }


}

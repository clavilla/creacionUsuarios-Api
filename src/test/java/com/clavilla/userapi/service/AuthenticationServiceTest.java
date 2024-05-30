package com.clavilla.userapi.service;

import com.clavilla.userapi.exception.EmailAlreadyExistsException;
import com.clavilla.userapi.mapper.UserMapper;
import com.clavilla.userapi.model.dto.PhoneDto;
import com.clavilla.userapi.model.dto.UserRequestDto;
import com.clavilla.userapi.model.dto.UserResponseDto;
import com.clavilla.userapi.model.entity.Phone;
import com.clavilla.userapi.model.entity.User;
import com.clavilla.userapi.repository.UserRepository;
import com.clavilla.userapi.utils.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private AuthenticationService authenticationService;

    private UserRequestDto userRequestDto;
    private PhoneDto phoneDto;
    private User user;
    private Phone phone;

    @BeforeEach
    public void setUp() {
        userRequestDto = new UserRequestDto();
        userRequestDto.setName("Juan Rodriguez");
        userRequestDto.setEmail("juanrodriguez@gmail.com");
        userRequestDto.setPassword("Hunter@2");
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setCityCode("1");
        phoneDto.setCountryCode("57");
        phoneDto.setNumber("1234567");
        userRequestDto.setPhones(List.of(phoneDto));

        user = new User();
        user.setName("Juan Rodriguez");
        user.setEmail("juanrodriguez@gmail.com");
        user.setPassword("Hunter@2");
        phone = new Phone();
        phone.setCitycode("1");
        phone.setCountrycode("57");
        phone.setNumber("1234567");
        user.setPhones(List.of(phone));
    }

    @Test
    public void createUser_ValidInput_ReturnsUserResponseDto() {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setToken("token");

        when(userMapper.convertToEntity(userRequestDto)).thenReturn(user);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtUtils.createToken(any(Authentication.class))).thenReturn("token");
        when(userMapper.convertToDto(user)).thenReturn(userResponseDto);

        UserResponseDto result = authenticationService.createUser(userRequestDto);

        assertNotNull(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void createUser_EmailAlreadyExists_ThrowsException() {
        when(userMapper.convertToEntity(userRequestDto)).thenReturn(user);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        assertThrows(EmailAlreadyExistsException.class, () -> authenticationService.createUser(userRequestDto));
    }

    @Test
    public void createUser_InvalidEmail_ThrowsException() {
        userRequestDto.setEmail("invalidEmail");
        user.setEmail("invalidEmail");
        when(userMapper.convertToEntity(userRequestDto)).thenReturn(user);

        assertThrows(IllegalArgumentException.class, () -> authenticationService.createUser(userRequestDto));
    }

    @Test
    public void createUser_InvalidPassword_ThrowsException() {
        userRequestDto.setPassword("invalidPassword");
        user.setPassword("invalidPassword");
        when(userMapper.convertToEntity(userRequestDto)).thenReturn(user);

        assertThrows(IllegalArgumentException.class, () -> authenticationService.createUser(userRequestDto));
    }
}
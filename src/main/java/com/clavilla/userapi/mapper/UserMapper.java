package com.clavilla.userapi.mapper;

import com.clavilla.userapi.model.dto.UserRequestDto;
import com.clavilla.userapi.model.dto.UserResponseDto;
import com.clavilla.userapi.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User convertToEntity(UserRequestDto userRequestDto) {
        return modelMapper.map(userRequestDto, User.class);
    }

    public UserResponseDto convertToDto(User user) {
        return modelMapper.map(user, UserResponseDto.class);
    }

    public List<UserResponseDto> convertToListDto(List<User> userList) {
        return userList.stream().map(this::convertToDto).toList();
    }
}

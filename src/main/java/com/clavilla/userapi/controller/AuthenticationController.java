package com.clavilla.userapi.controller;

import com.clavilla.userapi.model.dto.UserRequestDto;
import com.clavilla.userapi.model.dto.UserResponseDto;
import com.clavilla.userapi.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequestDto){
        return new ResponseEntity<>(authenticationService.createUser(userRequestDto), HttpStatus.CREATED);
    }
}

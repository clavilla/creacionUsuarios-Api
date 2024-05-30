package com.clavilla.userapi.controller;

import com.clavilla.userapi.model.dto.ErrorResponseDto;
import com.clavilla.userapi.model.dto.UserRequestDto;
import com.clavilla.userapi.model.dto.UserResponseDto;
import com.clavilla.userapi.service.AuthenticationService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request, please follow the API documentation for the proper structure",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequestDto){
        return new ResponseEntity<>(authenticationService.createUser(userRequestDto), HttpStatus.CREATED);
    }
}

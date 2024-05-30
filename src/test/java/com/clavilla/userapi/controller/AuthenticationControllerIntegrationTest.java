package com.clavilla.userapi.controller;

import com.clavilla.userapi.exception.EmailAlreadyExistsException;
import com.clavilla.userapi.model.dto.PhoneDto;
import com.clavilla.userapi.model.dto.UserRequestDto;
import com.clavilla.userapi.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    public void registerUserTest() throws Exception {

        mockMvc.perform(post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserRequestDto())))
                .andExpect(status().isCreated());
    }

    @Test
    public void registerUserTest_EmailAlreadyExists() throws Exception {
        UserRequestDto userRequestDto = createUserRequestDto();

        when(authenticationService.createUser(any(UserRequestDto.class)))
                .thenThrow(new EmailAlreadyExistsException("The email already exist in database"));

        mockMvc.perform(post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(status().isBadRequest());
    }


    private UserRequestDto createUserRequestDto() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("Juan Rodriguez");
        userRequestDto.setEmail("juanrodriguez@gmail.com");
        userRequestDto.setPassword("Hunter@2");

        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setNumber("1234567890");
        phoneDto.setCityCode("1");
        phoneDto.setCountryCode("57");

        userRequestDto.setPhones(List.of(phoneDto));

        return userRequestDto;
    }
}
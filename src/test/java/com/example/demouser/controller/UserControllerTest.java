package com.example.demouser.controller;

import com.example.demouser.dto.AuthenticationResponseDto;
import com.example.demouser.dto.ExceptionResponseDto;
import com.example.demouser.dto.UserResponseDto;
import com.example.demouser.helper.ObjectExample;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.demouser.helper.JsonHelper.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class UserControllerTest {

    private final MockMvc mock;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserControllerTest(MockMvc mock, ObjectMapper objectMapper){
        this.mock = mock;
        this.objectMapper = objectMapper;
    }

    @Test
    void createUserSuccess() throws Exception {
        var request = ObjectExample.createUserRequestDto();
        var result = mock
                .perform(post("/api/v1/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objecToJson(objectMapper, request)))
                .andExpect(status().isCreated())
                .andReturn();

        var response = jsonToObject(objectMapper, result.getResponse().getContentAsString(), UserResponseDto.class);
        assertNotNull(response.getId());
        assertNotNull(response.getToken());
        assertNotNull(response.isActive());
    }

    @Test
    void createUserInvalidPassword() throws Exception {
        var request = ObjectExample.createUserInvalidPassRequestDto();
        var result = mock
                .perform(post("/api/v1/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objecToJson(objectMapper, request)))
                .andExpect(status().isBadRequest())
                .andReturn();

        var responseExpected = ObjectExample.invalidPassResponseDto();
        var response = jsonToObject(objectMapper, result.getResponse().getContentAsString(), ExceptionResponseDto[].class);
        assertEquals(response[0].getError(), responseExpected.getError());
        assertEquals(response[0].getMessage(), responseExpected.getMessage());

    }

    @Test
    void createUserInvalidEmail() throws Exception {
        var request = ObjectExample.createUserInvalidEmailRequestDto();
        var result = mock
                .perform(post("/api/v1/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objecToJson(objectMapper, request)))
                .andExpect(status().isBadRequest())
                .andReturn();

        var responseExpected = ObjectExample.invalidEmailResponseDto();
        var response = jsonToObject(objectMapper, result.getResponse().getContentAsString(), ExceptionResponseDto[].class);
        assertEquals(response[0].getError(), responseExpected.getError());
        assertEquals(response[0].getMessage(), responseExpected.getMessage());
    }

    @Test
    void authenticateUserSuccess() throws Exception {
        var requestAuthenticate = ObjectExample.createAuthenticationRequestDto();
        var resultAuthenticate = mock
                .perform(post("/api/v1/user/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objecToJson(objectMapper, requestAuthenticate)))
                .andExpect(status().isOk())
                .andReturn();

        var response = jsonToObject(objectMapper, resultAuthenticate.getResponse().getContentAsString(), AuthenticationResponseDto.class);
        assertNotNull(response.getToken());
    }

    @Test
    void authenticateUserInvalid() throws Exception {
        var requestAuthenticate = ObjectExample.createAuthenticationRequestDto();
        requestAuthenticate.setPassword("Bci11111111");
        var result = mock
                .perform(post("/api/v1/user/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objecToJson(objectMapper, requestAuthenticate)))
                .andExpect(status().isNotFound())
                .andReturn();


        var response = jsonToObject(objectMapper, result.getResponse().getContentAsString(), ExceptionResponseDto.class);
        assertEquals(response.getError(), HttpStatus.NOT_FOUND.getReasonPhrase());
        assertEquals(response.getMessage(), "Bad credentials");
    }
}
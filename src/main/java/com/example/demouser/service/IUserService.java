package com.example.demouser.service;

import com.example.demouser.dto.AuthenticationRequestDto;
import com.example.demouser.dto.AuthenticationResponseDto;
import com.example.demouser.dto.UserRequestDto;
import com.example.demouser.dto.UserResponseDto;

public interface IUserService {
    UserResponseDto registerUser(UserRequestDto userRequestDto) throws Exception;

    AuthenticationResponseDto authenticateUser(AuthenticationRequestDto request);

    UserResponseDto getUserById(String id);
}

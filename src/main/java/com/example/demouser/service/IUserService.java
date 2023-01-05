package com.example.demouser.service;

import com.example.demouser.dto.UserDto;

public interface IUserService {
    UserDto createUser(UserDto userDto) throws Exception;
}

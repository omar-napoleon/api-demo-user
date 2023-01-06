package com.example.demouser.controller;

import com.example.demouser.dto.AuthenticationRequestDto;
import com.example.demouser.dto.AuthenticationResponseDto;
import com.example.demouser.dto.UserRequestDto;
import com.example.demouser.dto.UserResponseDto;
import com.example.demouser.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService iUserService;


    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(
            @Valid @RequestBody UserRequestDto userRequestDto) throws Exception {
        return new ResponseEntity<>(iUserService.registerUser(userRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @Valid @RequestBody AuthenticationRequestDto request) {
        return ResponseEntity.ok(iUserService.authenticateUser(request));
    }
}

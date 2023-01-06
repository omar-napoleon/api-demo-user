package com.example.demouser.helper;

import com.example.demouser.dto.AuthenticationRequestDto;
import com.example.demouser.dto.ExceptionResponseDto;
import com.example.demouser.dto.PhoneDto;
import com.example.demouser.dto.UserRequestDto;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ObjectExample {

    public static UserRequestDto createUserRequestDto(){
        List<PhoneDto> phones = new ArrayList<>();

        phones.add(PhoneDto.builder()
                .number("93992991")
                .cityCode("9")
                .countryCode("56")
                .build());
        return UserRequestDto.builder()
                .name("Juan Rodriguez")
                .email("juanr@odriguez.org")
                .password("Bci00001")
                .phones(phones)
                .build();
    }

    public static UserRequestDto createUserInvalidPassRequestDto() {
        var user = createUserRequestDto();
        user.setPassword("12345");
        return user;
    }

    public static UserRequestDto createUserInvalidEmailRequestDto() {
        var user = createUserRequestDto();
        user.setEmail("12345");
        return user;
    }

    public static ExceptionResponseDto invalidEmailResponseDto() {
        return ExceptionResponseDto.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Invalid email")
                .build();
    }

    public static ExceptionResponseDto invalidPassResponseDto() {
        return ExceptionResponseDto.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Password must contain at least 8 characters, a number and a uppercase letter")
                .build();
    }

    public static AuthenticationRequestDto createAuthenticationRequestDto () {
        var user = createUserRequestDto();
        return AuthenticationRequestDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}

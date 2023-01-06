package com.example.demouser.controller;

import com.example.demouser.dto.UserResponseDto;
import com.example.demouser.service.IUserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {

    private final IUserService iUserService;

    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Bearer token", required = true,
            examples = {@ExampleObject(name="Authorization", value="Bearer 1233Token")})
    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello, its works JWT");
    }

    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Bearer token", required = true,
            examples = {@ExampleObject(name="Authorization", value="Bearer 1233Token")})
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable(required = true) String id){

        return new ResponseEntity<UserResponseDto>(iUserService.getUserById(id),  HttpStatus.OK);
    }

}

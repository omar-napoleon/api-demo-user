package com.example.demouser.dto;

import com.example.demouser.validator.Password;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank
    @Email(message = "{valid.invalidEmail}")
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Password(message = "{valid.invalidPassword}")
    private String password;
}

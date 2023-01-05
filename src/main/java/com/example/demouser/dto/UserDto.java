package com.example.demouser.dto;

import com.example.demouser.validator.Password;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank(message = "{valid.invalidName}")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank
    @Email(message = "{valid.invalidEmail}")
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Password(message = "{valid.invalidPassword}")
    private String password;

    private String id;
    private Instant createdAt;
    private Instant modifiedAt;
    private Instant lastLogin;
    private String token;
    private boolean isActive;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PhoneDto> phones;
}

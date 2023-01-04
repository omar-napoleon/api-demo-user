package com.example.demouser.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull
    private String password;

    private String id;
    private Instant createdAt;
    private Instant modifiedAt;
    private Instant lastLogin;
    private String token;
    private Boolean isActive;

    private List<UserPhoneDto> phones;
}

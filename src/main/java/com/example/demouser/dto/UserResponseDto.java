package com.example.demouser.dto;

import com.example.demouser.validator.Password;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    private String id;
    private Instant createdAt;
    private Instant modifiedAt;
    private Instant lastLogin;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

    @JsonProperty("isActive")
    private boolean active;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PhoneDto> phones;
}

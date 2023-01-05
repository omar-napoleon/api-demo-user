package com.example.demouser.dto;

import com.example.demouser.validator.Password;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class UserRequestDto implements Serializable {
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PhoneDto> phones;
}

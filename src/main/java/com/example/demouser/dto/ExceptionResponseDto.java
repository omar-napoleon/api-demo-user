package com.example.demouser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponseDto implements Serializable {

    private String error;

    @JsonProperty("message")
    private String message;

}

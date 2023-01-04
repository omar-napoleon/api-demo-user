package com.example.demouser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDto implements Serializable {
    @Size(min=8)
    private String number;
    @Size(min=1)
    @JsonProperty("citycode")
    private String cityCode;
    @Size(min=1)
    @JsonProperty("countrycode")
    private String countryCode;
}

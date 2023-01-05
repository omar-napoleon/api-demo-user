package com.example.demouser.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:messages.properties")
@ConfigurationProperties(prefix = "msg")
@Data
public class MessageConfig {

    private String userExist;

}

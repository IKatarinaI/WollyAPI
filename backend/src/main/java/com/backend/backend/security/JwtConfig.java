package com.backend.backend.security;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class JwtConfig {
    private Long tokenValidity;
    private String authoritiesKey;
    private String secret;
}

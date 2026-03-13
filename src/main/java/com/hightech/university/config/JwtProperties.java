package com.hightech.university.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "spring.security.jwt")
public class JwtProperties {
    @Size(min = 32)
    private String secret;

    @Min(60_000)
    private long expirationMs;

    @NotBlank
    private String issuer;
}
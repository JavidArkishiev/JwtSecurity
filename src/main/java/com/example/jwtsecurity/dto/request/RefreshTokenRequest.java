package com.example.jwtsecurity.dto.request;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String token;
}

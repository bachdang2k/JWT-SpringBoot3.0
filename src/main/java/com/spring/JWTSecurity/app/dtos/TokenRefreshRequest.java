package com.spring.JWTSecurity.app.dtos;

import lombok.Data;

@Data
public class TokenRefreshRequest {
    private String refreshToken;
}

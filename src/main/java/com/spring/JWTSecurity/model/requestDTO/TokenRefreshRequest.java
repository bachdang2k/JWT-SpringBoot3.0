package com.spring.JWTSecurity.model.requestDTO;

import lombok.Data;

@Data
public class TokenRefreshRequest {
    private String refreshToken;
}

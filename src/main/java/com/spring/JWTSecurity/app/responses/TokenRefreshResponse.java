package com.spring.JWTSecurity.app.responses;

import com.spring.JWTSecurity.domain.entity.TokenType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenRefreshResponse {

    private String accessToken;
    private String refreshToken;
    private final TokenType tokenType = TokenType.BEARER;
}

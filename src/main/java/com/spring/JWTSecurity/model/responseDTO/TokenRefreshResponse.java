package com.spring.JWTSecurity.model.responseDTO;

import com.spring.JWTSecurity.entity.TokenType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenRefreshResponse {

    private String accessToken;
    private String refreshToken;
    private final TokenType tokenType = TokenType.BEARER;
}

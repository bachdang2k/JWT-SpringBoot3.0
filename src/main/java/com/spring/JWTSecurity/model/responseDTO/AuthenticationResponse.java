package com.spring.JWTSecurity.model.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private final String type = "Bearer";
    private String name;
    private Collection<? extends GrantedAuthority> roles;
    private String message;
}

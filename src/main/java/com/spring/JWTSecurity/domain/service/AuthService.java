package com.spring.JWTSecurity.domain.service;

import com.spring.JWTSecurity.app.dtos.LoginDTO;
import com.spring.JWTSecurity.app.dtos.RegisterDTO;
import com.spring.JWTSecurity.app.responses.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthService {
    AuthenticationResponse register(RegisterDTO registerDTO);
    AuthenticationResponse authenticate(LoginDTO loginDTO);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}

package com.spring.JWTSecurity.service;

import com.spring.JWTSecurity.entity.User;
import com.spring.JWTSecurity.model.requestDTO.LoginDTO;
import com.spring.JWTSecurity.model.requestDTO.RegisterDTO;
import com.spring.JWTSecurity.model.responseDTO.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthService {
    AuthenticationResponse register(RegisterDTO registerDTO);
    AuthenticationResponse authenticate(LoginDTO loginDTO);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}

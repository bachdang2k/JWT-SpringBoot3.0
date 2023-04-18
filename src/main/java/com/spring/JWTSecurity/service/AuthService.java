package com.spring.JWTSecurity.service;

import com.spring.JWTSecurity.model.requestDTO.LoginDTO;
import com.spring.JWTSecurity.model.requestDTO.RegisterDTO;
import com.spring.JWTSecurity.model.responseDTO.AuthenticationResponse;

public interface AuthService {
    AuthenticationResponse register(RegisterDTO registerDTO);
    AuthenticationResponse authenticate(LoginDTO loginDTO);
}

package com.spring.JWTSecurity.app.controller;

import com.spring.JWTSecurity.app.dtos.LoginDTO;
import com.spring.JWTSecurity.app.dtos.RegisterDTO;
import com.spring.JWTSecurity.app.responses.AuthenticationResponse;
import com.spring.JWTSecurity.domain.service.AuthService;
import com.spring.JWTSecurity.domain.service.UserService;
import com.spring.JWTSecurity.domain.util.ValidateObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> register(@RequestBody RegisterDTO registerDTO) {

        Map<String, String> errorValidator = ValidateObject.validateRegisterDTO(registerDTO);

        if (userService.existsByUsername(registerDTO.getUsername())) {
            errorValidator.put("username", " Username has existed");
        }
        if (userService.existsByEmail(registerDTO.getEmail())) {
            errorValidator.put("email", " email has existed");
        }
        if (!ObjectUtils.isEmpty(errorValidator)) {
            return new ResponseEntity<>(errorValidator, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(authService.register(registerDTO), HttpStatus.CREATED);

    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDTO loginDTO){
        return new ResponseEntity<>(authService.authenticate(loginDTO), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authService.refreshToken(request, response);
    }
}

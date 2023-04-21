package com.spring.JWTSecurity.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.JWTSecurity.entity.*;
import com.spring.JWTSecurity.model.requestDTO.LoginDTO;
import com.spring.JWTSecurity.model.requestDTO.RegisterDTO;
import com.spring.JWTSecurity.model.responseDTO.AuthenticationResponse;
import com.spring.JWTSecurity.repository.database.TokenRepository;
import com.spring.JWTSecurity.security.JWT.JWTService;
import com.spring.JWTSecurity.security.userDetail.UserDetailsImpl;
import com.spring.JWTSecurity.security.userDetail.UserDetailsServiceImpl;
import com.spring.JWTSecurity.service.AuthService;
import com.spring.JWTSecurity.service.RoleService;
import com.spring.JWTSecurity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RoleService roleService;
    private final UserService userService;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailService;
    private final TokenRepository tokenRepository;
    @Override
    public AuthenticationResponse register(RegisterDTO registerDTO) {

        User user = User.builder()
                .firstName(registerDTO.getFirstName())
                .lastName(registerDTO.getLastName())
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .email(registerDTO.getEmail())
                .createdDate(new Date())
                .startTime(new Date())
                .lastModifiedDate(new Date())
                .build();

        Set<String> strRoles = registerDTO.getRoles();
        Set<Role> roles = new HashSet<>();

        if (ObjectUtils.isEmpty(strRoles)) {
            Role userRole = roleService.findByRoleName(RoleName.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(
                    role -> {
                        switch (role.toLowerCase()) {
                            case "admin" -> {
                                Role adminRole = roleService.findByRoleName(RoleName.ADMIN).orElseThrow(() -> new RuntimeException("Role not found"));
                                roles.add(adminRole);
                            }
                            case "author" -> {
                                Role authorRole = roleService.findByRoleName(RoleName.AUTHOR).orElseThrow(() -> new RuntimeException("Role not found"));
                                roles.add(authorRole);
                            }
                            default -> {
                                Role userRole = roleService.findByRoleName(RoleName.USER).orElseThrow(() -> new RuntimeException("Role not found"));
                                roles.add(userRole);
                            }
                        }
                    }
            );
        }
        user.setRoles(roles);
        UserDetailsImpl userDetails = UserDetailsImpl.build(user);
        var savedUser = userService.save(user);
        var jwtToken = jwtService.generateToken(userDetails);
        var refreshToken = jwtService.generateRefreshToken(userDetails);
        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse
                .builder()
                .message("Register Successful")
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .username(userDetails.getUsername())
                .roles(new HashSet<>(roles.stream().map(role -> role.getRoleName().toString()).collect(Collectors.toList())))
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(LoginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(), //principals
                    loginDTO.getPassword()  //credentials
            )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        var jwtToken = jwtService.generateToken(userDetails);
        var refreshToken = jwtService.generateRefreshToken(userDetails);
        var user = userService.getUserByUsername(loginDTO.getUsername()).get();
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthenticationResponse
                .builder()
                .message("Login successfully")
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .username(userDetails.getUsername())
                .roles(roles)
                .build();
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }

        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (!ObjectUtils.isEmpty(username)) {
            UserDetailsImpl userDetails = (UserDetailsImpl) userDetailService.loadUserByUsername(username);
            User user = userService.getUserByUsername(username).get();

            if (jwtService.isTokenValid(refreshToken, userDetails)) {
            var accessToken = jwtService.generateToken(userDetails);

            revokeAllUserTokens(user);
            saveUserToken(user, accessToken);
            var authResponse = AuthenticationResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .username(username)
                    .build();
            new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }

    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {

        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (ObjectUtils.isEmpty(validUserTokens))
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}

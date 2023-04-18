package com.spring.JWTSecurity.service.Impl;

import com.spring.JWTSecurity.entity.Role;
import com.spring.JWTSecurity.entity.RoleName;
import com.spring.JWTSecurity.entity.User;
import com.spring.JWTSecurity.model.requestDTO.LoginDTO;
import com.spring.JWTSecurity.model.requestDTO.RegisterDTO;
import com.spring.JWTSecurity.model.responseDTO.AuthenticationResponse;
import com.spring.JWTSecurity.security.JWT.JWTService;
import com.spring.JWTSecurity.security.userDetail.UserDetailsImpl;
import com.spring.JWTSecurity.service.AuthService;
import com.spring.JWTSecurity.service.RoleService;
import com.spring.JWTSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RoleService roleService;
    private final UserService userService;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
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
                        switch (role) {
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
        userService.save(user);
        UserDetailsImpl userDetails = UserDetailsImpl.build(user);

        return AuthenticationResponse
                .builder()
                .message("Register Successful")
                .token(jwtService.generateToken(userDetails))
                .name(userDetails.getUsername())
                .roles(userDetails.getAuthorities())
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

        return AuthenticationResponse
                .builder()
                .message("Login successfully")
                .token(jwtService.generateToken(userDetails))
                .name(userDetails.getUsername())
                .roles(userDetails.getAuthorities())
                .build();
    }
}

package com.spring.JWTSecurity.domain.config;

import com.spring.JWTSecurity.domain.security.JWT.JWTAuthenticationFilter;
import com.spring.JWTSecurity.domain.security.JWT.JWTEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final JWTEntryPoint jwtEntryPoint;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf()
                    .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**")
                    .permitAll()
                .requestMatchers("api/v1/admin/**")
                    .hasAuthority("ADMIN")
                .requestMatchers("api/v1/client/**")
                    .hasAuthority("USER")
                .anyRequest()
                    .authenticated()
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(jwtEntryPoint)
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        ; // jwtFilter -> username,password Filter

        return http.build();
    }
}

package com.spring.JWTSecurity.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginDTO {
    @JsonProperty("username")
    String username;
    @JsonProperty("password")
    String password;
}

package com.spring.JWTSecurity.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/client")
@RequiredArgsConstructor
public class ClientController {

    @GetMapping("/test")
    public ResponseEntity<Object> testUserRole() {
        return new ResponseEntity<>("USER ROLE", HttpStatus.OK);
    }
}

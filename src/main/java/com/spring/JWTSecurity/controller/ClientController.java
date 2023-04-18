package com.spring.JWTSecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@PreAuthorize("hasRole({'USER','ADMIN'})")
@RequestMapping("api/v1/client")
@RequiredArgsConstructor
public class ClientController {

    @GetMapping("/test")
    public ResponseEntity<Object> testUserRole() {
        return new ResponseEntity<>("USER ROLE", HttpStatus.OK);
    }
}

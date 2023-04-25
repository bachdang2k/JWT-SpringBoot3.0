package com.spring.JWTSecurity.app.controller;

import com.spring.JWTSecurity.app.responses.ProfileResponse;
import com.spring.JWTSecurity.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Object> getUserList() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable long id) {
        return new ResponseEntity<>(userService.getProfile(id), HttpStatus.OK);
    }
}

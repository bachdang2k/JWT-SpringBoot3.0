package com.spring.JWTSecurity.domain.service;

import com.spring.JWTSecurity.domain.entity.User;
import com.spring.JWTSecurity.app.dtos.UserDTO;
import com.spring.JWTSecurity.app.responses.ProfileResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User save(User user);

    List<UserDTO> getAllUser();
    UserDTO updateUserById(Long id);
    ProfileResponse getProfile(long id);

}

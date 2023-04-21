package com.spring.JWTSecurity.service;

import com.spring.JWTSecurity.entity.User;
import com.spring.JWTSecurity.model.requestDTO.UserDTO;
import com.spring.JWTSecurity.model.responseDTO.ProfileDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User save(User user);

    List<UserDTO> getAllUser();
    UserDTO updateUserById(Long id);
    ProfileDTO getProfile(long id);

}

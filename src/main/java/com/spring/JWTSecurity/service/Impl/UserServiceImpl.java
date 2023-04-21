package com.spring.JWTSecurity.service.Impl;

import com.spring.JWTSecurity.entity.User;
import com.spring.JWTSecurity.model.mapper.Mapper;
import com.spring.JWTSecurity.model.requestDTO.UserDTO;
import com.spring.JWTSecurity.repository.database.UserRepository;
import com.spring.JWTSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> userList = userRepository.findAll();;
        return userList.stream().map(Mapper::toUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUserById(Long id) {
        return null;
    }
}

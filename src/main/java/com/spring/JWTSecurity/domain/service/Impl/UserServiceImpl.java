package com.spring.JWTSecurity.domain.service.Impl;

import com.spring.JWTSecurity.domain.entity.User;
import com.spring.JWTSecurity.domain.service.UserService;
import com.spring.JWTSecurity.app.mapper.Mapper;
import com.spring.JWTSecurity.app.dtos.UserDTO;
import com.spring.JWTSecurity.app.responses.ProfileResponse;
import com.spring.JWTSecurity.domain.repository.database.UserRepository;
import com.spring.JWTSecurity.domain.repository.redis.CacheKey;
import com.spring.JWTSecurity.domain.repository.redis.RemoteCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RemoteCache remoteCache;
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

    @Override
    public ProfileResponse getProfile(long id) {

        String key = CacheKey.genUserKey(id);

        User user = remoteCache.get(key, User.class);

        if (ObjectUtils.isEmpty(user)) {
            user = userRepository.findUserById(id).orElseThrow(() -> new RuntimeException("User not found !!!"));
            remoteCache.put(key, user);
        }

        return Mapper.toProfileDTO(user);
    }
}

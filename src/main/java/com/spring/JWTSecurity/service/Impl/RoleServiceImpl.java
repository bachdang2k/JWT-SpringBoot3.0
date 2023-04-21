package com.spring.JWTSecurity.service.Impl;

import com.spring.JWTSecurity.entity.Role;
import com.spring.JWTSecurity.entity.RoleName;
import com.spring.JWTSecurity.repository.database.RoleRepository;
import com.spring.JWTSecurity.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public Optional<Role> findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}

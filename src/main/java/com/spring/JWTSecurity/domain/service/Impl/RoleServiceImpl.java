package com.spring.JWTSecurity.domain.service.Impl;

import com.spring.JWTSecurity.domain.entity.Role;
import com.spring.JWTSecurity.domain.entity.RoleName;
import com.spring.JWTSecurity.domain.repository.database.RoleRepository;
import com.spring.JWTSecurity.domain.service.RoleService;
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

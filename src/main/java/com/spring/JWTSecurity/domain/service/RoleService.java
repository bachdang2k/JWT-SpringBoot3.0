package com.spring.JWTSecurity.domain.service;

import com.spring.JWTSecurity.domain.entity.Role;
import com.spring.JWTSecurity.domain.entity.RoleName;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findByRoleName(RoleName roleName);
}

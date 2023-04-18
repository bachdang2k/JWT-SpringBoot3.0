package com.spring.JWTSecurity.service;

import com.spring.JWTSecurity.entity.Role;
import com.spring.JWTSecurity.entity.RoleName;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findByRoleName(RoleName roleName);
}

package com.spring.JWTSecurity.repository;

import com.spring.JWTSecurity.entity.Role;
import com.spring.JWTSecurity.entity.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByRoleName(RoleName role);
}

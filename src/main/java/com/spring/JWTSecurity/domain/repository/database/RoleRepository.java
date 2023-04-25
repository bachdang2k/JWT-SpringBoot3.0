package com.spring.JWTSecurity.domain.repository.database;

import com.spring.JWTSecurity.domain.entity.Role;
import com.spring.JWTSecurity.domain.entity.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByRoleName(RoleName role);
}

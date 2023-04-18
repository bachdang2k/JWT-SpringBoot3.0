package com.spring.JWTSecurity.repository;

import com.spring.JWTSecurity.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();
    Optional<User> findByUsername(String username);

    Optional<User> findUserById(Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    //User save(User user);


}

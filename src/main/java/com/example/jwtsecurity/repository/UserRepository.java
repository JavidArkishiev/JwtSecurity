package com.example.jwtsecurity.repository;

import com.example.jwtsecurity.entity.User;
import com.example.jwtsecurity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByRole(Role role);
    boolean existsByEmail(String email);

}

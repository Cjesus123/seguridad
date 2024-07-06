package com.tutorial.authservice.repository;

import com.tutorial.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String username);
    Optional<User> findByUserNameOrEmail(String username, String email);
    Optional<User> findByTokenPassword(String token);
}

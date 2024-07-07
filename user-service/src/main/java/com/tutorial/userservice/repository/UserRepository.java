package com.tutorial.userservice.repository;

import com.tutorial.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String username);
    Optional<User> findById(int id);
    void deleteById(int id);
    Optional<User> findByEmail(String email);
}

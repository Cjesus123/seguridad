package com.tutorial.userservice.service;

import com.tutorial.userservice.DTO.NewUserDto;
import com.tutorial.userservice.entity.Role;
import com.tutorial.userservice.entity.User;
import com.tutorial.userservice.repository.RoleRepository;
import com.tutorial.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User save(NewUserDto dto) {
        Optional<User> user = userRepository.findByUserName(dto.getUserName());
        if(user.isPresent())
            return null;
        Optional<Role> role = roleRepository.findByRoleName(dto.getRole());
        String password = passwordEncoder.encode(dto.getPassword());
        User authUser = User.builder()
                .name(dto.getName())
                .lastName(dto.getLastName())
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .password(password)
                .role(role.get())
                .build();
        return userRepository.save(authUser);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
}

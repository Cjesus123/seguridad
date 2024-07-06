package com.tutorial.userservice.service;

import com.tutorial.userservice.DTO.NewUserDto;
import com.tutorial.userservice.entity.AuthUser;
import com.tutorial.userservice.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    AuthUserRepository authUserRepository;

    PasswordEncoder passwordEncoder;

    public AuthUser save(NewUserDto dto) {
        Optional<AuthUser> user = authUserRepository.findByUserName(dto.getUserName());
        if(user.isPresent())
            return null;
        String password = passwordEncoder.encode(dto.getPassword());
        AuthUser authUser = AuthUser.builder()
                .userName(dto.getUserName())
                .password(password)
                .role(dto.getRole())
                .build();
        return authUserRepository.save(authUser);
    }

    public List<AuthUser> getAll(){
        return authUserRepository.findAll();
    }
}

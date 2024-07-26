package com.tutorial.authservice.service;

import com.tutorial.authservice.dto.AuthUserDto;
import com.tutorial.authservice.dto.RequestDto;
import com.tutorial.authservice.dto.TokenDto;
import com.tutorial.authservice.entity.User;
import com.tutorial.authservice.repository.UserRepository;
import com.tutorial.authservice.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    public Optional<User> getByUsernameOrEmail(String usernameOrEmail){
        return this.userRepository.findByUserNameOrEmail(usernameOrEmail,usernameOrEmail);
    }


    public TokenDto login(AuthUserDto dto) {
        Optional<User> user = userRepository.findByUserName(dto.getUserName());
        if(user.isEmpty()) {
            return null;
        }
        if(passwordEncoder.matches(dto.getPassword(), user.get().getPassword()))
            return new TokenDto(jwtProvider.createToken(user.get()));
        return null;
    }

    public TokenDto validate(String token, RequestDto dto) {
        if(!jwtProvider.validate(token, dto))
            throw new RuntimeException("No tiene permiso para acceder a este recurso");
        String username = jwtProvider.getUserNameFromToken(token);
        if(userRepository.findByUserName(username).isEmpty())
            throw new RuntimeException("Error al obtener el usuario");
        return new TokenDto(token);
    }

    public void save(User user){
        userRepository.save(user);
    }

    public Optional<User> getByTokenPassword(String tokenPassword){
        return this.userRepository.findByTokenPassword(tokenPassword);
    }
}

package com.tutorial.userservice.service;

import com.tutorial.userservice.dto.ChangePasswordDto;
import com.tutorial.userservice.dto.NewUserDto;
import com.tutorial.userservice.dto.UpdateUserDto;
import com.tutorial.userservice.entity.Role;
import com.tutorial.userservice.entity.User;
import com.tutorial.userservice.repository.RoleRepository;
import com.tutorial.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public Optional<User> getById(int id){
        return this.userRepository.findById(id);
    }

    public void changePassword(ChangePasswordDto changePasswordDto) {
        if(changePasswordDto.getOldPassword() == null || changePasswordDto.getOldPassword().isBlank()){
            throw new RuntimeException("Contraseña Antigua no valida");
        }
        if(changePasswordDto.getNewPassword() == null || changePasswordDto.getNewPassword().isBlank()){
            throw new RuntimeException("Contraseña nueva no valida");
        }
        if (changePasswordDto.getConfirmPassword() == null || changePasswordDto.getConfirmPassword().isBlank()){
            throw new RuntimeException("Contraseña de confirmacion no valida");
        }
        if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())){
            throw new RuntimeException("Las contraseñas nuevas deben ser iguales.");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Usuario no autenticado.");
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        if (!passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("La contraseña anterior es incorrecta.");
        }
        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        userRepository.save(user);
    }

    public User UpdateUser(UpdateUserDto updateUserDto){
        if(!userRepository.existsById(updateUserDto.getId())){
            throw new RuntimeException("El usuario con id: "+updateUserDto.getId()+" no existe");
        }
        User user = this.userRepository.findById(updateUserDto.getId()).get();
        user.setName(updateUserDto.getName());
        user.setEmail(updateUserDto.getEmail());
        user.setUserName(updateUserDto.getUserName());
        user.setLastName(updateUserDto.getLastName());
        user.setRole(new Role(updateUserDto.getRole()));
        return userRepository.save(user);
    }

    public void deleteUser(int id){
        if(userRepository.existsById(id))
            this.userRepository.deleteById(id);
        throw new RuntimeException("El usuario con id: "+id+" no existe");
    }

    public User save(NewUserDto dto) {
        Optional<User> user = userRepository.findByUserName(dto.getUserName());
        if(user.isPresent() && user.get().getUserName().equals(dto.getUserName()))
            throw new RuntimeException("El nombre de usuario ya existe");
        if(user.isPresent() && user.get().getEmail().equals(dto.getEmail())){
            throw new RuntimeException("El email ya existe");
        }
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

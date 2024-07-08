package com.tutorial.userservice.service;

import com.tutorial.userservice.dto.ChangePasswordDto;
import com.tutorial.userservice.dto.NewUserDto;
import com.tutorial.userservice.dto.UpdateUserDto;
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

    public Optional<User> getById(int id){
        return this.userRepository.findById(id);
    }

    public User updateUser(UpdateUserDto updateUserDto) {
        Optional<User> existingUser = userRepository.findById(updateUserDto.getId());
        if (existingUser.isEmpty())
            throw new RuntimeException("El usuario con id: " + updateUserDto.getId() + " no existe");
        Optional<User> userWithSameUsername = userRepository.findByUserName(updateUserDto.getUserName());
        if (userWithSameUsername.isPresent() && !userWithSameUsername.get().getId().equals(updateUserDto.getId()))
            throw new RuntimeException("El nombre de usuario ya está en uso");
        Optional<User> userWithSameEmail = userRepository.findByEmail(updateUserDto.getEmail());
        if (userWithSameEmail.isPresent() && !userWithSameEmail.get().getId().equals(updateUserDto.getId()))
            throw new RuntimeException("El email ya está en uso");
        Optional<Role> role = roleRepository.findByRoleName(updateUserDto.getRole());
        if (role.isEmpty())
            throw new RuntimeException("El rol seleccionado no existe");
        User user = existingUser.get();
        user.actualizarDatos(updateUserDto.getName(),
                updateUserDto.getLastName(),
                updateUserDto.getUserName(),
                updateUserDto.getEmail(),
                role.get());
        return userRepository.save(user);
    }

    public void deleteUser(int id){
        if(!userRepository.existsById(id))
            throw new RuntimeException("El usuario con id: "+id+" no existe");
        this.userRepository.deleteById(id);
    }

    public User save(NewUserDto dto) {
        if(userRepository.findByUserName(dto.getUserName()).isPresent())
            throw new RuntimeException("El nombre de usuario ya existe");
        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new RuntimeException("El email ya existe");
        }
        Optional<Role> role = roleRepository.findByRoleName(dto.getRole());
        if (role.isEmpty()) {
            throw new RuntimeException("El rol ingresado no existe");
        }
        if(dto.getPassword() == null || dto.getPassword().isBlank())
            throw new RuntimeException("Contraseña no valida");
        String password = passwordEncoder.encode(dto.getPassword());
        User authUser = new User(null,dto.getName(),dto.getLastName(),dto.getUserName(),dto.getEmail(),password,role.get(),null);
        return userRepository.save(authUser);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void changePassword(String username,ChangePasswordDto changePasswordDto){
        Optional<User> user = userRepository.findByUserName(username);
        if(user.isEmpty())
            throw new RuntimeException("El usuario " + username + " no existe");
        if (!passwordEncoder.matches(changePasswordDto.getOldPassword(), user.get().getPassword()))
            throw new RuntimeException("La contraseña actual es incorrecta");
        if(!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword()))
            throw new RuntimeException("Las contraseñas no coinciden");
        user.get().setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        userRepository.save(user.get());
    }
}

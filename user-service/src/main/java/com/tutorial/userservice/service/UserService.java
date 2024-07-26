package com.tutorial.userservice.service;

import com.tutorial.userservice.client.TeacherClient;
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
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeacherClient teacherClient;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Optional<User> getById(int id){
        return this.userRepository.findById(id);
    }

    public User updateUser(Integer id,UpdateUserDto updateUserDto) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty())
            throw new RuntimeException("El usuario con id: " + id + " no existe");
        Optional<User> userWithSameUsername = userRepository.findByUserName(updateUserDto.getUserName());
        if (userWithSameUsername.isPresent() && !userWithSameUsername.get().getId().equals(id))
            throw new RuntimeException("El nombre de usuario ya está en uso");
        Optional<User> userWithSameEmail = userRepository.findByEmail(updateUserDto.getEmail());
        if (userWithSameEmail.isPresent() && !userWithSameEmail.get().getId().equals(id))
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
        if(dto.getRole().name().equals("ROLE_DIRECTOR_CARRERA")){
            if(!teacherClient.existsTeacherByEmail(dto.getEmail())){
                throw new RuntimeException("El docente con email" + dto.getEmail() + " no existe" );
            }
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

    public List<User> getAll(String userName){
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .filter(user -> !user.getUserName().equals(userName))
                .collect(Collectors.toList());
    }

    public void changePassword(String username,ChangePasswordDto changePasswordDto){
        Optional<User> user = userRepository.findByUserName(username);
        if(changePasswordDto.getNewPassword() == null || changePasswordDto.getNewPassword().isBlank())
            throw new RuntimeException("La contraseña nueva es invalida");
        if(changePasswordDto.getConfirmPassword() == null || changePasswordDto.getConfirmPassword().isBlank())
            throw new RuntimeException("La confirmacion de contraseña no es valida");
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

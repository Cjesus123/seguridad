package com.tutorial.userservice.controller;

import com.tutorial.userservice.dto.ChangePasswordDto;
import com.tutorial.userservice.dto.NewUserDto;
import com.tutorial.userservice.dto.UpdateUserDto;
import com.tutorial.userservice.entity.User;
import com.tutorial.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        Optional<User> user = userService.getById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else{
            return ResponseEntity.badRequest().body("usuario no encontrado");
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        try {
            userService.changePassword(changePasswordDto);
            return ResponseEntity.ok("Contraseña actualizada exitosamente.");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().body("Usuario eliminado exitosamente.");
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> create(@RequestBody NewUserDto dto){
        try {
            User user = userService.save(dto);
            return ResponseEntity.ok().body(user);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDto dto){
        try{
            User user = userService.updateUser(dto);
            return ResponseEntity.ok().body(user);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

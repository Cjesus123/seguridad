package com.tutorial.userservice.controller;

import com.tutorial.userservice.dto.ChangePasswordDto;
import com.tutorial.userservice.dto.NewUserDto;
import com.tutorial.userservice.dto.UpdateUserDto;
import com.tutorial.userservice.entity.User;
import com.tutorial.userservice.service.JWTService;
import com.tutorial.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JWTService jwtService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        Optional<User> user = userService.getById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else{
            return ResponseEntity.badRequest().body("usuario no encontrado");
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        List<User> users = userService.getAll(jwtService.getUsernameFromToken(token));
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewUserDto dto){
        try {
            User user = userService.save(dto);
            return ResponseEntity.ok().body(user);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id,@RequestBody UpdateUserDto dto){
        try{
            User user = userService.updateUser(id,dto);
            return ResponseEntity.ok().body(user);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody ChangePasswordDto changePasswordDto) {
        try{
            userService.changePassword(jwtService.getUsernameFromToken(token),changePasswordDto);
            return ResponseEntity.ok().body("Contraseña Actualizada");
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

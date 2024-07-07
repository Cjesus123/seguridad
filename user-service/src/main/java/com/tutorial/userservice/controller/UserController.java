package com.tutorial.userservice.controller;

import com.tutorial.userservice.DTO.NewUserDto;
import com.tutorial.userservice.entity.User;
import com.tutorial.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody NewUserDto dto){
        User user = userService.save(dto);
        if(user == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(user);
    }

}

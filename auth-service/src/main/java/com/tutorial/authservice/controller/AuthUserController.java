package com.tutorial.authservice.controller;

import com.tutorial.authservice.dto.AuthUserDto;
import com.tutorial.authservice.dto.RequestDto;
import com.tutorial.authservice.dto.TokenDto;
import com.tutorial.authservice.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    AuthUserService authUserService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthUserDto dto){
        TokenDto tokenDto = authUserService.login(dto);
        if(tokenDto == null)
            return ResponseEntity.badRequest().body("Usuario o Contraseña incorrectos");
        return ResponseEntity.ok().body(tokenDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestParam String token, @RequestBody RequestDto dto){
        try {
            TokenDto tokenDto = authUserService.validate(token, dto);
            if (tokenDto == null)
                return ResponseEntity.badRequest().body("Error el validar token");
            return ResponseEntity.ok(tokenDto);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

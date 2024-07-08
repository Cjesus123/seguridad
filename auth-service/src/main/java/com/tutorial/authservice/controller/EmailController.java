package com.tutorial.authservice.controller;

import com.tutorial.authservice.dto.EmailValuesDTO;
import com.tutorial.authservice.dto.RecoverPasswordDto;
import com.tutorial.authservice.service.EmailService;
import com.tutorial.authservice.entity.User;
import com.tutorial.authservice.service.AuthUserService;
import com.tutorial.authservice.utiles.Mensaje;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/email-password")
public class EmailController {
    @Autowired
    EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Autowired
    private AuthUserService userService;

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValuesDTO emailValuesDTO) {
        Optional<User> userOpt = userService.getByUsernameOrEmail(emailValuesDTO.getMailTo());
        if (!userOpt.isPresent()) {
            return new ResponseEntity<>(new Mensaje("No existe ningun usuario con esas credenciales"),HttpStatus.NOT_FOUND);
        }
        User user = userOpt.get();
        emailValuesDTO.setMailFrom(mailFrom);
        emailValuesDTO.setMailTo(user.getEmail());
        emailValuesDTO.setSubject("Cambio de Contraseña");
        emailValuesDTO.setUsername(user.getUserName());
        UUID uuid = UUID.randomUUID();
        String tokenPassword = uuid.toString();
        emailValuesDTO.setTokenPassword(tokenPassword);
        user.setTokenPassword(tokenPassword);
        userService.save(user);
        emailService.sendEmailTemplate(emailValuesDTO);
        return new ResponseEntity<>(new Mensaje("Correo enviado con exito"),HttpStatus.OK);
    }

    @PostMapping ("/recover-password")
    public ResponseEntity<?> recoverPassword (@Valid @RequestBody RecoverPasswordDto dto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje ("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        if(!dto.getPassword().equals (dto.getConfirmPassword()))
            return new ResponseEntity (new Mensaje ("Las contraseñas no coinciden"), HttpStatus.BAD_REQUEST);
        Optional<User> userOpt =  userService.getByTokenPassword(dto.getTokenPassword());
        if (!userOpt.isPresent ())
            return new ResponseEntity(new Mensaje ("No existe ningún usuario con esas credenciales"), HttpStatus.NOT_FOUND);
        User user =  userOpt.get();
        String newPassword = passwordEncoder.encode(dto.getPassword());
        user.setPassword (newPassword);
        user.setTokenPassword (null);
        userService.save(user);
        return new ResponseEntity(new Mensaje ("Contraseña actualizada"), HttpStatus.OK);
    }
}

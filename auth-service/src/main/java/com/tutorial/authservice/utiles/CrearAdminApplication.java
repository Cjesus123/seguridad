package com.tutorial.authservice.utiles;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CrearAdminApplication {

    public static void main(String[] args) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Generate encoded password for an ADMIN user
        String encodedPassword = passwordEncoder.encode("admin");

        System.out.println("Encoded admin password: " + encodedPassword);
    }
}
package com.tutorial.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String tokenPassword;
    @ManyToOne
    private Role role;

    public User(Integer id, String name, String lastName, String userName, String email, String password, Role role, String tokenPassword) {
        validarDatos(name,lastName,userName,email,role);
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.tokenPassword = tokenPassword;
        this.role = role;
    }

    private void validarDatos(String name,String lastName,String userName,String email,Role role){
        if(name == null || name.isBlank())
            throw new RuntimeException("Nombre no valido");
        if(lastName == null || lastName.isBlank())
            throw new RuntimeException("Apellido no valido");
        if(email == null || email.isBlank())
            throw new RuntimeException("Email no valido");
        if(userName == null || userName.isBlank())
            throw new RuntimeException("Nombre de usuario no valido");
        if(role == null)
            throw new RuntimeException("Role no valido");
    }

    public void actualizarDatos(String name,String lastName,String userName,String email,Role role){
        validarDatos(name,lastName,userName,email,role);
        setName(name);
        setLastName(lastName);
        setUserName(userName);
        setEmail(email);
        setRole(role);
    }
}

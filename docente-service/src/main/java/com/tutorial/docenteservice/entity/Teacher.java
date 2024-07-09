package com.tutorial.docenteservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastName;
    private String file;
    private String dni;
    private String email;
    @OneToMany
    private List<Availability> availabilities = new ArrayList<>();

    public Teacher(){}

    public Teacher(Integer id, String name, String lastName, String file, String dni, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.file = file;
        this.dni = dni;
        this.email = email;
    }
}

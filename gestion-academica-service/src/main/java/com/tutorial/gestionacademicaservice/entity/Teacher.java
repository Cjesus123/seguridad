package com.tutorial.gestionacademicaservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String legajo;
    private String dni;
    private String email;
    @OneToMany
    private List<Availability> availabilities = new ArrayList<>();
    @JsonIgnoreProperties("teachers")
    @ManyToMany(mappedBy = "teachers")
    private List<Subject> subjects;

    public Teacher(){}

    public Teacher(Integer id, String name, String lastName, String legajo, String dni, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.legajo = legajo;
        this.dni = dni;
        this.email = email;
    }
}

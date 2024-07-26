package com.tutorial.docenteservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Teacher{
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
    @ManyToMany(mappedBy = "teachers")
    private List<Subject> subjects;

    public Teacher(){}

    public Teacher(Integer id, String name, String lastName, String legajo, String dni, String email)
    {
        validateData(name,lastName,legajo,dni,email);
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.legajo = legajo;
        this.dni = dni;
        this.email = email;
    }

    private void validateData(String name, String lastName, String legajo, String dni, String email){
        if(name == null || name.isBlank())
            throw new RuntimeException("El nombre del docente no es valido");
        if(lastName == null || lastName.isBlank())
            throw new RuntimeException("El apellido del docente no es valido");
        if (legajo == null || legajo.isBlank())
            throw new RuntimeException("El legajo del docente no es valido");
        if(dni == null || dni.isBlank())
            throw new RuntimeException("El dni del docente no es valido");
        if (email == null || email.isBlank())
            throw new RuntimeException("El email del docente no es valido");
    }

    public void updateData(String name, String lastName, String legajo, String dni, String email){
        validateData(name,lastName,legajo,dni,email);
        setName(name);
        setLastName(lastName);
        setLegajo(legajo);
        setDni(dni);
        setEmail(email);
    }
}

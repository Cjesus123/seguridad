package com.tutorial.aulaservice.entity;

import com.tutorial.aulaservice.enums.ClassroomStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Classroom{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int capacity;
    private ClassroomStatus status;

    public Classroom(Integer id, String name, int capacity) {
        validator(name,capacity);
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.status = ClassroomStatus.LIBRE;
    }

    private void validator(String name, int capacity) {
        if(name == null || name.isBlank())
            throw new RuntimeException("El nombre del aula no es valido");
        if(capacity <= 0)
            throw new RuntimeException("La capacidad del aula no es valida");
    }

    public void actualizarDatos(String name, int capacity){
        validator(name,capacity);
        setName(name);
        setCapacity(capacity);
    }
}

package com.tutorial.aulaservice.entity;

import com.tutorial.aulaservice.enums.ClassroomType;
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
    private ClassroomType classroomType;

    public Classroom(Integer id, String name, int capacity, ClassroomType classroomType) {
        validator(name,capacity,classroomType);
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.classroomType = classroomType;
    }

    private void validator(String name, int capacity, ClassroomType classroomType) {
        if(name == null || name.isBlank())
            throw new RuntimeException("El nombre del aula no es valido");
        if(capacity <= 0)
            throw new RuntimeException("La capacidad del aula no es valida");
        if(classroomType == null)
            throw new RuntimeException("El tipo de aula no es valido");
    }

    public void updateData(String name, int capacity, ClassroomType classroomType){
        validator(name,capacity,classroomType);
        setName(name);
        setCapacity(capacity);
        setClassroomType(classroomType);
    }
}

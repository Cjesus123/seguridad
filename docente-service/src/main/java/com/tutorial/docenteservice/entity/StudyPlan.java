package com.tutorial.docenteservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class StudyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "career_id")
    private Career career;
    @OneToMany(mappedBy = "studyPlan", cascade = CascadeType.ALL)
    private List<Subject> subjects = new ArrayList<>();

    public StudyPlan(Integer id,String name, Career career) {
        validator(name,career);
        this.id = id;
        this.name = name;
        this.career = career;
    }

    private void validator(String name, Career career) {
        if (career == null) {
            throw new RuntimeException("Seleccione una carrera");
        }
        if(name == null || name.isEmpty()) {
            throw new RuntimeException("Nombre del carrera no puede ser nulo");
        }
    }

    public void updateData(String name,Career career){
        validator(name,career);
        setName(name);
        setCareer(career);
    }
}

package com.tutorial.docenteservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int numberRegistered;
    private int subjectYear;
    private int weeklyLoad;
    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private StudyPlan studyPlan;
    @ManyToMany
    @JoinTable(
            name = "subject_overlap",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "overlap_subject_id")
    )
    private List<Subject> overlappingMatters;
    @ManyToMany
    @JoinTable(
            name = "subject_teacher",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> teachers;

    public Subject(Integer id,String name,int numberRegistered,int subjectYear,int weeklyLoad){
        validator(name,numberRegistered,subjectYear,weeklyLoad);
        this.id = id;
        this.name = name;
        this.numberRegistered = numberRegistered;
        this.subjectYear = subjectYear;
        this.weeklyLoad = weeklyLoad;
    }

    public void updateData(String name, int numberRegistered,int subjectYear,int weeklyLoad){
        validator(name,numberRegistered,subjectYear,weeklyLoad);
        setName(name);
        setNumberRegistered(numberRegistered);
        setSubjectYear(subjectYear);
        setWeeklyLoad(weeklyLoad);
    }

    private void validator(String name,int numberRegistered,int subjectYear,int weeklyLoad){
        if(name == null || name.isBlank())
            throw new RuntimeException("El nombre no es valido");
        if(numberRegistered<0)
            throw new RuntimeException("Cantidad de alumnos inscriptos no valido!");
        if(subjectYear<1)
            throw new RuntimeException("El año de la materia no es valido");
        if(weeklyLoad<1){
            throw new RuntimeException("La cantidad de horas semanales no es valida");
        }
    }
}

package com.tutorial.docenteservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tutorial.docenteservice.enums.PeriodType;
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
    private PeriodType periodType;
    private int subjectYear;
    private int weeklyLoad;
    @JsonIgnoreProperties("subjects")
    @ManyToOne
    @JoinColumn
    private StudyPlan studyPlan;
    @ManyToMany
    @JoinTable(
            name = "subject_overlap",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "overlap_subject_id")
    )
    private List<Subject> overlappingMatters;
    @JsonIgnoreProperties("subjects")
    @ManyToMany
    @JoinTable(
            name = "subject_teacher",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> teachers;
}

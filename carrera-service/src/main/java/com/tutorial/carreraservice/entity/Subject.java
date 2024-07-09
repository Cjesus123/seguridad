package com.tutorial.carreraservice.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private int numberRegistered;
    @ManyToOne
    @JoinColumn
    private StudyPlan studyPlan;
}

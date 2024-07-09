package com.tutorial.carreraservice.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @JoinColumn
    private Career career;

    @OneToMany(mappedBy = "studyPlan", cascade = CascadeType.ALL)
    private List<Subject> subjects = new ArrayList<>();
}

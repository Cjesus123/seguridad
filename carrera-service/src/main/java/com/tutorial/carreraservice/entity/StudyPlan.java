package com.tutorial.carreraservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    @JoinColumn(name = "career_id")
    private Career career;
    @OneToMany(mappedBy = "studyPlan", cascade = CascadeType.ALL)
    private List<Subject> subjects = new ArrayList<>();
}

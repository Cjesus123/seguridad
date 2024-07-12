package com.tutorial.plandeestudioservice.entity;

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
}

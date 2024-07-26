package com.tutorial.docenteservice.entity;

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
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "director_id", referencedColumnName = "id")
    private Teacher director;
    private String name;
    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL)
    private List<StudyPlan> studyPlans = new ArrayList<>();
}

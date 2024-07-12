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
public class Career{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL)
    private List<StudyPlan> studyPlans = new ArrayList<>();

    public Career(Integer id,String name){
        validator(name);
        this.id = id;
        this.name = name;
    }

    private void validator(String name){
        if(name == null || name.isBlank()){
            throw new RuntimeException("El nombre no es valido");
        }
    }

    public void updateData(String name){
        validator(name);
        setName(name);
    }
}

package com.tutorial.plandeestudioservice.repository;

import com.tutorial.plandeestudioservice.entity.StudyPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyPlanRepository extends CrudRepository<StudyPlan,Integer>{
    List<StudyPlan> findByCareer_Id(int id);
}

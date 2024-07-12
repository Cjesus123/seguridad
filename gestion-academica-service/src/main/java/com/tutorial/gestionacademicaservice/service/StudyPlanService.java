package com.tutorial.gestionacademicaservice.service;

import com.tutorial.gestionacademicaservice.entity.StudyPlan;
import com.tutorial.gestionacademicaservice.repository.StudyPlanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class StudyPlanService {

    @Autowired
    private StudyPlanRepository studyPlanRepository;

    public List<StudyPlan> getAllStudyPlans() {
        return (List<StudyPlan>) studyPlanRepository.findAll();
    }

    public StudyPlan getStudyPlanById(Integer id) {
        return studyPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Study Plan not found with id: " + id));
    }

    public StudyPlan createStudyPlan(StudyPlan studyPlan) {
        return studyPlanRepository.save(studyPlan);
    }

    public StudyPlan updateStudyPlan(Integer id, StudyPlan studyPlan) {
        if (!studyPlanRepository.existsById(id)) {
            throw new RuntimeException("Study Plan not found with id: " + id);
        }
        studyPlan.setId(id);
        return studyPlanRepository.save(studyPlan);
    }

    public void deleteStudyPlan(Integer id) {
        if (!studyPlanRepository.existsById(id)) {
            throw new RuntimeException("Study Plan not found with id: " + id);
        }
        studyPlanRepository.deleteById(id);
    }

    public List<StudyPlan> obtenerPlanesPorCarrera(int id) {
        return studyPlanRepository.findByCareer_Id(id);
    }
}

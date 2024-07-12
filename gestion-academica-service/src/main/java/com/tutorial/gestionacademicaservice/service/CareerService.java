package com.tutorial.gestionacademicaservice.service;

import com.tutorial.gestionacademicaservice.entity.Career;
import com.tutorial.gestionacademicaservice.entity.StudyPlan;
import com.tutorial.gestionacademicaservice.repository.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CareerService {
    @Autowired
    private CareerRepository careerRepository;

    @Transactional(readOnly = true)
    public List<Career> getAllCareers() {
        return (List<Career>) careerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Career getCareerById(Integer id) {
        return careerRepository.findById(id).orElseThrow(() -> new RuntimeException("Career not found with id: " + id));
    }

    @Transactional
    public Career createCareer(Career career) {
        return careerRepository.save(career);
    }

    @Transactional
    public Career updateCareer(Integer id, Career updatedCareer) {
        Career career = getCareerById(id);
        career.setName(updatedCareer.getName());
        career.setStudyPlans(updatedCareer.getStudyPlans());
        return careerRepository.save(career);
    }

    @Transactional
    public void deleteCareer(Integer id) {
        careerRepository.deleteById(id);
    }

    @Transactional
    public Career addStudyPlansToCareer(Integer careerId, List<StudyPlan> studyPlans) {
        Career career = getCareerById(careerId);
        for (StudyPlan studyPlan : studyPlans) {
            studyPlan.setCareer(career); // Establecer la relación con Career
            career.getStudyPlans().add(studyPlan);
        }
        return careerRepository.save(career);
    }

    @Transactional
    public Career removeStudyPlanFromCareer(Integer careerId, Integer studyPlanId) {
        Career career = getCareerById(careerId);
        career.getStudyPlans().removeIf(plan -> plan.getId().equals(studyPlanId));
        return careerRepository.save(career);
    }
}

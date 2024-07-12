package com.tutorial.gestionacademicaservice.controller;
import com.tutorial.gestionacademicaservice.entity.Career;
import com.tutorial.gestionacademicaservice.entity.StudyPlan;
import com.tutorial.gestionacademicaservice.service.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("carreras")
public class CareerController {
    @Autowired
    private CareerService careerService;

    @GetMapping
    public List<Career> getAllCareers() {
        return careerService.getAllCareers();
    }

    @GetMapping("/{id}")
    public Career getCareerById(@PathVariable Integer id) {
        return careerService.getCareerById(id);
    }

    @PostMapping
    public Career createCareer(@RequestBody Career career) {
        return careerService.createCareer(career);
    }

    @PutMapping("/{id}")
    public Career updateCareer(@PathVariable Integer id, @RequestBody Career career) {
        return careerService.updateCareer(id, career);
    }

    @DeleteMapping("/{id}")
    public void deleteCareer(@PathVariable Integer id) {
        careerService.deleteCareer(id);
    }

    @PostMapping("/{careerId}/plan")
    public Career addStudyPlansToCareer(@PathVariable Integer careerId, @RequestBody List<StudyPlan> studyPlans) {
        return careerService.addStudyPlansToCareer(careerId, studyPlans);
    }

    @DeleteMapping("/{careerId}/plan/{studyPlanId}")
    public Career removeStudyPlanFromCareer(@PathVariable Integer careerId, @PathVariable Integer studyPlanId) {
        return careerService.removeStudyPlanFromCareer(careerId, studyPlanId);
    }
}

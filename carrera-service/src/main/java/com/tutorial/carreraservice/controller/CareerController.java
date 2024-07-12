package com.tutorial.carreraservice.controller;

import com.tutorial.carreraservice.clientes.PlanEstudioClient;
import com.tutorial.carreraservice.entity.Career;
import com.tutorial.carreraservice.entity.StudyPlan;
import com.tutorial.carreraservice.service.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrera")
public class CareerController {
    @Autowired
    private CareerService careerService;
    @Autowired
    private PlanEstudioClient planEstudioClient;

    @GetMapping("/{id}/planes")
    public List<StudyPlan> getPlanesEstudio(@PathVariable Integer id) {
        return planEstudioClient.getPlanesEstudioPorCarrera(id);
    }

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

    @PostMapping("/{careerId}/studyPlans")
    public Career addStudyPlansToCareer(@PathVariable Integer careerId, @RequestBody List<StudyPlan> studyPlans) {
        return careerService.addStudyPlansToCareer(careerId, studyPlans);
    }

    @DeleteMapping("/{careerId}/studyPlans/{studyPlanId}")
    public Career removeStudyPlanFromCareer(@PathVariable Integer careerId, @PathVariable Integer studyPlanId) {
        return careerService.removeStudyPlanFromCareer(careerId, studyPlanId);
    }
}

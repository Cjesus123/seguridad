package com.tutorial.carreraservice.clientes;

import com.tutorial.carreraservice.entity.StudyPlan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "plandeestudio-service")
public interface PlanEstudioClient {
    @GetMapping("/plandeestudio/carrera/{id}")
    List<StudyPlan> getPlanesEstudioPorCarrera(@PathVariable int id);
}
package com.tutorial.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "docente-service")
public interface TeacherClient {
    @GetMapping("/docentes/exists/{email}")
    boolean existsTeacherByEmail(@PathVariable("email") String email);
}
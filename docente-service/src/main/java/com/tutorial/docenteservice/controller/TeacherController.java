package com.tutorial.docenteservice.controller;

import com.tutorial.docenteservice.DTO.TeacherDTO;
import com.tutorial.docenteservice.entity.Availability;
import com.tutorial.docenteservice.entity.Teacher;
import com.tutorial.docenteservice.service.TeacherService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("docentes")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<?> getAllTeachers() {
        try{
            return ResponseEntity.ok(teacherService.getAll());
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{id}/disponibilidades")
    public List<Availability> getAvailabilityFromTeacher(@PathVariable Integer id){
        return teacherService.getAvailabilitiesFromTeacher(id);
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<?> getTeacherById(@PathVariable Integer teacherId) {
        try {
            return ResponseEntity.ok(teacherService.getById(teacherId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("exists/{email}")
    public boolean existsTeacherByEmail(@PathVariable String email) {
        return teacherService.existsTeacherByEmail(email);
    }

    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody TeacherDTO teacher) {
        try {
            teacherService.save(teacher);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{teacherId}")
    public Teacher updateTeacher(@PathVariable Integer teacherId, @RequestBody Teacher updatedTeacher) {
        return teacherService.update(teacherId, updatedTeacher);
    }

    @DeleteMapping("/{teacherId}")
    public void deleteTeacher(@PathVariable Integer teacherId) {
        teacherService.delete(teacherId);
    }

    @PostMapping("/{teacherId}/disponibilidades")
    public void addAvailabilitiesToTeacher(@PathVariable Integer teacherId, @RequestBody List<Availability> availabilities) {
        teacherService.addAvailabilitiesToTeacher(teacherId, availabilities);
    }
}

package com.tutorial.docenteservice.controller;

import com.tutorial.docenteservice.DTO.TeacherDTO;
import com.tutorial.docenteservice.entity.Availability;
import com.tutorial.docenteservice.entity.Teacher;
import com.tutorial.docenteservice.service.TeacherService;
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
            return ResponseEntity.ok(teacherService.getAllTeachers());
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
            return ResponseEntity.ok(teacherService.getTeacherById(teacherId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody TeacherDTO teacher) {
        try {
            return ResponseEntity.ok(teacherService.createTeacher(teacher));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{teacherId}")
    public Teacher updateTeacher(@PathVariable Integer teacherId, @RequestBody Teacher updatedTeacher) {
        return teacherService.updateTeacher(teacherId, updatedTeacher);
    }

    @DeleteMapping("/{teacherId}")
    public void deleteTeacher(@PathVariable Integer teacherId) {
        teacherService.deleteTeacher(teacherId);
    }

    @PostMapping("/{teacherId}/disponibilidades")
    public void addAvailabilitiesToTeacher(@PathVariable Integer teacherId, @RequestBody List<Availability> availabilities) {
        teacherService.addAvailabilitiesToTeacher(teacherId, availabilities);
    }

    @DeleteMapping("/{teacherId}/disponibilidades")
    public void removeAvailabilitiesFromTeacher(@PathVariable Integer teacherId, @RequestBody List<Long> availabilityIds) {
        teacherService.removeAvailabilitiesFromTeacher(teacherId, availabilityIds);
    }
}

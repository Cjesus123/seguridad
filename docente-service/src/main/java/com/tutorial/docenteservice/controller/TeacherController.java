package com.tutorial.docenteservice.controller;

import com.tutorial.docenteservice.entity.Availability;
import com.tutorial.docenteservice.entity.Teacher;
import com.tutorial.docenteservice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docente")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("{id}/disponibilidad")
    public List<Availability> getAvailabilityFromTeacher(@PathVariable Integer id){
        return teacherService.getAvailabilitiesFromTeacher(id);
    }

    @GetMapping("/{teacherId}")
    public Teacher getTeacherById(@PathVariable Integer teacherId) {
        return teacherService.getTeacherById(teacherId);
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    @PutMapping("/{teacherId}")
    public Teacher updateTeacher(@PathVariable Integer teacherId, @RequestBody Teacher updatedTeacher) {
        return teacherService.updateTeacher(teacherId, updatedTeacher);
    }

    @DeleteMapping("/{teacherId}")
    public void deleteTeacher(@PathVariable Integer teacherId) {
        teacherService.deleteTeacher(teacherId);
    }

    @PostMapping("/{teacherId}/availabilities")
    public void addAvailabilitiesToTeacher(@PathVariable Integer teacherId, @RequestBody List<Availability> availabilities) {
        teacherService.addAvailabilitiesToTeacher(teacherId, availabilities);
    }

    @DeleteMapping("/{teacherId}/availabilities")
    public void removeAvailabilitiesFromTeacher(@PathVariable Integer teacherId, @RequestBody List<Long> availabilityIds) {
        teacherService.removeAvailabilitiesFromTeacher(teacherId, availabilityIds);
    }
}

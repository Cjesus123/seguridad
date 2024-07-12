package com.tutorial.plandeestudioservice.controller;

import com.tutorial.plandeestudioservice.entity.Subject;
import com.tutorial.plandeestudioservice.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable Integer id) {
        return subjectService.getSubjectById(id);
    }

    @PostMapping
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectService.createSubject(subject);
    }

    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable Integer id, @RequestBody Subject subject) {
        return subjectService.updateSubject(id, subject);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Integer id) {
        subjectService.deleteSubject(id);
    }
}

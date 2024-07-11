package com.tutorial.calendarioacademicoservice.controller;

import com.tutorial.calendarioacademicoservice.entity.AcademicCalendar;
import com.tutorial.calendarioacademicoservice.service.AcademicCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/academic-calendars")
public class AcademicCalendarController {

    @Autowired
    private AcademicCalendarService academicCalendarService;

    @GetMapping
    public List<AcademicCalendar> getAllAcademicCalendars() {
        return academicCalendarService.getAllAcademicCalendars();
    }

    @GetMapping("/{id}")
    public AcademicCalendar getAcademicCalendarById(@PathVariable Integer id) {
        return academicCalendarService.getAcademicCalendarById(id);
    }

    @PostMapping
    public AcademicCalendar createAcademicCalendar(@RequestBody AcademicCalendar academicCalendar) {
        return academicCalendarService.createAcademicCalendar(academicCalendar);
    }

    @PutMapping("/{id}")
    public AcademicCalendar updateAcademicCalendar(@PathVariable Integer id, @RequestBody AcademicCalendar academicCalendar) {
        return academicCalendarService.updateAcademicCalendar(id, academicCalendar);
    }

    @DeleteMapping("/{id}")
    public void deleteAcademicCalendar(@PathVariable Integer id) {
        academicCalendarService.deleteAcademicCalendar(id);
    }
}

package com.tutorial.calendarioacademicoservice.service;

import com.tutorial.calendarioacademicoservice.entity.AcademicCalendar;
import com.tutorial.calendarioacademicoservice.repository.AcademicCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AcademicCalendarService {

    @Autowired
    private AcademicCalendarRepository academicCalendarRepository;

    public List<AcademicCalendar> getAllAcademicCalendars() {
        return (List<AcademicCalendar>) academicCalendarRepository.findAll();
    }

    public AcademicCalendar getAcademicCalendarById(Integer id) {
        return academicCalendarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic Calendar not found with id: " + id));
    }

    public AcademicCalendar createAcademicCalendar(AcademicCalendar academicCalendar) {
        return academicCalendarRepository.save(academicCalendar);
    }

    public AcademicCalendar updateAcademicCalendar(Integer id, AcademicCalendar academicCalendar) {
        if (!academicCalendarRepository.existsById(id)) {
            throw new RuntimeException("Academic Calendar not found with id: " + id);
        }
        academicCalendar.setId(id);
        return academicCalendarRepository.save(academicCalendar);
    }

    public void deleteAcademicCalendar(Integer id) {
        if (!academicCalendarRepository.existsById(id)) {
            throw new RuntimeException("Academic Calendar not found with id: " + id);
        }
        academicCalendarRepository.deleteById(id);
    }
}

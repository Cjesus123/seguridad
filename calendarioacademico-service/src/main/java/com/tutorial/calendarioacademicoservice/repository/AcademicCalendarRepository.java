package com.tutorial.calendarioacademicoservice.repository;

import com.tutorial.calendarioacademicoservice.entity.AcademicCalendar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicCalendarRepository extends CrudRepository<AcademicCalendar,Integer> {
}

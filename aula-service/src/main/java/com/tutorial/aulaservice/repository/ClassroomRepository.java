package com.tutorial.aulaservice.repository;

import com.tutorial.aulaservice.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
    Optional<Classroom> findById(int classroomId);
    Optional<Classroom> findByName(String name);
}

package com.tutorial.docenteservice.service;

import com.tutorial.docenteservice.DTO.TeacherDTO;
import com.tutorial.docenteservice.entity.Availability;
import com.tutorial.docenteservice.entity.Teacher;
import com.tutorial.docenteservice.repository.AvailabilityRepository;
import com.tutorial.docenteservice.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Transactional(readOnly = true)
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Teacher> getById(Integer id) {
        return teacherRepository.findById(id);
    }

    @Transactional
    public void save(TeacherDTO teacherDTO){
        if(teacherRepository.existsByLegajo(teacherDTO.legajo()))
            throw new RuntimeException("El docente con el legajo " + teacherDTO.legajo() + " ya existe");
        if(teacherRepository.existsByDni(teacherDTO.dni()))
            throw new RuntimeException("El docente con el dni " + teacherDTO.dni() + " ya existe");
        Teacher teacher = new Teacher(null,teacherDTO.name(), teacherDTO.lastName(), teacherDTO.legajo(), teacherDTO.dni(), teacherDTO.email());
        teacherRepository.save(teacher);
    }

    @Transactional
    public Teacher update(Integer id, Teacher updatedTeacher) {
        Optional<Teacher> teacher = getById(id);
        if (teacher.isEmpty()) {
            throw new RuntimeException("No existe el docente con id: " + id);
        }
        teacher.get().setName(updatedTeacher.getName());
        teacher.get().setLastName(updatedTeacher.getLastName());
        teacher.get().setLegajo(updatedTeacher.getLegajo());
        teacher.get().setDni(updatedTeacher.getDni());
        teacher.get().setEmail(updatedTeacher.getEmail());
        return teacherRepository.save(teacher.get());
    }

    @Transactional
    public void delete(Integer id) {
        Optional<Teacher> teacher = getById(id);
        if (teacher.isEmpty()) {
            throw new RuntimeException("No existe el docente con id: " + id);
        }
        teacherRepository.delete(teacher.get());
    }

    public void addAvailabilitiesToTeacher(Integer teacherId, List<Availability> availabilities) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("No existe el docente con id: " + teacherId));

        availabilities.forEach(availability -> {
            availabilityRepository.save(availability);
            teacher.getAvailabilities().add(availability);
        });

        teacherRepository.save(teacher);
    }

    public List<Availability> getAvailabilitiesFromTeacher(Integer id){
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isEmpty())
            throw new RuntimeException("El docente no existe");
        return teacher.get().getAvailabilities();
    }

    public boolean existsTeacherByEmail(String email) {
        return teacherRepository.existsByEmail(email);
    }
}

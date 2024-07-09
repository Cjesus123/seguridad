package com.tutorial.docenteservice.service;

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
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Transactional(readOnly = true)
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Teacher getTeacherById(Integer id) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        return teacherOptional.orElse(null);
    }

    @Transactional
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Transactional
    public Teacher updateTeacher(Integer id, Teacher updatedTeacher) {
        Teacher teacher = getTeacherById(id);
        if (teacher == null) {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
        teacher.setName(updatedTeacher.getName());
        teacher.setLastName(updatedTeacher.getLastName());
        teacher.setFile(updatedTeacher.getFile());
        teacher.setDni(updatedTeacher.getDni());
        teacher.setEmail(updatedTeacher.getEmail());
        return teacherRepository.save(teacher);
    }

    @Transactional
    public void deleteTeacher(Integer id) {
        Teacher teacher = getTeacherById(id);
        if (teacher == null) {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
        teacherRepository.delete(teacher);
    }

    public void addAvailabilitiesToTeacher(Integer teacherId, List<Availability> availabilities) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        availabilities.forEach(availability -> {
            availabilityRepository.save(availability);
            teacher.getAvailabilities().add(availability);
        });

        teacherRepository.save(teacher); // Guardar para actualizar la lista de disponibilidades en Teacher
    }

    public List<Availability> getAvailabilitiesFromTeacher(Integer id){
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isEmpty())
            throw new RuntimeException("El docente no existe");
        return teacher.get().getAvailabilities();
    }

    @Transactional
    public void removeAvailabilitiesFromTeacher(Integer teacherId, List<Long> availabilityIds) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        availabilityIds.forEach(availabilityId -> {
            Availability availabilityToRemove = teacher.getAvailabilities().stream()
                    .filter(av -> av.getId().equals(availabilityId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Availability not found with id: " + availabilityId));

            teacher.getAvailabilities().remove(availabilityToRemove);
            availabilityRepository.delete(availabilityToRemove);
        });

        teacherRepository.save(teacher); // Guardar para actualizar la lista de disponibilidades en Teacher
    }
}

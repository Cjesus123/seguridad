package com.tutorial.aulaservice.service;

import com.tutorial.aulaservice.DTO.ClassroomDTO;
import com.tutorial.aulaservice.entity.Classroom;
import com.tutorial.aulaservice.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ClassRoomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Transactional(readOnly = true)
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Classroom> findById(int id) {
        return classroomRepository.findById(id);
    }

    @Transactional
    public void save(ClassroomDTO classroomDTO){
        if(classroomRepository.findByName(classroomDTO.name()).isPresent())
            throw new RuntimeException("Ya existe el aula con el nombre " + classroomDTO.name());
        Classroom classroom = new Classroom(null, classroomDTO.name(),
                classroomDTO.capacity(),classroomDTO.classroomType());
        classroomRepository.save(classroom);
    }

    @Transactional
    public void update(int id, ClassroomDTO classroomDTO){
        Optional<Classroom> classroomOptional = classroomRepository.findByName(classroomDTO.name());
        if(classroomOptional.isPresent() && classroomOptional.get().getId() != id){
            throw new RuntimeException("Ya existe el aula con el nombre " + classroomDTO.name());
        }
        Optional<Classroom> classroom = classroomRepository.findById(id);
        if(classroom.isEmpty()){
            throw new RuntimeException("El aula solicitada no existe");
        }
        classroom.get().updateData(classroomDTO.name(), classroomDTO.capacity(),classroomDTO.classroomType());
        classroomRepository.save(classroom.get());
    }

    @Transactional
    public void delete(int id){
        Optional<Classroom> classroom = classroomRepository.findById(id);
        if(classroom.isEmpty()){
            throw new RuntimeException("El aula solicitada no existe");
        }
        classroomRepository.deleteById(id);
    }
}
package com.tutorial.aulaservice.service;

import com.tutorial.aulaservice.DTO.ClassroomDTO;
import com.tutorial.aulaservice.entity.Classroom;
import com.tutorial.aulaservice.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ClassRoomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    public Optional<Classroom> findById(int id) {
        return classroomRepository.findById(id);
    }

    public Classroom save(ClassroomDTO classroomDTO){
        Classroom classroom = new Classroom(null, classroomDTO.name(),
                classroomDTO.capacity());
        return classroomRepository.save(classroom);
    }

    public Classroom update(int id, ClassroomDTO classroomDTO){
        Optional<Classroom> classroom = classroomRepository.findById(id);
        if(classroom.isEmpty()){
            throw new RuntimeException("El aula solicitada no existe");
        }
        classroom.get().actualizarDatos(classroomDTO.name(), classroomDTO.capacity());
        return classroomRepository.save(classroom.get());
    }

    public void delete(int id){
        Optional<Classroom> classroom = classroomRepository.findById(id);
        if(classroom.isEmpty()){
            throw new RuntimeException("El aula solicitada no existe");
        }
        classroomRepository.deleteById(id);
    }
}
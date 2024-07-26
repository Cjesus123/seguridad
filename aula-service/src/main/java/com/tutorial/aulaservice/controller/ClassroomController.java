package com.tutorial.aulaservice.controller;

import com.tutorial.aulaservice.DTO.ClassroomDTO;
import com.tutorial.aulaservice.entity.Classroom;
import com.tutorial.aulaservice.enums.ClassroomType;
import com.tutorial.aulaservice.service.ClassRoomService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("aulas")
public class ClassroomController {

    @Autowired
    ClassRoomService classRoomService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor, seleccione un archivo CSV.");
        }
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(reader);

            for (CSVRecord record : records) {
                ClassroomDTO classroomDTO = new ClassroomDTO(record.get("name"),Integer.parseInt(record.get("capacity")),ClassroomType.valueOf(record.get("classroomType")));
                classRoomService.save(classroomDTO);
            }
            return ResponseEntity.status(HttpStatus.OK).body("Archivo subido y procesado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar el archivo.");
        }
    }

    @GetMapping
    public ResponseEntity<List<Classroom>> getAll() {
        return ResponseEntity.ok(classRoomService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        Optional<Classroom> classroom = classRoomService.findById(id);
        if(classroom.isEmpty())
            return ResponseEntity.badRequest().body("El aula solicitada no existe");
        return ResponseEntity.ok(classroom.get());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ClassroomDTO classroomDTO){
        try {
            classRoomService.save(classroomDTO);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id,@RequestBody ClassroomDTO classroomDTO){
        try {
            classRoomService.update(id,classroomDTO);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            classRoomService.delete(id);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

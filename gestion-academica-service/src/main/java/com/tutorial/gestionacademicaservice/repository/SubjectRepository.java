package com.tutorial.gestionacademicaservice.repository;

import com.tutorial.gestionacademicaservice.entity.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<Subject,Integer>{

}

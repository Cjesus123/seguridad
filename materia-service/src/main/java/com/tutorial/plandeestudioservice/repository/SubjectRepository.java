package com.tutorial.plandeestudioservice.repository;

import com.tutorial.plandeestudioservice.entity.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<Subject,Integer>{

}

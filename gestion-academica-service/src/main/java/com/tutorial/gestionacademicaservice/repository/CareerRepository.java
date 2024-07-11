package com.tutorial.gestionacademicaservice.repository;

import com.tutorial.gestionacademicaservice.entity.Career;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends CrudRepository<Career,Integer>{

}

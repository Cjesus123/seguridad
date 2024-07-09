package com.tutorial.carreraservice.repository;

import com.tutorial.carreraservice.entity.Career;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends CrudRepository<Career,Integer>{

}

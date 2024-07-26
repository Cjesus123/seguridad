package com.tutorial.docenteservice.repository;

import com.tutorial.docenteservice.entity.Availability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends CrudRepository<Availability,Integer>{
}

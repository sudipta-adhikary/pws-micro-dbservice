package com.cts.micro.dbservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cts.micro.dbservice.model.Trainee;

public interface TraineeRepository extends CrudRepository<Trainee, Integer> {

	public List<Trainee> findByName(String name);

}

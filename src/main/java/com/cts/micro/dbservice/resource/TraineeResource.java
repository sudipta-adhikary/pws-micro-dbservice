package com.cts.micro.dbservice.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.micro.dbservice.model.Trainee;
import com.cts.micro.dbservice.repository.TraineeRepository;

@RestController
@RequestMapping("/rest/db-service")
@EnableJpaRepositories(basePackages = "com.cts.micro.dbservice.repository")
public class TraineeResource {

	@Autowired
	private TraineeRepository repository;

	@GetMapping("/{name}/{email}")
	public String add(@PathVariable final String name, @PathVariable final String email) {

		System.out.println("Received add request - [name=" + name + ", email=" + email + "]");

		Trainee trainee = new Trainee(name, email);
		repository.save(trainee);

		return "Tried saving [name=" + name + ", email=" + email + "]";
	}

	@GetMapping("/{name}")
	public Iterable<Trainee> findTrainee(@PathVariable final String name) {
		return repository.findByName(name);
	}

	@GetMapping("/delete/{name}")
	public List<String> delete(@PathVariable final String name) {

		List<String> messages = new ArrayList<String>();
		repository.findByName(name).stream().forEach(trainee -> {
			repository.delete(trainee);
			messages.add("Record deleted for trainee - " + trainee.getName());
		});

		return messages;
	}

	@GetMapping("/fetchall")
	public Iterable<Trainee> findAllTrainee() {
		return repository.findAll();
	}

}

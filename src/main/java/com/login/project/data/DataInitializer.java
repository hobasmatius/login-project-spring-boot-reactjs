package com.login.project.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.login.project.model.User;
import com.login.project.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	private final UserRepository userRepository;
	
	@Autowired
	public DataInitializer(UserRepository repository) {
		this.userRepository = repository;
	}
	
	// let's preloaded the database with some data upon running
	@Override
	public void run(String... args) throws Exception {
		this.userRepository.save(new User("user1", "thisishobas1234", "user1@gmail.com"));
		this.userRepository.save(new User("user2", "user2ishere94", "user2@gmail.com"));
	}
}

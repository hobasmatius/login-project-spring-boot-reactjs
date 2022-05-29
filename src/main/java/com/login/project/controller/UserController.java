package com.login.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.project.model.HttpResponse;
import com.login.project.model.LoginDTO;
import com.login.project.model.User;
import com.login.project.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	private List<User> users;
	
	@PostMapping("/login")
	public ResponseEntity<HttpResponse> login(
			@RequestBody LoginDTO payload 
	) {
		if (users == null)
			users = userRepository.findAll();
		
		User result = users.stream().filter(
				x -> x.getEmail().equals(payload.getUsernameOrEmail()) || x.getUsername().equals(payload.getUsernameOrEmail())
				&& x.getPassword().equals(payload.getPassword())).findFirst().orElse(null);
		
		if (result == null) {
			return ResponseEntity.ok(new HttpResponse("401", "Invalid username/email or password"));
		} else {
			return ResponseEntity.ok(new HttpResponse("200", "OK"));
		}
	}
			
}

package com.login.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.project.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
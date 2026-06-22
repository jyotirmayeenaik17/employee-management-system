package com.prolifics.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prolifics.employee.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	//custom method
	Optional<User> findByUsername(String username);
}

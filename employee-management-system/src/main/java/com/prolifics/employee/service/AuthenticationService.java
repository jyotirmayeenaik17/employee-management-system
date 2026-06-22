package com.prolifics.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prolifics.employee.dto.RegisterRequest;
import com.prolifics.employee.entity.User;
import com.prolifics.employee.repository.UserRepository;

@Service
public class AuthenticationService {
	@Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public String register(RegisterRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(
                encoder.encode(
                        request.getPassword()));

        user.setRole(request.getRole());

        repository.save(user);

        return "User Registered Successfully";
    }
}

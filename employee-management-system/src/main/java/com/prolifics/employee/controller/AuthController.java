package com.prolifics.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prolifics.employee.dto.AuthResponse;
import com.prolifics.employee.dto.LoginRequest;
import com.prolifics.employee.dto.RegisterRequest;
import com.prolifics.employee.entity.User;
import com.prolifics.employee.repository.UserRepository;
import com.prolifics.employee.security.JwtService;
import com.prolifics.employee.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	 @Autowired
	 private AuthService authService;
	 
	 @Autowired
	 private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){

        return ResponseEntity.ok(
        		authService.register(request));
    }
    
    

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request){

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()));

        if(authentication.isAuthenticated()) {

        	User user = userRepository
        	        .findByUsername(request.getUsername())
        	        .orElseThrow(() ->
        	                new UsernameNotFoundException("User not found"));

        	String token = jwtService.generateToken(user.getUsername());

            return ResponseEntity.ok(
                    new AuthResponse(token));
        }

        throw new UsernameNotFoundException(
                "Invalid Credentials");
    }

}

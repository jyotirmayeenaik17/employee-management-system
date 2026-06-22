package com.prolifics.employee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Bean : Spring Boot creates one object of BCryptPasswordEncoder and stores it in the Spring Container.
 * 
 * Spring Security uses the AuthenticationManager bean to validate:
		Username exists
		Password is correct
		User is authorized
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
    private JwtAuthenticationFilter jwtFilter;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public AuthenticationManager authenticationManager(
	        AuthenticationConfiguration config)
	        throws Exception {

	    return config.getAuthenticationManager();
	}
	
	 @Bean
	    SecurityFilterChain securityFilterChain(
	            HttpSecurity http)
	            throws Exception {

	        return http
	                .csrf(csrf -> csrf.disable())

	                .authorizeHttpRequests(auth ->
	                        auth
	                                .requestMatchers(
	                                		"/auth/**")
	                                .permitAll()

	                                .requestMatchers(
	                                        "/employee/add")
	                                .hasAuthority("ROLE_ADMIN")
	                                .requestMatchers(
	                                        "/employee/all")
	                                .hasAnyAuthority(
	                                        "ROLE_ADMIN",
	                                        "ROLE_USER")

	                                .anyRequest()
	                                .authenticated())

	                .sessionManagement(session ->
	                        session.sessionCreationPolicy(
	                                SessionCreationPolicy.STATELESS))

	                .addFilterBefore(
	                        jwtFilter,
	                        UsernamePasswordAuthenticationFilter.class)

	                .build();
	 }
}

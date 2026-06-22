package com.prolifics.employee.dto;

import lombok.Data;

@Data
public class EmployeeDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String department;
    private Double salary;
}

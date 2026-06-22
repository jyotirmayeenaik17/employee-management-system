package com.prolifics.employee.dto;

import com.prolifics.employee.entity.Role;

import lombok.Data;

@Data
public class RegisterRequest {

	private String username;
    private String password;
    private Role role;

}

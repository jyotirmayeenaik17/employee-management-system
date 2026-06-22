package com.prolifics.employee.service;

import java.util.List;

import com.prolifics.employee.dto.EmployeePageResponse;
import com.prolifics.employee.entity.Employee;

public interface EmployeeService {
	
	public Employee saveEmployee(Employee employee);
	
	public List<Employee> getAllEmployees();
	
	public Employee updateEmployee(Long id, Employee employee);
	
	public EmployeePageResponse  getAllEmployee(
            int pageNo,
            int pageSize,
            String sortBy,
            String sortDir);

}

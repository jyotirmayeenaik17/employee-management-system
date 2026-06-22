package com.prolifics.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prolifics.employee.dto.EmployeePageResponse;
import com.prolifics.employee.entity.Employee;
import com.prolifics.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	//create employee
	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
		
	}
	
	//get All employee
	/**@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}*/
	
	//update employee by id
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		Employee updatedEmployee = employeeService.updateEmployee(id, employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	//get All Employees using pagination
	@GetMapping("/getAllEmployees")
	public EmployeePageResponse  getAllEmployee(
			@RequestParam(defaultValue = "0")
			int pageNo,
			
			@RequestParam(defaultValue = "5")
			int pageSize,
			
			@RequestParam(defaultValue = "id")
			String sortBy,
			
			@RequestParam(defaultValue = "asc")
			String sortDir){
		
		return employeeService.getAllEmployee(pageNo, pageSize, sortBy, sortDir);
		
	}
	
	@GetMapping("/all")
    public String getAllEmployees() {

        return "All Employees";
    }

    @PostMapping("/add")
    public String addEmployee() {

        return "Employee Added";
    }
	
}

package com.prolifics.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prolifics.employee.dto.EmployeePageResponse;
import com.prolifics.employee.entity.Employee;
import com.prolifics.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee updateEmployee(Long id, Employee employee) {
		
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("Employee not found with id : "+ id));
	
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setDepartment(employee.getDepartment());
		existingEmployee.setEmail(employee.getEmail());
		existingEmployee.setSalary(employee.getSalary());
		
		return employeeRepository.save(existingEmployee);
	}

	@Override
	public EmployeePageResponse  getAllEmployee(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();

        Pageable pageable =
                PageRequest.of(pageNo, pageSize, sort);

        Page<Employee> page =  employeeRepository.findAll(pageable);
        
        EmployeePageResponse response =
                new EmployeePageResponse();

        response.setContent(page.getContent());
        response.setPageNo(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLast(page.isLast());

        return  response;
	}

}

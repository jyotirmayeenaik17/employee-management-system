package com.prolifics.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prolifics.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}

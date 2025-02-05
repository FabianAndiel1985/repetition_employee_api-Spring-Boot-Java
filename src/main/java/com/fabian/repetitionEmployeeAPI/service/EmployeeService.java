package com.fabian.repetitionEmployeeAPI.service;

import java.util.List;

import com.fabian.repetitionEmployeeAPI.entity.Employee;

public interface EmployeeService {

	public Employee saveEmployee(Employee empl);
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(int id);
	public String deleteEmployeeById(int id);
}

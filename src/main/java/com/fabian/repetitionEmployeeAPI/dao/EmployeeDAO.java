package com.fabian.repetitionEmployeeAPI.dao;

import java.util.List;

import com.fabian.repetitionEmployeeAPI.component.Employee;

public interface EmployeeDAO {
	
	public Employee saveEmployee(Employee employee);
	public Employee getEmployeeById(int id);
	public List<Employee> getAllEmployees();
	public String deleteEmployeeById(int id);

}

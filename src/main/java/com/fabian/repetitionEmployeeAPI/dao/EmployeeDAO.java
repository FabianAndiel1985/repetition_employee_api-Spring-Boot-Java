package com.fabian.repetitionEmployeeAPI.dao;

import java.util.List;

import com.fabian.repetitionEmployeeAPI.component.Employee;

public interface EmployeeDAO {
	
	public void saveEmployee(Employee employee) throws Exception;
	public Employee getEmployeeById(int id) throws Exception;
	public List<Employee> getAllEmployees()throws Exception;
	public void deleteEmployeeById(int id) throws Exception;

}

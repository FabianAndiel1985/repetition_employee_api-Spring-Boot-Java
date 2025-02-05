package com.fabian.repetitionEmployeeAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabian.repetitionEmployeeAPI.dao.DatabaseException;
import com.fabian.repetitionEmployeeAPI.dao.EmployeeDAO;
import com.fabian.repetitionEmployeeAPI.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDAO employeeDao;

	@Autowired
	public EmployeeServiceImpl(EmployeeDAO employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public Employee saveEmployee(Employee empl) {
		return employeeDao.saveEmployee(empl);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeDao.getAllEmployees();
	}

	@Override
	public Employee getEmployeeById(int id) {
		return employeeDao.getEmployeeById(id);
	}

	@Override
	public String deleteEmployeeById(int id) {
		return employeeDao.deleteEmployeeById(id);
	}
	
	
	
	

}

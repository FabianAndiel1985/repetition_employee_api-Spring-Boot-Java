package com.fabian.repetitionEmployeeAPI.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabian.repetitionEmployeeAPI.component.Employee;
import com.fabian.repetitionEmployeeAPI.dao.DatabaseException;
import com.fabian.repetitionEmployeeAPI.dao.EmployeeDAO;
import com.fabian.repetitionEmployeeAPI.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	private EmployeeDAO employeeDao;
	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		List<Employee> employees = null;
		employees = employeeService.getAllEmployees();
		return employees;
	}

	
	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable("id") int id ) {
		Employee employee = null;
		employee = employeeService.getEmployeeById(id);
		return employee;
	}
	
	
	@PostMapping("/employees")
		public String postEmployee(@RequestBody Employee employee) {
			employee.setId(0);
			employeeService.saveEmployee(employee);
			return employee.toString();
		}
	
	
	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable("id") int id )  {
		String message = "";
		message = employeeService.deleteEmployeeById(id);
		return message;
	}
	
	
	
	@PutMapping("/employees")
	public String updateEmployee(@RequestBody Employee employee) {
		//wenn id null ist oder 0 nicht weiterlassen
			employeeService.saveEmployee(employee);
		return "Employee updated";
	}

}

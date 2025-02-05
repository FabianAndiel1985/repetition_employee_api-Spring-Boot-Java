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

import com.fabian.repetitionEmployeeAPI.dao.DatabaseException;
import com.fabian.repetitionEmployeeAPI.dao.EmployeeDAO;
import com.fabian.repetitionEmployeeAPI.entity.Employee;
import com.fabian.repetitionEmployeeAPI.service.EmployeeService;
import com.fabian.repetitionEmployeeAPI.service.UpdateIdMissingException;

import jakarta.validation.Valid;

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
		public String postEmployee(@Valid @RequestBody Employee employee) {
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
	public String updateEmployee(@Valid @RequestBody  Employee employee) {
		if(employee.getId() == 0) {
			System.out.println("GOES HERE ");
			throw new UpdateIdMissingException("The id of the employee to be updated is needed");
		}
		employeeService.saveEmployee(employee);
		return "Employee updated";
	
	}

}

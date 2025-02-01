package com.fabian.repetitionEmployeeAPI.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabian.repetitionEmployeeAPI.component.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		
		List<Employee> employees = new ArrayList<>();
		Employee e1 = new Employee(1,"Fabian","Andiel","fabian.andiel@yahoo.com");
		Employee e2 = new Employee(2,"Fabian","Andiel","fabian.andiel@yahoo.com");
		Employee e3 = new Employee(3,"Fabian","Andiel","fabian.andiel@yahoo.com");
		
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
		
		return employees;
	}
	
	@GetMapping("/emplopyees/{id}")
	public String getEmployeeById() {
		return "This is the first message";
	}
	
	@PostMapping("/emplopyee")
		public String postEmployee() {
			return "This is the first message";
		}
	
	@PutMapping("/emplopyees/{id}")
	public String updateEmployee() {
		return "This is the first message";
	}

	@DeleteMapping("/emplopyees/{id}")
	public String deleteEmployee() {
		return "This is the first message";
	}
	
}

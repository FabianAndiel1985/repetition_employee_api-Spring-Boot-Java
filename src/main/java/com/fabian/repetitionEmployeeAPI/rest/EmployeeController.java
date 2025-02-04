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
import com.fabian.repetitionEmployeeAPI.dao.EmployeeDAO;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	private EmployeeDAO employeeDao;

	@Autowired
	public EmployeeController(EmployeeDAO employeeDao) {
		this.employeeDao = employeeDao;
	}

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		List<Employee> employees = null;
		try {
			employees = employeeDao.getAllEmployees();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employees;
	}
	
	@GetMapping("/employees/{id}")
	public String getEmployeeById(@PathVariable("id") int id ) {
	
		Employee employee = null;
		try {
		employee = employeeDao.getEmployeeById(id);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		return employee.toString();
	}
	
	
	@PostMapping("/employees")
		public String postEmployee(@RequestBody Employee employee) {
			employee.setId(0);
			try {
				employeeDao.saveEmployee(employee);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return null;
			}
			return employee.toString();
		}
	
	
	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable("id") int id )  {
		try {
			employeeDao.deleteEmployeeById(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		return "Employee with id successfully deleted " + id;
	}
	
	
	
	@PutMapping("/employees")
	public String updateEmployee(@RequestBody Employee employee) {
		try {
			employeeDao.saveEmployee(employee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Employee updated";
	}

}

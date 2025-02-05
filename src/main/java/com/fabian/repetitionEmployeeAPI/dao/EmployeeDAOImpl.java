package com.fabian.repetitionEmployeeAPI.dao;

import java.io.UncheckedIOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import com.fabian.repetitionEmployeeAPI.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public Employee saveEmployee(Employee employee) {
		  try {
			if (employee.getId() != 0) {
		        return entityManager.merge(employee); 
		    } else {
		         entityManager.persist(employee);
		        return employee; 
		    } 
		}
		catch(Exception e) {
			throw new DatabaseException("Database issue with saving/updating employee");
		}
	}

	
	@Override
	public Employee getEmployeeById(int id) {
		TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee where id=:uid", Employee.class);
		theQuery.setParameter("uid", id);

		Employee employee = null;
		try {
			employee = theQuery.getSingleResult();
		} catch (Exception e) {
			if(employee == null) {
				throw new EmployeeNotFoundException("Requested Employee could not be found");
			}
			throw new DatabaseException("Database issue with retrieving employee");
		}
		return employee;
	}



	@Override
	public List<Employee> getAllEmployees() {
		TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
		List<Employee> employees = null;
		try {
		employees = theQuery.getResultList();
		}
		catch(Exception exc) {
		throw new DatabaseException("There is a database issue with retrieving the entries");	
		}
		if(employees.size() == 0) {
		throw new EmployeeNotFoundException("Requested Employees could not be found");
		}
		return employees;
	}

	@Override
	@Transactional
	public String deleteEmployeeById(int id) {
		
	Employee employee = entityManager.find(Employee.class, id);
		
	  if (employee == null) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " does not exist.");
        }
		
		try {
			Query query = entityManager.createQuery("DELETE FROM Employee e WHERE e.id = :id");
			query.setParameter("id", id);
			int numbOfEntities = query.executeUpdate(); 
			if (numbOfEntities == 0) {
	            throw new DatabaseException("Failed to delete employee with ID " + id);
			}
	
			return "Employee with id" + id + " deleted";
		}
		
		catch (Exception exc) {
			throw new DatabaseException("There is an issue with the database");
		}
		
		
	}

}

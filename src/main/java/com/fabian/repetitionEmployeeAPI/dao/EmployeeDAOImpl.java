package com.fabian.repetitionEmployeeAPI.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fabian.repetitionEmployeeAPI.component.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public void saveEmployee(Employee employee) throws Exception {
		try {
			if (employee.getId() != 0) {
		        entityManager.merge(employee); // Use merge() for updates
		    } else {
		        entityManager.persist(employee);
		    }
		}
		
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	
	@Override
	public Employee getEmployeeById(int id) throws Exception {
		if(id < 0) {
			//error handling here
		}
		TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee where id=:uid", Employee.class);
		theQuery.setParameter("uid", id);

		Employee employee = null;
		try {
			employee = theQuery.getSingleResult();
		} catch (Exception e) {
			throw new Exception(e.getMessage());	
		}
		return employee;
	}



	@Override
	public List<Employee> getAllEmployees() throws Exception {
		TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
		List<Employee> employees = null;
		try {
			employees = theQuery.getResultList();
		} catch (Exception e) {
			throw new Exception(e.getMessage());	
		}
		return employees;
	}



	@Override
	@Transactional
	public void deleteEmployeeById(int id) throws Exception {
		Query query = entityManager.createQuery("DELETE FROM Employee e WHERE e.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

}

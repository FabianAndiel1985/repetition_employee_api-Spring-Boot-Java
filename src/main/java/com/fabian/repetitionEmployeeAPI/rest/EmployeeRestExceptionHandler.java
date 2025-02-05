package com.fabian.repetitionEmployeeAPI.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fabian.repetitionEmployeeAPI.dao.DatabaseException;
import com.fabian.repetitionEmployeeAPI.dao.EmployeeNotFoundException;

@RestControllerAdvice
public class EmployeeRestExceptionHandler {

	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<EmployeeErrorReponse> handleException(DatabaseException exc) {
		EmployeeErrorReponse error = new EmployeeErrorReponse();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<EmployeeErrorReponse> handleException(EmployeeNotFoundException exc) {
		EmployeeErrorReponse error = new EmployeeErrorReponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);	
	}
	
	/*
	//generalized 
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
		StudentErrorResponse error = new StudentErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);	
	}
	
*/	
	
	
	

}

package com.fabian.repetitionEmployeeAPI.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fabian.repetitionEmployeeAPI.dao.DatabaseException;
import com.fabian.repetitionEmployeeAPI.dao.EmployeeNotFoundException;
import com.fabian.repetitionEmployeeAPI.service.UpdateIdMissingException;

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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
	
	@ExceptionHandler(UpdateIdMissingException.class)
	public ResponseEntity<EmployeeErrorReponse> handleException(UpdateIdMissingException exc) {
		EmployeeErrorReponse error = new EmployeeErrorReponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);	
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

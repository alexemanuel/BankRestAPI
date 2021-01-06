package com.zup.orangetalents.exception;

import org.hibernate.exception.ConstraintViolationException;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ConstraintViolationExceptionHandler {
	
	// Postgresql unique violation error code
	private static final String UNIQUE_VIOLATION = "23505";
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handlerConstraintViolationException(ConstraintViolationException exception) {		
		
		String defaultErrorTitle = "Constraint Violation Error";
		String SQlStateErrorCode = exception.getSQLState();
		
		if(SQlStateErrorCode.equals(UNIQUE_VIOLATION)) {
			defaultErrorTitle = "Duplicated Key Error";
		}
							
		ErrorDetails x = new ErrorDetails(HttpStatus.CONFLICT.toString(), 
										defaultErrorTitle,
										 exception.getCause().getMessage(),
										 null);		
	
		return new ResponseEntity<>(x, HttpStatus.CONFLICT);
	}
}
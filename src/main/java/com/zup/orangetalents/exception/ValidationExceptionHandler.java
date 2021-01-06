package com.zup.orangetalents.exception;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice 
public class ValidationExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		List<FieldError> errors = exception.getBindingResult().getFieldErrors();
		Set<ErrorDetails> x = new HashSet<ErrorDetails>();
		
		for(FieldError error: errors) {
			String details = error.getDefaultMessage();
			x.add(new ErrorDetails(HttpStatus.BAD_REQUEST.toString(), 
									"Validation Error", 
									String.format(details, error.getRejectedValue()), 
									error.getField()));
		}							
		return new ResponseEntity<>(x, HttpStatus.BAD_REQUEST);
	}

}

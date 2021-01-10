package com.zup.orangetalents.exception.handler;

import static org.springframework.http.HttpStatus.CONFLICT;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zup.orangetalents.jsonapi.JsonApiError;
import com.zup.orangetalents.jsonapi.JsonApiErrorResponse;


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
		
		JsonApiError error = new JsonApiError()
									.withStatus(CONFLICT.toString())
									.withTitle(defaultErrorTitle)
									.withDetails(exception.getCause().getMessage());
		
		JsonApiErrorResponse<JsonApiError> jsonApiErrorObject = new JsonApiErrorResponse<JsonApiError>(List.of(error));
		return new ResponseEntity<>(jsonApiErrorObject, HttpStatus.CONFLICT);
	}
}
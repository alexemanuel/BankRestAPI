package com.zup.orangetalents.exception.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zup.orangetalents.jsonapi.JsonApiError;
import com.zup.orangetalents.jsonapi.JsonApiErrorResponse;

@ControllerAdvice 
public class ValidationExceptionHandler {
	
	private static final String TITLE_ERROR = "Validation Error";
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		// Using a Set we guarantee that there will be only one error for field
		Set<JsonApiError> errors = new HashSet<JsonApiError>();
		
		for(FieldError error: fieldErrors) {
			String errorDetails = String.format(error.getDefaultMessage(), error.getRejectedValue());
			String fieldName = error.getField();
			
			JsonApiError jsonApiError = new JsonApiError()
											.withStatus(BAD_REQUEST.toString())
											.withTitle(TITLE_ERROR)
											.withDetails(errorDetails)
											.withFieldName(fieldName);
			errors.add(jsonApiError);
		}
		JsonApiErrorResponse<JsonApiError> jsonApiErrorObject = new JsonApiErrorResponse<JsonApiError>(errors);
		return new ResponseEntity<>(jsonApiErrorObject, BAD_REQUEST);
	}
}

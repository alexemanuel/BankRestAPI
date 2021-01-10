package com.zup.orangetalents.exception.handler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zup.orangetalents.exception.AccountNotFoundException;
import com.zup.orangetalents.jsonapi.JsonApiError;
import com.zup.orangetalents.jsonapi.JsonApiErrorResponse;

@ControllerAdvice
public class AccountNotFoundExceptionHandler {
	
	private static final String TITLE_ERROR = "Account Not Found Error";
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<?> handlerAccountNotFoundException(AccountNotFoundException exception) {
		
		JsonApiError error = new JsonApiError()
									.withStatus(NOT_FOUND.toString())
									.withTitle(TITLE_ERROR)
									.withDetails(exception.getMessage());
							
		JsonApiErrorResponse<JsonApiError> jsonApiErrorObject = new JsonApiErrorResponse<JsonApiError>(List.of(error));
		return new ResponseEntity<>(jsonApiErrorObject, NOT_FOUND);
	}	
}

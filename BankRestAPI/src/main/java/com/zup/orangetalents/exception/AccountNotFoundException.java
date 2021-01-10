package com.zup.orangetalents.exception;

public class AccountNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public AccountNotFoundException(Object id) {
		super(String.format("Account with id (%s) was not found", id));
	}
}

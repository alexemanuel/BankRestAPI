package com.zup.orangetalents.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.orangetalents.model.Account;
import com.zup.orangetalents.repository.AccountRepository;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
	
	@Autowired
	AccountRepository accountRepository;
	
	@PostMapping
	public Account createAccount(@Valid @RequestBody Account account) {
		return accountRepository.save(account);
	}
}

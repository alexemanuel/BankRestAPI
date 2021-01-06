package com.zup.orangetalents.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.orangetalents.jsonapi.JsonApiData;
import com.zup.orangetalents.jsonapi.JsonApiModel;
import com.zup.orangetalents.model.Account;
import com.zup.orangetalents.repository.AccountRepository;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
	
	@Autowired
	AccountRepository accountRepository;
	
	@PostMapping
	public ResponseEntity<JsonApiModel> createAccount(@Valid @RequestBody Account account) {
		Account registredAccount = accountRepository.save(account);
		
		JsonApiData<Account> jsonApiData = new JsonApiData<Account>()
				.withAttributes(registredAccount)
				.withId(String.valueOf(registredAccount.getId()))
				.withType("accounts")
				.withLink(linkTo(methodOn(AccountController.class).createAccount(account)).withSelfRel())
				.withLink(linkTo(methodOn(AccountController.class).createAccount(account)).withSelfRel());
		
		JsonApiModel jsonApiModel = new JsonApiModel(jsonApiData);
		
		return new ResponseEntity<JsonApiModel>(jsonApiModel, HttpStatus.CREATED);
	}
}

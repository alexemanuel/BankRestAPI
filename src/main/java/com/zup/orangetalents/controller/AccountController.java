package com.zup.orangetalents.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.orangetalents.exception.AccountNotFoundException;
import com.zup.orangetalents.jsonapi.JsonApiData;
import com.zup.orangetalents.jsonapi.JsonApiModel;
import com.zup.orangetalents.model.Account;
import com.zup.orangetalents.repository.AccountRepository;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
	
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping("{id}")
	public ResponseEntity<?> getAccount(@PathVariable(value = "id") long id) {
		Account account = accountRepository.findById(id).
										orElseThrow(() -> new AccountNotFoundException(id));
			
		JsonApiData<Account> jsonApiData = new JsonApiData<Account>()
				.withAttributes(account)
				.withId(id)
				.withType("accounts")
				.withLink(linkTo(methodOn(AccountController.class).getAccount(id)).withSelfRel())
				.withLink(linkTo(methodOn(AccountController.class).getAllAccounts()).withRel("Accounts"));
		
		JsonApiModel<?> jsonApiModel = new JsonApiModel<>(jsonApiData);
		
		return ResponseEntity.status(HttpStatus.OK).body(jsonApiModel);		
	}
	
	@GetMapping
	public ResponseEntity<?> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();		
		
		List<JsonApiData<Account>> jsonApiData = StreamSupport.stream(accounts.spliterator(), false)
			.map(account -> {
				long id = account.getId();
				
				return new JsonApiData<Account>()
						.withAttributes(account)
						.withId(id)
						.withType("accounts")
						.withLink(linkTo(methodOn(AccountController.class).getAccount(id)).withSelfRel());
				
			}).collect(Collectors.toList());
		
		JsonApiModel<?> jsonApiModel = new JsonApiModel<>(jsonApiData);
		
		return ResponseEntity.status(HttpStatus.OK).body(jsonApiModel);
	}
	

	@PostMapping
	public ResponseEntity<?> createAccount(@Valid @RequestBody Account account) {
		Account registredAccount = accountRepository.save(account);
		long id = registredAccount.getId();
		
		JsonApiData<Account> jsonApiData = new JsonApiData<Account>()
				.withAttributes(registredAccount)
				.withId(id)
				.withType("accounts")
				.withLink(linkTo(methodOn(AccountController.class).createAccount(account)).withSelfRel())
				.withLink(linkTo(methodOn(AccountController.class).getAccount(id)).withRel("Account"));
		
		JsonApiModel<?> jsonApiModel = new JsonApiModel<>(jsonApiData);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(jsonApiModel);
	}
}

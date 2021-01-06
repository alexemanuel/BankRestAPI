package com.zup.orangetalents.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "Name must not be blank")
	@Min(value = 5, message = "Name must be at least 5 characters")
	private String name;
	
	@NotBlank(message = "Email must have a value")
	@Email(message = "Invalid Email format. Email must follow the formatter **@** ")
	private String email;
	
	@NotBlank(message = "CPF must not be blank")
	@CPF(message = "Invalid CPF. Try again with a valid CPF")
	private String cpf;
	
	@NotBlank(message = "Birth Date must not be blank")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ")
	@Past(message = "Birth Date must be before the current date")
	private Date birthData;
	
	public Account() {}
}

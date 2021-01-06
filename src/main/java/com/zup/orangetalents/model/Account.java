package com.zup.orangetalents.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Account {
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "Name must not be blank")
	@Size(min = 5, message = "Name must have at least 5 characters")
	private String name;
	
	@NotBlank(message = "Email must have a value")
	@Email(message = "Invalid Email format. Email must follow the formatter **@** ")
	private String email;
	
	@NotBlank(message = "CPF must not be blank")
	@CPF(message = "Invalid CPF. Try again with a valid CPF")
	private String cpf;
	
	@NotNull(message = "Birth Date must not be blank")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Past(message = "Birth Date must be before the current date")
	private Date birthDate;
	
	public Account() {}
}

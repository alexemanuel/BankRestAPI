package com.zup.orangetalents.jsonapi;

import java.util.Date;

import lombok.Getter;

@Getter
public class JsonApiResponseTemplate {
	//Define a specific templates to the JSON responses
	 
	//API version
	private final String version = "1.0";
	//Timestamp of the response
	private final Date timestamp = new Date();
}

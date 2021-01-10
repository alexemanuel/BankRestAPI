package com.zup.orangetalents.jsonapi;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@Getter
@With
@AllArgsConstructor
@NoArgsConstructor
public class JsonApiError {
	
	// The HTTP status code applicable to this problem
	private String status;
	// A short, human-readable summary of the problem
	private String title;
	// A human-readable explanation specific to this occurrence of the problem
	private String details;	
	// Field that originated the error
	@JsonIgnore
	private String fieldName;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JsonApiError other = (JsonApiError) obj;
		if (fieldName == null) {
			if (other.fieldName != null)
				return false;
		} else if (!fieldName.equals(other.fieldName))
			return false;
		return true;
	}
}

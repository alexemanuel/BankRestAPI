package com.zup.orangetalents.jsonapi;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;


@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"version", "timestamp", "data"})
public class JsonApiModel extends JsonApiResponseTemplate{
	private JsonApiData<?> data;
}



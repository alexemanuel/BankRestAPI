package com.zup.orangetalents.jsonapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;


@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "type", "attributes", "links"})
public class JsonApiData<T> {
 
	// String that identify uniquely that resource type
	private Object id;
	// The resource category
	private String type;
	// A list of links object containing links related to the resource.
	private List<Link> links = new ArrayList<Link>();
	// An attribute object representing some of the resource’s data
	private T attributes;
	
	public JsonApiData<T> withLink(Link link) {
		links.add(link);
		return this;
	}
}

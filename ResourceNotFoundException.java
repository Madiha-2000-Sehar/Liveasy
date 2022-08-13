package com.liveasy.loads.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ResourceNotFoundException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	private String message;
	/*String resourceName;
	String fieldName;
	String fieldValue;
	public ResourceNotFoundException(String resourceName, String fieldName, String id) {
		super(String.format("%s is not found with %s: %s", resourceName,fieldName,id));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = id;*/
	
}

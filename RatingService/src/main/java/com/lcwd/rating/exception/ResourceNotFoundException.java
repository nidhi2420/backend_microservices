package com.lcwd.rating.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	private String resourceName;
	private String resourceId;
	private int resourceValue;
	
	public ResourceNotFoundException(String resourceName,String resourceId,int resourceValue) {
		super(String.format("%s not found with this %s:%s",resourceName,resourceId,resourceValue));
		this.resourceName = resourceName;
		this.resourceId = resourceId;
		this.resourceValue = resourceValue;
	}
	
}

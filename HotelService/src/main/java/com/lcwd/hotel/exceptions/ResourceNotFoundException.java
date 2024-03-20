package com.lcwd.hotel.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private String resourceName;
	private String resourceField;
	private Integer resourceId;
	public ResourceNotFoundException(String resourceName, String resourceField, Integer resourceId) {
		super(String.format("%s not found with %s : %s",resourceName,resourceField,resourceId));
		this.resourceName = resourceName;
		this.resourceField = resourceField;
		this.resourceId = resourceId;
	}
}

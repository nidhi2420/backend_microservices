package com.user.service.exceptions;

public class ResourceNotFoundException  extends RuntimeException{

	private String resourceName;
	private String resourceId;
	private Integer id;
	
	public ResourceNotFoundException(String resourceName,String resourceId,Integer id) {
		super(String.format("%s resouce not found with %s : %s",resourceName,resourceId,id));
		this.resourceId= resourceId;
		this.resourceName = resourceName;
		this.id = id;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
}

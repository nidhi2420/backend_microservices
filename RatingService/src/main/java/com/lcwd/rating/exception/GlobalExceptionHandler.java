package com.lcwd.rating.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lcwd.rating.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceErrorHandler(ResourceNotFoundException ex){
		ApiResponse response = new ApiResponse();
		response.setMessage(ex.getMessage());
		response.setStatus(false);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);		
	}

}

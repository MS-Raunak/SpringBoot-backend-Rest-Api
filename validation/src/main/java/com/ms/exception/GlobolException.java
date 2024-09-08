package com.ms.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobolException {
	
	//handle resource not found
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		
		ApiResponse apiResponse = new ApiResponse(message, false);
		
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	} 
	
	
	//Handle field validation
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> map = new HashMap();
		
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String field = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			map.put(field, message);
		});
		
		return new ResponseEntity<Map<String,String>>(map, HttpStatus.BAD_REQUEST);
	}
}

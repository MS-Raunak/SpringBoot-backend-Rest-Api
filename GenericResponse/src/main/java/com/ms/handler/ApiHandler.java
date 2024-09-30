package com.ms.handler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiHandler {
	private Integer statusCode;
	private String message;
	private Object data;
	
	
	public ResponseEntity<?> createApiResponse(){
		Map<String, Object> response = new LinkedHashMap<>();
		response.put("status", statusCode);
		response.put("message", message);
		response.put("data", data);
		
		return new ResponseEntity<>(response, HttpStatus.valueOf(statusCode));
	}
}

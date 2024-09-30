package com.ms.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ms.handler.ApiHandler;

public class CommonUtil {
	public static ResponseEntity<?> createApiBuilderResponse(HttpStatus statusCode, String message, Object data){
		ApiHandler apiHandler = new ApiHandler(statusCode.value(), message, data);
		
		return apiHandler.createApiResponse();
		
	}
}

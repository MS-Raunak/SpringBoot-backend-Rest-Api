package com.ms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ApiResponse {
	String message;
	Boolean success;
}

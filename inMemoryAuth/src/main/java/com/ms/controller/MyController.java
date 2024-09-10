package com.ms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class MyController {
	
	@GetMapping("/")
	public String get(HttpServletRequest request) {
		return "Hello India"+request.getSession().getId();
	}

}

package com.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.model.Users;
import com.ms.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public Users registerUser(@RequestBody Users user) {
		return userService.registerUser(user);
	}
}

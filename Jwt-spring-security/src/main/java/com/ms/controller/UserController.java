package com.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.model.Users;
import com.ms.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String getMethodName(HttpServletRequest request) {
		return "Hello user, here is your session id:  " + request.getSession().getId();
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<Users> postMethodName(@RequestBody Users user) {
		
		Users saveUser = userService.saveUser(user);
		if (saveUser==null) {
			return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Users>(saveUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/users")
	public List<Users> getUsers() {
		return userService.getAllUsers();
	}
	
	
	
}

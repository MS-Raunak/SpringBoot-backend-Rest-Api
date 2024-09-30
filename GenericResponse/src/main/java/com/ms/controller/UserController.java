package com.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.entity.User;
import com.ms.service.UserService;
import com.ms.util.CommonUtil;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {
		List<User> users = userService.getUsers();
		return CommonUtil.createApiBuilderResponse(HttpStatus.OK, "Ok", users);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Integer id) {
		User user = userService.getUserById(id);
		return CommonUtil.createApiBuilderResponse(HttpStatus.OK, "Ok", user);
	}
	
	
	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		User saveUser = userService.saveUser(user);
		return CommonUtil.createApiBuilderResponse(HttpStatus.CREATED, "Success", "Created");
	}
	
	
}

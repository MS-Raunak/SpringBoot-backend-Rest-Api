package com.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.model.User;
import com.ms.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		User saveUser = userService.saveUser(user);
		if (ObjectUtils.isEmpty(saveUser)) {
			return new ResponseEntity<>("Something error found..User not saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("user saved successfully", HttpStatus.CREATED);
	}
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<?> getUser(@PathVariable Integer id) {
		User user = userService.getUserById(id);
		if(ObjectUtils.isEmpty(user)) {
			return new ResponseEntity<>("User not found with this id", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/getUsers")
	public ResponseEntity<?> getAllUser() {
		List<User> allUsers = userService.getAllUsers();
		if (ObjectUtils.isArray(allUsers)) {
			return new ResponseEntity<>("User not found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<?> putMethodName(@PathVariable Integer id, @RequestBody User user) {
		User updatedUser = userService.updateUserById(user, id);
		if (ObjectUtils.isEmpty(updatedUser)) {
			return new ResponseEntity<>("User not found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id){
		User userById = userService.getUserById(id);
		
		if (ObjectUtils.isEmpty(userById)) {
			return new ResponseEntity<>("User not found to delete", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		userService.deleteUserById(id);
		return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
	}
	
	
	

}

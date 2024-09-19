package com.ms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ms.model.Users;

public interface UserService {
	public Users saveUser(Users user);

	public List<Users> getAllUsers();

}

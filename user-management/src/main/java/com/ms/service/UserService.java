package com.ms.service;

import java.util.List;

import com.ms.model.User;

public interface UserService {
	public User saveUser(User user);
	public User getUserById(Integer id);
	public List<User> getAllUsers();
	public User updateUserById(User user, Integer id);
	public void deleteUserById(Integer id);
}

package com.ms.service;

import java.util.List;

import com.ms.entity.User;

public interface UserService {
	User saveUser(User user);
	List<User> getUsers();
	User getUserById(Integer id);
}

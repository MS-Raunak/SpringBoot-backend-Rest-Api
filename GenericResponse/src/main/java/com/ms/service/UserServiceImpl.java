package com.ms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.entity.User;
import com.ms.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepo userRepo;

	@Override
	public User saveUser(User user) {
		User savedUser = userRepo.save(user);
		return savedUser;
	}

	@Override
	public List<User> getUsers() {
		
		return userRepo.findAll();
	}

	@Override
	public User getUserById(Integer id) {
		
		 Optional<User> byId = userRepo.findById(id);
		 return byId.get();
	}

}

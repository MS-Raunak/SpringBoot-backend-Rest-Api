package com.ms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.model.Users;
import com.ms.repo.UserRepo;
import com.ms.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepo userRepo;

	@Override
	public Users registerUser(Users user) {
		
		return userRepo.save(user);
	}

}

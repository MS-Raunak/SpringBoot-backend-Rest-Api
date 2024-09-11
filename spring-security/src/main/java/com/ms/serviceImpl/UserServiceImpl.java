package com.ms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ms.model.Users;
import com.ms.repo.UserRepo;
import com.ms.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepo userRepo;
	@Autowired
	PasswordEncoder passwordEncoder;


	@Override
	public Users registerUser(Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

}

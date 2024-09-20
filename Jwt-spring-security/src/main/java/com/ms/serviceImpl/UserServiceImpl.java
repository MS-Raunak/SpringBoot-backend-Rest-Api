package com.ms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ms.model.Users;
import com.ms.repo.UserRepo;
import com.ms.service.JWTService;
import com.ms.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JWTService jwtService;
	
	@Override
	public Users saveUser(Users user) {
		 user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		 
		 Users savedUser = userRepo.save(user);
		 
		 if (ObjectUtils.isEmpty(savedUser)) {
			return null;
		}
		 
		 return savedUser;
	}

	@Override
	public List<Users> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public String verify(Users user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "fail";
        }
    }

}

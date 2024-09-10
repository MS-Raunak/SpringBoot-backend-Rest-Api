package com.ms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ms.model.UserPrincipal;
import com.ms.model.Users;
import com.ms.repo.UserRepo;

//UserDetailsService interface provided by spring security, we just have to implement
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findByUsername(username);
		
		if (user==null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("User not found");
		}
		
		return new UserPrincipal(user);
	}

}

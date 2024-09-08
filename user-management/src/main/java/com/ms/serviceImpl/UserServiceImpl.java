package com.ms.serviceImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ms.model.User;
import com.ms.repository.UserRepository;
import com.ms.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		User saveUser = userRepository.save(user);
		if (!ObjectUtils.isEmpty(saveUser)) {
			return saveUser;
		}
		return null;
	}

	@Override
	public User getUserById(Integer id) {
		Optional<User> optional = userRepository.findById(id);
		User user = null;
		if (optional.isPresent()) {
			user = optional.get();
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> allUsers = userRepository.findAll();
		if (ObjectUtils.isEmpty(allUsers)) {
			return null;
		}
		return allUsers;
	}

	@Override
	public User updateUserById(User user, Integer id) {
		Optional<User> optional = userRepository.findById(id);
		
		if (optional.isPresent()) {
			User u = optional.get();
			u.setId(id);
			u.setUsername(user.getUsername());
			u.setAge(user.getAge());
			u.setEmail(user.getEmail());
			
			userRepository.save(u);
			
			return u;
		}
		return null;
	}

	@Override
	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);	
	}

}

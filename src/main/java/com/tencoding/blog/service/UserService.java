package com.tencoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public int saveUser(User user) {
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	@Transactional(readOnly = true)
	public User login(User user) {
		// repository select요청
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
}

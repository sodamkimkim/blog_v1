package com.tencoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.UserRepository;

@Service // IOC 등록
public class UserService {
	
	@Autowired // 자동으로 초기화까지 해줌
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder; // bean으로 등록했기 때문에 ioc에 이 객체가 들어가 있고, 싱글톤 패턴으로 되어있기 때문에 그냥 편하게 갖다 쓰면된다.
	 
	@Transactional
	public int saveUser(User user) {

		try {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			user.setPassword(encPassword); // 암호화된 패스워드 넣을 거임.
			user.setRole(RoleType.USER);
		
			userRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	
//	@Transactional(readOnly = true)
//	public User login(User user) {
//		// repository select요청
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//		
//	}
}

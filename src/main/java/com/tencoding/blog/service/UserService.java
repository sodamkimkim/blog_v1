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
	//DI 해서 데이터베이스 데이터 가져옴

//	/**
//	 * 서비스가 필요한 이유
//	 * 현실 모델링: 레스토랑(주문: 종업원 - 주방장)
//	 * 	 * 트랜잭션관리
//송금: 홍길도(10), 이순신(0) --> 홍길동(select)-->이순신(5) (insert)
//하나의 기능+ 하나의 기능을 묶어서 단위의 기능을 만들어 내기 위해 필요하다
//하나의기능(서버가 될 수 있다.)
//	 * /
	
	@Autowired // 자동으로 초기화까지 해줌
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder; //bean으로 등록했기 때문에 ioc에 이 객체가 들어가 있고,
	// 싱글톤 패턴으로 설계되었다.
	
	@Transactional
	public int saveUser(User user) {

		try {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			user.setPassword(encPassword); // 암호화된 패스워드 넣을 것임.
			user.setRole(RoleType.USER);
			userRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	

}

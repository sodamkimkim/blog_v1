package com.tencoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

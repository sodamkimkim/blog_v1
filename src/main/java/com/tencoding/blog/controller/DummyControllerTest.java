package com.tencoding.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	//UserRepository 는 메모리에 올라가 있는 상태이다.
	// 그럼 어떻게 가져 오나요?--> 스프링에서는 DI하면 됨(의존성 주입)
	@Autowired // 스프링 컴포넌트 스캔할 때 자동으로 뉴 때려진다. reference가 이 변수에 담긴다.
	private UserRepository userRepository;
	
	//로그인은 겟방식 x, post방식 써야한다 privacy 때문에
	@PostMapping("/dummy/join")
	public String join(@RequestBody User user) {
		System.out.println("--------------------");

		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		System.out.println("--------------------");
		
		System.out.println(user.getId());
		System.out.println(user.getCreateDate());
		System.out.println(user.getRole()); // null ->default 값 불가.
//		user.setRole(RoleType.USER.toString());// 데이터타입 일치화
		user.setRole(RoleType.USER); 
		userRepository.save(user);
		return "회원가입 완료되었습니다.";
		
	}
}
;
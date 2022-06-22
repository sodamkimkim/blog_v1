package com.tencoding.blog.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.model.User;
import com.tencoding.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	UserService userService;

	// 회원가입할 때 사용
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {

		System.out.println("UserApiController 호출 됨!!!");
		user.setRole(RoleType.USER);
		int result = userService.saveUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}

	// 로그인할 때 사용
	// /blog/api/user/login
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
		System.out.println("login 호출 됨.");
		// 서비스한테 이 사용자가 데이터베이스에 있는지 확인해줘 요청함.
		// principal은 접근 주체라는 의미로 사용
		User principal = userService.login(user);
		// 접근 주체가 정상적으로 username, password 확인! (세션이라는 거대한 메모리에 저장)
		if (principal != null) {
			session.setAttribute("principal", principal); // "키", 밸류
			System.out.println("세션 정보가 저장되었습니다.");
		}

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}

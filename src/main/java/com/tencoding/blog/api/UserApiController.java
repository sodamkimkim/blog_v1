package com.tencoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.model.User;
import com.tencoding.blog.service.UserService;

@RestController // json을 리턴하는 controller
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager; //필터 뜰 때 미리 security에 올려놔야 한다.
	

	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		userService.updateUser(user);
		// 강제로 Authentication객체를 만들고, SecurityContext안에 집어 넣으면 된다.
		
		// 1. Authentication 객체 생성
		// 2. AuthenticationManager를 메모리에 올려서, authenticate메서드를 사용해서 Authentication객체를 저장한다.
		// 3. 세션 - SecurityContextHolder.getContext().setAuthentication()활용해서 ,, Authentication객체 만든거 넣어주면 된다.
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
				); // 토큰 만들어주면 매니저 생성되고, authentication객체에 강제로 넣어주기만 하면 된다.
		SecurityContextHolder.getContext().setAuthentication(authentication); // securityContext는 authentication바로 밖에 있는 거. 이쪽으로 먼저 접근한다.
				
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

//	security적용 전
//	// 회원가입할 때 사용
//	@PostMapping("/api/user")
//	public ResponseDto<Integer> save(@RequestBody User user) {
//
//		System.out.println("UserApiController 호출 됨!!!");
//		user.setRole(RoleType.USER);
//		int result = userService.saveUser(user);
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
//	}
//
//	// 로그인할 때 사용
//	// /blog/api/user/login
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//		// HttpSession 은 내장되어있는 클래스인데,,
////		@Autowired
////		private HttpSession session;
////		이렇게 멤버변수로 선언해도 된다.
////		매개변수로 안써도됨(저거 지워도 됨.)
//		System.out.println("login 호출 됨.");
//		// 서비스한테 이 사용자가 데이터베이스에 있는지 확인해줘 요청함.
//		// principal은 접근 주체라는 의미로 사용
//		User principal = userService.login(user);
//		// 접근 주체가 정상적으로 username, password 확인! (세션이라는 거대한 메모리에 저장)
//		if (principal != null) {
//			session.setAttribute("principal", principal); // 세션에 값 저장할 때 "키", 밸류 형식으로 저장한다.
//			System.out.println("세션 정보가 저장되었습니다.");
//			return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//		}
//		
//		else {
//			return null;
//		}
//
//
//	}
}

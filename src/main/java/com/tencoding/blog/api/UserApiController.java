package com.tencoding.blog.api;

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
	private UserService userService;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(User user){ // RequestBody어노테이션 쓴ㄴ다는 것은 json으로넣겠다는 건데. 
		//application/x-www-form-urlencoded;charset=UTF-8 이거는 키밸류 값으로 던져줌. 여기에 맞춰줘야 해서 매개변수User user앞의 @RequestBody지워주겠음.
		System.out.println(user.toString());
		int result=userService.saveUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),result);
	}

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

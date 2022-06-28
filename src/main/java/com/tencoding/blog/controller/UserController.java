package com.tencoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.model.User;
import com.tencoding.blog.service.UserService;


@Controller
public class UserController {
	@Autowired
	private HttpSession httpSession; 

	
	@Autowired
	private UserService userService;
	
	//../blog/user/login_form
	@GetMapping("/auth/login_form")
	public String loginForm() {
		return "user/login_form";
	}
	
	@GetMapping("/auth/join_form")
	public String joinForm() {
		return "user/join_form";
	}
	
	@GetMapping("/logout")
	public String logout() {
		// 세션정보를 제거. (로그아웃 처리)
		httpSession.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/user/update_form")
	public String updateForm() {
		return "user/update_form";
	}
	
	@PostMapping("/auth/joinProc")
	public String save(User user){ // RequestBody어노테이션 쓴ㄴ다는 것은 json으로넣겠다는 건데. 
		//application/x-www-form-urlencoded;charset=UTF-8 이거는 키밸류 값으로 던져줌.
		//여기서 회원가입기능은 자바스크립트 ajax가 아니라 form으로 데이터 주고 받을 거고
		// 이때 데이터 통신 default설정은 application/x-www-form-urlencoded;charset=UTF-8.
		// 여기에 맞춰줘야 해서 매개변수User user앞의 @RequestBody지워주겠음.

		int result=userService.saveUser(user);
		return "redirect:/";
	}
}

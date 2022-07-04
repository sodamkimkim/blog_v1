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

}

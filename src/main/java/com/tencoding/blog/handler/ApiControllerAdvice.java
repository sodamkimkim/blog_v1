package com.tencoding.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice
@RestControllerAdvice
public class ApiControllerAdvice {

	@ExceptionHandler(value = IllegalArgumentException.class)
	public String illegalArgumentException(IllegalArgumentException e) {
		return "<h1> " +e.getMessage() + " </h1>";
//		return new IllegalArgumentException("해당 유저는 없는 사용자 입니다." + id); //이거 controller에 적어줬었는데 e.getMessage찍어보면 이 메시지 나옴. 
	}
}

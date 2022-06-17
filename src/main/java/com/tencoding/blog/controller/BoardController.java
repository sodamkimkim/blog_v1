package com.tencoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 페이지를 리턴할 것이기 때문에 restController아니고 cotroller
public class BoardController {

	@GetMapping({"" , "/"})
	public String index() {
		return "home";
	}
	
	//부트스트랩 사용
}

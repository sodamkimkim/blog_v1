package com.tencoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // 페이지를 리턴할 것이기 때문에 restController아니고 cotroller
public class BoardController {


	@GetMapping({"" , "/"})
	public String index() {
		return "home";
	}
	
	//부트스트랩 사용
	
	@GetMapping("/board/save_form")
	public String saveForom () {
		log.info("saveForm 메서드 호출");
		return "/board/save_form";
	}
}

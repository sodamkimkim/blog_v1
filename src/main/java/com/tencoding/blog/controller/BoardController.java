package com.tencoding.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tencoding.blog.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // 페이지를 리턴할 것이기 때문에 restController아니고 cotroller
public class BoardController {


	@Autowired
	BoardService boardService;
	
	@GetMapping({"" , "/", "index"})
	public String index(@PageableDefault(size=2, sort="id", direction=Direction.DESC) Pageable pageable, Model model) {

	
		model.addAttribute("pageable", boardService.getBoardList(pageable));
		return "index";
	}
	
	//부트스트랩 사용
	
	@GetMapping("/board/save_form")
	public String saveForom () {
		log.info("saveForm 메서드 호출");
		return "/board/save_form";
	}
	
	//a태그는 항상 get으로 mapping한다.
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.boardDetail(id));
		return "/board/detail";
	}
}

package com.tencoding.blog.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tencoding.blog.model.Board;
import com.tencoding.blog.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // 페이지를 리턴할 것이기 때문에 restController아니고 cotroller
public class BoardController {


	@Autowired
	BoardService boardService;
	
	@GetMapping({"" , "/", "index"})
	public String index(@PageableDefault(size=5, sort="id", direction=Direction.DESC) Pageable pageable, Model model) {
		//PageableDefault 어노테이션이 limit start end page 해서 자동으로 쿼리 만들어줘서 페이징..
		Page<Board> pageBoards =  boardService.getBoardList(pageable);
		// [1 2 3 4 5 6 7 8 9 10]
		// 1. 현재페이지 앞 뒤로 2칸블록(칸)씩 보여야 함.
		// 2. 페이지 버튼 누르면 해당 페이지로 화면을 이동해야 한다.
		// 3. 현재 페이지에 active하기(활성화)
		
//		int startPage = pageBoards.getPageable().getPageNumber() -2;
//		int endPage = pageBoards.getPageable().getPageNumber() +2;
		int nowPage = pageBoards.getPageable().getPageNumber()+1; // 현재페이지
		int startPage = Math.max(nowPage-2, 1);// 두 int값 중에 큰값반환
		int endPage = Math.min(nowPage+2, pageBoards.getTotalPages());
		System.out.println("----------------------------");
		log.info("현재 화면의 블록 숫자(현재 페이지) : {}", nowPage);
		log.info("현재 화면에 보여질 블록의 시작 번호  : {}", startPage);
		log.info("현재화면에 보여질 마지막 블록의 번호 : {}", endPage);
		log.info("화면에 보여줄 총 게시글 / 한 화면에 보여질 게시글(총 페이지 숫자) : {}", pageBoards.getTotalPages());
		System.out.println("----------------------------");

		// 페이지 번호를 배열로 만들어서 던져주기
		ArrayList<Integer> pageNumbers = new ArrayList<>();
		// 주의 !! 마지막 번호까지 저장하기
		for (int i = startPage; i <= endPage; i++) {
			pageNumbers.add(i);
		}
		
		model.addAttribute("pageable", pageBoards);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageNumbers", pageNumbers);
		
		return "index";
	}

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
	
	@GetMapping("/board/{id}/update_form")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.boardDetail(id));
		return "/board/update_form";
	}

}

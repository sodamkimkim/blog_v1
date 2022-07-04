package com.tencoding.blog.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

	// PageableDefault 어노테이션이 limit start end page 해서 자동으로 쿼리 만들어줘서 페이징..
	@GetMapping({ "", "/", "/board/search" })
	public String index(String q, Model model,
			@PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable) {
		// @RequestParam에 값 안날라오면(null) 오류발생한다. @RequestParam어노테이션 안쓰면 key밸류 구조라서 받아들여짐

		String searchTitle = q == null ? "" : q;
		Page<Board> pageBoards = boardService.searchBoardByTitle(searchTitle, pageable);

//		int startPage = pageBoards.getPageable().getPageNumber() -2;
//		int endPage = pageBoards.getPageable().getPageNumber() +2;
		int nowPage = pageBoards.getPageable().getPageNumber() + 1; // 현재페이지
		int startPage = Math.max(nowPage - 2, 1);// 두 int값 중에 큰값반환
		int endPage = Math.min(nowPage + 2, pageBoards.getTotalPages());
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
		model.addAttribute("searchTitle", searchTitle); // ""또는 "검색어" 
		
		return "index";
	}

	@GetMapping("/board/save_form")
	public String saveForom() {
		log.info("saveForm 메서드 호출");
		return "/board/save_form";
	}

	// a태그는 항상 get으로 mapping한다.
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

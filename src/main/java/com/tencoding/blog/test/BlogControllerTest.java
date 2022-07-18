package com.tencoding.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tencoding.blog.model.Board;

//http://localhost:8080/test/hello
/**
 * 
 * 스프링이 com.tencoding.blog패키지 이하를 스캔해서 모든 자바 파일을 메모리에 new하는 것은 아니고, 특정 어노테이션이
 * 붙어있는 파일들을 new 해서(IOC) 스프링 컨테이너에서 관리해 준다.
 * 
 * @author ITPS
 *
 */
@Controller
@RequestMapping("/test")
public class BlogControllerTest {
	@GetMapping("/hello")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}
	
	@GetMapping("/temp1")
	public String temp1() {
		return "test";
	}
	// key=value title="xxx"&content="xxxx";
	@GetMapping("/xss")
	@ResponseBody
	public String xssTest(Board board) {
		// 기본 파싱전략 키밸류
		// String title, String content이렇게 써도 되는데
		// ObjectMapper동작시켜서 Board써도 됨 - Model의 Board에 title, content만들어져 있으니까 재활용 하겠음
		System.out.println("board : "+board);
		return "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Insert title here</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "	<h1>xss lucy 라이브러리 확인</h1>\r\n"
				+ "</body>\r\n"
				+ "</html>";
	}
	

}

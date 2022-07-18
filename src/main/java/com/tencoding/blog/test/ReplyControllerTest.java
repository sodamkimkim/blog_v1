package com.tencoding.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tencoding.blog.dto.ReplyCountOfBoardDto;
import com.tencoding.blog.model.Board;
import com.tencoding.blog.model.Reply;
import com.tencoding.blog.repository.BoardRepository;
import com.tencoding.blog.repository.ReplyCountOfBoardRepository;
import com.tencoding.blog.repository.ReplyRepository;

@RestController
public class ReplyControllerTest {
	@Autowired
	ReplyCountOfBoardRepository replyCountOfBoardRepository;
	
	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRepository replyRepository;
	
	@GetMapping("/test/board/{boardId}")
	public Board getBoard(@PathVariable int boardId) {
		// jackson라이브러리 실행될 때, 오브젝트로 파싱(json으로 만들 때, getter를 호출하는 메서드 동작이 있는데,
		// 이 때, 무한참조 발생
		return boardRepository.findById(boardId).get();
	}
	/**
	 * board를 호출했을 때, reply에 포함된 board를 무시하고,
	 * reply에서 호출했을 때는 무시하지 않는다.
	 * detail.jsp에서 reply.board를 호출하는 순간, 무한 참조가 일어난다.
	 * => stack-overflow 발생.
	 * 
	 * 하지만 호출하지 않았기 떄문에, 발생하지 않았다.
	 * 
	 * <해결 방법>
	 * --> @JsonIgnoreProperties 사용한다.
	 * 
	 * @return
	 */
	
	@GetMapping("/test/reply")
	public List<Reply> getReply() {
		return replyRepository.findAll();
	}
	
	// ... ./test/group-by-count
	//security적용되어있기 때문에 인증되지않은 사용자들은 다 튕겨낼거임
	// /test/허용해주기
	@GetMapping("/test/group-by-count3")
	public String test3() {
		List<ReplyCountOfBoardDto> result = replyCountOfBoardRepository.getReplyCount();
		return "" + result.toString();
	}
}

package com.tencoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.model.Board;
import com.tencoding.blog.model.Reply;
import com.tencoding.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	BoardService boardService;
	// 문제인식!!
	// 세션을 어떻게 가져와야 하는가? --> @AuthenticationPrincipal PrincipalDetail detail가져와서 사용.
	
	// 1. 주소 맵핑, 데이터 받기
	// 1. 데이터 주소 맵핑, 데이터 받기
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail detail) { // 세션
		// 이 함수가 도착할 때, 전용 security session 안에서 principalDetail을 들고 오는 거임.
		// 무조건 이 화면 들어온다는 거는 세션이 만들어져 있는 상태라서 로그인 확인 같은건 필요 없다.
		boardService.write(board, detail.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // responsedto는 우리가 만든 객체고, allArgs선언했음(생성자)
//		private HttpStatus status; //HttpStatus타입으로 선언하면, controller단에서 return 할때 .value()안적어줘도 된다.

	}

	// 2. 서비스 레이어 만들기
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		boardService.deleteById(id);
		return new ResponseDto<>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
		boardService.modifyBoard(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	// url: `/api/board/${data.boardId}/reply`
	@PostMapping("api/board/{boardId}/reply")
	public ResponseDto<Reply> replySave(@PathVariable int boardId, 
			@RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principalDetail) {
		//  서비스 데이터 처리
		Reply replyEntity = boardService.writeReply(principalDetail.getUser(), boardId, reply);
		System.out.println("여기는 BoardApiController - replySave - principal유저 : " + principalDetail.getUser());
		return new ResponseDto<Reply>(HttpStatus.OK.value(), replyEntity);
	}
	///api/board/${boardId}/reply/${replyId}
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> deleteReply(@PathVariable int boardId, @PathVariable int replyId) {
		System.out.println("boardId:" + boardId);
		System.out.println("replyId:" + replyId);
		boardService.deleteReplyById(replyId);
		return new ResponseDto<>(HttpStatus.OK.value(), 1);
	}
}

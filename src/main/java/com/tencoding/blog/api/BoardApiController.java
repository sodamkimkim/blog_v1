package com.tencoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.model.Board;
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
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail detail) { // 세션 가져오기.
		// 이 함수가 도착할 때, 전용 security session 안에서 principalDetail을 들고 오는 거임.
		// 무조건 이 화면 들어온다는 거는 세션이 만들어져 있는 상태라서 로그인 확인 같은건 필요 없다.
		boardService.write(board, detail.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // responsedto는 우리가 만든 객체고, allArgs선언했음(생성자)
//		private HttpStatus status; //HttpStatus타입으로 선언하면, controller단에서 return 할때 .value()안적어줘도 된다.
		
	}
	// 2. 서비스 레이어 만들기
	// 

}

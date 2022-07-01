package com.tencoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.blog.model.Board;
import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.BoardRepository;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;

	public void write(Board board, User user) {
		// 데이터 넘어올 때, 화면단에서 content랑 title만 넘어오는데,
		// Board는 사실 다른데이터도 필요하다.
		// userId도 필요함(작성자가 누구인지?)
		// 서비스단 만드는 이유?
		// transaction관리하려고..(디비에 엉켜서 바로 접근하지 않기 위해서)
		board.setCount(0);
		board.setUserId(user);
		// 우리가 지금 하고있는 ORM
		// model에서 통으로 데이터 던져주고 있다.
		boardRepository.save(board);
	}
}

package com.tencoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.model.Board;
import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void write(Board board, User user) {
		// 데이터 넘어올때, 화면단에서 content랑 title만 넘어오는데 , Board는 사실 다른 데이터도 필요하다. userId도
		// 필요함.(작성자가 누구인지)
		// 서비스단 만드는 이유?
		// transaction관리하려고.(디비에 엉켜서 바로 접근하지 않기위해)
		board.setCount(0);
		board.setUserId(user);
		// 우리가 지금 하고 있는거 ORM.
		// model에서 통으로 데이터 던져주고 있다.
		boardRepository.save(board);
	}

	@Transactional
	public Page<Board> getBoardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional
	public Board boardDetail(int boardId) {
		return boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글은 찾을 수 없습니다.");
		});
	}

	@Transactional
	public void deleteById(int id) {
		boardRepository.deleteById(id);
	}

	// boardService.modifyBoard(id, board);
	@Transactional
	public void modifyBoard(int id, Board board) { // title, content
		// update는 디비에서 먼저 찾아서 가지고 와야한다.
		Board boardEntity = boardRepository.findById(id).orElseThrow(() -> { // 영속성 컨택스트의 1차 캐시에 담김.
			return new IllegalArgumentException("해당 글은 찾을 수 없습니다.");
		});
		
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		// 더티체킹하려면 @Transactional만 걸어주면된다.
	}
}

package com.tencoding.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tencoding.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{ // <T, id>에서 T 는 어떤엔터티(접근하는 녀석) : Board
	// select * from board where title like 'b%'; 이 쿼리를 ORM이용해서 만들어 주면 됨.
	// native query만들어도 되고, 함수(명명규칙)로 만들어도 되고(-> 스프링 문서에 보면 나와있다.)
	// like는 containing
	// select * from board where title like '%title%'
	Page<Board> findByTitleContaining(String title, Pageable pageable);
}

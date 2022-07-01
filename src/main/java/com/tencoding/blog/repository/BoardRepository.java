package com.tencoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tencoding.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	// <T, id>에서 T는 어떤 엔터티(접근하는 녀석) : Board
}

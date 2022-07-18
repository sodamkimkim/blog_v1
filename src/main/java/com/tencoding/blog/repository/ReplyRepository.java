package com.tencoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tencoding.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	
	//Reply가 가진 컬럼이 아니기 때문에 기본적으로 매핑이 안된다. => qlrm쓰고, repository다른거 만들어 줘야 한다.
//	SELECT * , (SELECT COUNT(boardId)
//			FROM reply AS B
//            WHERE B.boardId = A.id) AS replyCount
//FROM board AS A;
}

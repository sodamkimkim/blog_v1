package com.tencoding.blog.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import com.tencoding.blog.dto.ReplyCountOfBoardDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 자동으로 new해서 메모리에 올려줌(컴포넌트 스캔할 때)
@Repository // 직접 repostiory만들 땐 선언해 줘야 한다. implements jpa ~~ 안쓸 때.

public class ReplyCountOfBoardRepository {
	// 원래 repositoyr만들 때 implements해줬는데
	//qlrm쓸땐 직접 매핑해 줘야 한다.
	// EntityManager가 매핑같은거 해주는 애다.
//	private final EntityManager em;하면 에러 난다. final은 초기화 해줘야함.
	// 멤버변수 잘못 쓰게되면 공유하는 데이터라서 조심해서 써야 한다.
	// 그래서 final을 쓰는것이 기본임.
	
	private final EntityManager em;
//	public List<??오브젝트> getReplyCount(){}
//	오브젝트 타입을 가지고 있는 List를 만들 꺼다.
//	SELECT * , (SELECT COUNT(boardId)
//			FROM reply AS B
//            WHERE B.boardId = A.id) AS replyCount
//FROM board AS A;
	// db -> 자바 이 쿼리의 결과를 받는 오브젝트를 갖는 리스트
	// 모델로 받을 수도 있고 dto로 받을 수도 있고
	public List<ReplyCountOfBoardDto> getReplyCount(){
		
		List<ReplyCountOfBoardDto> list = new ArrayList<ReplyCountOfBoardDto>();
		
		//1. 직접 쿼리문 만들기
		// 항상 + 전 맨 마지막에 한 칸 띄워주기
		// 쿼리 ;지워주고 한 칸 띄워주기
		String queryStr = "SELECT A.id, A.content, (SELECT COUNT(boardId) "
                + "            FROM reply AS B "
                + "            WHERE B.boardId = A.id ) AS replyCount "
                + "FROM  board AS A ";

        Query nativeQuery = em.createNativeQuery(queryStr); // 문자열을 통해 질의어 생성.
		//2가지 방식
		//1. 직접 문자열을 컨트롤해서 object맵핑하는 방식 -> 굉장히 귀찮기 때문에 qlrm라이브러리
		//2. qlrm라이브러리를 사용해서 object맵핑하는 방식
		// 어떤 오브젝트로 떨어질 지 모르기 때문에 리스트 안에 최상위 object
        
        //1. 1번방식 해봄.
//		List<Object[]> resultList = nativeQuery.getResultList();
//		System.out.println(resultList.toString());
//		resultList.forEach(t->{
//			System.out.println(t.toString());
//		});
        
        //2. Qlrm라이브러리 사용
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        list = jpaResultMapper.list(nativeQuery, ReplyCountOfBoardDto.class);// 문자열아니라 쿼리 직접 넣어야함. 쿼리 떤져주고 어떤 오브젝트로 매핑할 것인가.
        // 이렇게 까지 하면 오류난다. (500에러)
        // while문 돌면서 데이터타입 하나하나에 맴핑해 주는데,, datatype이 inteter, string integer.. 등
        // db의 결과값 컬럼이 각각 데이터타입으로 들어옴
        // 근데 BigInteger이런거 조심!! . 컬럼에 따라 내부적으로 타입이 지정되어있는데, count쓴 결과값은 BigInteger타입. 마리아 db는 integer. Mysql은 bigInteger
        // 마리아 db는 컬럼명 테이블명 대소문자 구분이쑴.
		return list;
	}

}

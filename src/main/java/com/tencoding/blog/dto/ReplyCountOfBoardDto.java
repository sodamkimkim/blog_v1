package com.tencoding.blog.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ReplyCountOfBoardDto {
	
//	SELECT * , (SELECT COUNT(boardId)
//			FROM reply AS B
//            WHERE B.boardId = A.id) AS replyCount
//FROM board AS A;
//	얘 결과 받는 dto설계 중..
	private int id; //db에서 던져주는 컬럼 데이터 타입에 맞게 int -> Integer로 할려했으나 번잡하니까 생성자에서 변경해줌
	private String content;
	//count함수로 뽑아낸 결과값.
	private int replyCount;
	
	//<변수 선언으로 자동 맵핑>
//	JpaResultMapper과 동일한 컬럼 수로 맞추고, 동일한 데이터 타입을 선언한다면 
//	직접 커스텀 할 필요는 없다. 알아서 맵핑을 진행해 준다.
//	private Integer id;
//	private String content;
//	private BigInteger replyCount;
	
	//	어떤 녀석을 어떤 키값 매핑되는지 모르면 에러난다. 다적어주던지 커스텀 하던지
	//생성자 만들어 주기
	// <직접 커스텀 하기> -> orelse, 처음 선언부터 데이터타입 맞춰주기
	public ReplyCountOfBoardDto(Object[] objs) {
		// db랑 자바디티오랑 타입 맞춰주기. 
		this.id =((Integer)objs[0]).intValue(); // 리스트의 0번째 형변환
		this.content = ((String)objs[1]);
		this.replyCount = ((BigInteger)objs[2]).intValue();
	}
	
	public ReplyCountOfBoardDto(Integer id, String content, BigInteger replyCount) {
		this.id = id.intValue();
		this.content = content;
		this.replyCount = replyCount.intValue();
	}
	
}

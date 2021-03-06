package com.tencoding.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 같은 변수의 이름으로 데이터 타입을 다르게 사용해야 될 때는,,
//  제네릭 프로그래밍을 생각하자.

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T>{
	private int status; //HttpStatus타입으로 선언하면, controller단에서 return 할때 .value()안적어줘도 된다.
	private T data;
	
}

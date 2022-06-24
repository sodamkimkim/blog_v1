package com.tencoding.blog.test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {
	
	@Test
	public void hashEncode() {
		String encPassword = new BCryptPasswordEncoder().encode("123");
		System.out.println("해시 값:" + encPassword);
		
		// $2a$10$48nrfmwHlkYyLGS6sVjLcO4Lm7FCVQdFJDNrfnHf3TLFCHeN/rxy.
		//해시 값:$2a$10$B.3KhkoPH9Y6rsVUB9lJwekLGKSp3VW8nvCKjrs1Gei812Q9bXh6m

		// junit 테스트 두번 하면 해시값다르게 나온다. 스프링에서는 매번 123넣더라도 해시값이 다르게 나온다. 원래 해시 개념과 조금의 차이가 있다.
		

	}
}

package com.tencoding.blog.test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {
	
	@Test
	public void hashEncode() {
		String encPassword = new BCryptPasswordEncoder().encode("123");
		System.out.println("해시 값: ");
	}
}

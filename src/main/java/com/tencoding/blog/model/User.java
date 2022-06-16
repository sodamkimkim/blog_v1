package com.tencoding.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User 클래스가 자동으로  mysql에 테이블을 생성한다.
public class User {
	
		@Id // db로 따지면 primary key로 설정된다.
		@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB의 넘버링 전략을 따라가겠다. mysql의 auto-increment 사용하는 것.
		private int id;
		
		@Column(nullable = false, length = 30)
		private String username;
		
		@Column(nullable = false, length = 100)
		private String password;
		
		@Column(nullable = false, length = 50)
		private String email;
		
		@ColumnDefault("'user'")
		private String role; // 지금은 String 이지만 Enum타입 사용 권장 : admin, user, manager
		// Enum타입 왜 쓰나? 테이블에 String을 넣으면,, String관련 데이터가 뭐든 들어가는데,,
		// Enum타입들어가면 데이터의 범주화가 이루어 진다.(DOMAIN)
		
		@CreationTimestamp // 시간이 자동으로 입력 된다.
		private Timestamp createDate; //java.sql이 가지고 있는 Timestamp를 갖고 올 것.
		
}

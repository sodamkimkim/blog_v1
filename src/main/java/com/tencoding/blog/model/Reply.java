package com.tencoding.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "userId") // <--Reply테이블에서의 컬럼명이 된다.
	@JsonIgnoreProperties({"password","role", "email", "oauth"})
	private User user;
	
	@ManyToOne // 여러 개의 댓글은 하나의 게시글에 존재할 수 있다.
	@JoinColumn(name="boardId")
	@JsonIgnoreProperties({"replys", "userId"})
	private Board board;
	
	@CreationTimestamp
	private Timestamp createDate;
	

}

package com.tencoding.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content;
	
	@ColumnDefault("0") //디폴트값 설정. int형임 // String 쓰려면 " '안녕' "
	private int count; // 조회수
	
	// 여러 개의 게시글은 하나의 유저를 가진다. 
	@ManyToOne(fetch = FetchType.EAGER) //Board select 한 번에 데이터를 가져와. ** FetchType.LAZY --> 있어 정도만 가져오고, Board테이블 활용해서  user정보 쓸 때서야 갖고옴.
	@JoinColumn(name = "userId")
	private User userId;
	
	// 댓글 정보
	// 하나의 게시글에 여러 개의 댓글이 있을 수 있다.
	// one = board, many = reply
	// mappedBy = "board" board는 reply에 필요 이름이다.
	// mappedBy는 연관관계의 주인이 아니다. FK가 아님
	// DB에 컬럼을 만들지 마시오
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
	@JsonIgnoreProperties({"board", "content"}) // Reply 안에 있는 board getter를 무시
	@OrderBy("id desc")
	private List<Reply> replys;
	
	
	@CreationTimestamp
	private Timestamp createDate;
	
	
	
	
}

package com.tencoding.blog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Role {

//	@Id
//	@Column(length = 20)
//	private String actorName;
//	
//	@Id
//	@Column(length = 255)
//	private String title; // movie의 title을 그대로 가져와서 쓴다.
//	// Role에서 fk의 컬럼을 새롭게 만들지 않고 쓴다.(복합키)
//	// 그냥 @Id하면 pk 두개 되니까 클래스 만들어서 변수 두개. (oop. 하나의 오브젝트로 만든다.)

	@EmbeddedId
	private RoleId roleId;
	
	@MapsId("title") // RoleId.title과 movie의 녀석을 맵핑함.
	@ManyToOne
	@JoinColumn(name = "title")
	private Movie movie;
	
	@Column(length = 2)
	private String category;
	
	@Column(length = 20)
	private String roleName;
	
}

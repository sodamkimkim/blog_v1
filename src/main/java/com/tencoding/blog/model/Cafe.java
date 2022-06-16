package com.tencoding.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cafe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 100)
	private String cafeName;
	
	@Lob
	private String cafeDescription;
	
	@ColumnDefault("0")
	private String visitors;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "boardId")
	private Board board;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User user;
		
}

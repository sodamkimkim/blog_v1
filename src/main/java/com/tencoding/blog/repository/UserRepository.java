package com.tencoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tencoding.blog.model.User;

//IOC 어노테이션 안해줘도 JPARepository는 자동으로 됨
//Bean으로 등록 될 수 있나요? == IOC컨테이너에 가지고 있나요?
//DAO 역할을 하는 Repository.
public interface UserRepository extends JpaRepository<User, Integer> {
	// Spring JPA네이밍 전략
	// prepare statement
	// SELECT * FROM user WHERE username = ?1 AND password = ?2
	User findByUsernameAndPassword(String username, String password); //위의 쿼리 자동으로 만들어줌.
	
	// 두번째 방법 - native query사용
//	@Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2;")
//	User login();
}
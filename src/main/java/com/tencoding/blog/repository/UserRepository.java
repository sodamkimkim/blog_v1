package com.tencoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tencoding.blog.model.User;

//IOC 어노테이션 안해줘도 JPARepository는 자동으로 됨
//Bean으로 등록 될 수 있나요? == IOC컨테이너에 가지고 있나요?
//DAO 역할을 하는 Repository.
public interface UserRepository extends JpaRepository<User, Integer> {

}
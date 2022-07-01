package com.tencoding.blog.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User principal = userRepository.findByUsername(username)
							.orElseThrow(() -> {
								return new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
							});
		return new PrincipalDetail(principal); // security session에 유저정보가 저장된다.
	}
}

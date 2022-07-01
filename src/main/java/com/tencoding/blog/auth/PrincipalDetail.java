package com.tencoding.blog.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tencoding.blog.model.User;

import lombok.Data;

@Data
public class PrincipalDetail implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  User user;
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// 계정의 권한을 반환한다.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 이 collection에 사용자의 계정을 넣어 리턴함.
		Collection<GrantedAuthority> collectors = new ArrayList<GrantedAuthority>();
//		collectors.add(new GrantedAuthority() {
//			@Override
//			public String getAuthority() {
//					// "ROLE_" 스프링 security 규칙(꼭 넣기)
//					// "ROLE_USER", "ROLE_ADMIN"
//				return "ROLE_" + user.getRole();
//			}
//		});
//		
		collectors.add(() -> {
			return "ROLE_"+user.getRole();
		});
		return collectors;
	}
}

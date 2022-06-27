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
	 * 모든 class UID를 가지고 있다.
	 * class의 내용이 변경되면 UID값 역시 같이 변경된다.
	 * 직렬화하여 통신하고, UID값으로 통신한게 정상인지 확인을 하는데 ..
	 * 그 값이 바뀌게 되면, 다른 class 로 인식을 해버리게 된다.
	 * 이를 방지하기 위해서 고유값으로 미리 명시를 해주는 부분이 바로 serialVersionUID임.
	 */
	private static final long serialVersionUID = 1L;


	private User user;

	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴한다.
	// true면 만료안됨.
	// false면 계정 만료된 유저.
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정 잠김여부 확인(true면 사용가능)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	// 비밀번호 만료 여부를 알려준다.
	// true면 사용가능
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	// 계정 활성화 여부
	// true면 사용가능, false면 로그인 불가.
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	// 계정의 권한을 반환한다.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collectors = new ArrayList<GrantedAuthority>(); // 이 collection에 사용자의 계정을 넣어 리턴
//		collectors.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				// "ROLE_" 스프링 시큐리티 규칠( 꼭 넣기)
//				// "ROLE_USER", "ROLE_ADMIN"
//				return "ROLE_" + user.getRole();
//			}
//		});
		collectors.add(() -> {
			return "ROLE_"+user.getRole();
			
		});
		return collectors;
	}
	
}


package com.tencoding.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tencoding.blog.auth.PrincipalDetailService;

@Configuration // 빈 등록하기(스프링컨테이너 관리하는 메모리, IOC하는 거임)
@EnableWebSecurity //이 클래스를 시큐리티 필터로 만들기 위한 주석. 필터를 커스텀하기 위해서 재정의 하는 거임
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면(post) 권한 및 인증 처리를 미리 체크하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	// 1. 비밀번호 해시처리
	// 스프링프레임워크에서 많이 쓰는 해시함수 클래스인 BCryptPasswordEncoder
	
	@Bean // IOC가 된다. 필요할 때 가져와서 사용하면 된다. 필요할 때 Autowired로 DI하면된다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
		//security할 때, 해시암호화되어야 디비에 저장할 수 있는데, 이 함수가 암호화처리해줌.
		// 사용자로부터 패스워드 받아서 암호화해서 디비에 저장하기 전에 해시암호화 해준다.
	}
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	// 2. 특정 주소 필터를 설정할 예정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//configure(HttpSecurity http)하면 모든 녀석들 다 갖고옴. 필터를 커스텀하기 위해서 재정의하는 거다 여기서.
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/auth/**", "/", "/js/**", "/css/**", "/image/**") //auth뒤에 달고오는 애들은 다 허용해 줄거다. 이거 지정안해주면 static도 안된다.
		.permitAll() // ㄴ위의 경로들은 접근을 허용한다.
		.anyRequest() // 다른 모든 요청들은
		.authenticated() // 인증이 되어야 들어갈 수 있다.
	.and() // 그리고
		.formLogin() //로그인폼은
		.loginPage("/auth/login_form"); // 로그인 페이지를 우리가 만든 페이지로 등록한다. security가 제공하는 거 말고.
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 1. userDetailsService 들어갈 오브젝트 만들어줘야 한다.
		// 2. passwordEncoder에 우리가 사용하는 해시함수 알려줘야 한다.
		// 디비에 사용자가 있는지 확인하고, 해시 알고리즘 처리를 해서 비번 맞는지 확인해줌.
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}

}

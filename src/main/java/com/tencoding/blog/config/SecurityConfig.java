package com.tencoding.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

import com.tencoding.blog.auth.PrincipalDetailService;

@Configuration // 빈 등록하기(스프링컨테이너 관리하는 메모리. IOC하는 거임!)
@EnableWebSecurity // 이 클래스를 시큐리티 필터로 만들기 위한 주석. 필터를 커스텀하기 위해서 재정의 하는 거임
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면(post) 권한 및 인증 처리를 미리 체크 하겠다. 이사람이 미리 로그인되어있는지 안되어있는지
													// 확인하고 안되어있으면 튕구고, 권한 없으면 글쓰기 못하고.
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 이거 extends해야 재정의 할 수 있다.
	// 1. 비밀번호 해시처리
	// 스프링프레임워크에서 많이 쓰는 해시함수 클래스
	@Bean // IOC가 된다.(필요할 때 가져와서 사용하면 된다. 즉, 필요할 때 Autowired로 DI하면된다.)
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder(); // 해시 암호화 되어야 디비에 저장할 수 있는데 이 함수가 암호화처리해 주는 거다.
		// 사용자로부터 패스워드 받아서 암호화 해서 디비에 저장하기 전에 처리해주는 BC어쩌고 녀석.
	}

	@Bean // 강제 IOC
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	// 필터 뜨는 시점에 manager를 메모리에 올려라.

	@Autowired
	private PrincipalDetailService principalDetailService;

//  // // <--- url
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.httpFirewall(defaultHttpFirewall());
	}

	@Bean
	public HttpFirewall defaultHttpFirewall() {
		return new DefaultHttpFirewall();
	}

	// 2. 특정 주소 필터를 설정할 예정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // HttpSecurity하면 모든 녀석들 다 갖고 옴. 커스텀 하기 위해 재정의 하는 거임 여기서.
				.authorizeRequests().antMatchers("/auth/**", "/", "/js/**", "/css/**", "/image/**")// auth뒤에 달고 오는 애들은 다
																									// 허용해줄 거다. 이거
																									// 지정안해주면 static도
																									// 안됨.
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/auth/login_form")// 위에서 허용해준 경로
																											// 말고 다른 경로로
																											// 오면,(인증된
																											// 사용자가 아니면)
																											// 로그인 페이지로
																											// 돌아가라고 처리
																											// 하는 거임.
				.loginProcessingUrl("/auth/loginProc").defaultSuccessUrl("/"); // 로그인 성공하면 대문으로 보냄

		// 스프링 시큐리티가 해당 주소로 요청이 오면, 가로채서 대신 로그인 처리를 해 준다.
		// 단, 이 동작을 완료하기 위해서는 만들어야할 클래스가 있다.
		// UserDetails type Object를 만들어야 한다.

		// 3.시큐리티가 대신 로그인을 해 주는데, password를 가로채서
		// 해당 패스워드가 무엇으로 해시 처리되었는지 함수를 알려줘야 한다.
		// 같은 해시로 암호화해서 db의 해시값과 비교할 수 있다.

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 1. userDetailService 들어갈 오브젝트 만들어줘야 한다.
		// 2. passwordEncoder에 우리가 사용하는 해시함수를 알려줘야 한다.
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
		// 디비에 사용자가 있는지 확인하고, 해시알고리즘 처리를 해서 비번맞는지 확인해줌
	}
}

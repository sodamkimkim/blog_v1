package com.tencoding.blog.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

@Configuration // 클래스를 IOC에 등록
public class WebConfig{
	// lucy라이브러리 한계있음.
	// json을 못막음. (요즘)
	// lucy는 form에서 form으로 데이터 넘기는건 잘 통함. 근데 ajax로 넘기는건 한계가 있다(비동기 post통신으로 들어오는 거는 못막는다.).
	@Bean
	public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {
		//  스프링 필터 만들 때 사용하는 FilterRegistrationBean. < >에는 다양한거 들어올 수 있다.
		// 이녀석은 메서드이기 때문에 데이터는 FilterRegistrationBean이라는 오브젝트임.
		// 쓸 때 메모리 올리려면 @Bean해줘야 함.
		// IOC떠있는걸 사용할 땐 Autowired, heap에 등록할 땐 @Bean.
		// dispatcher servlet이 component scan할 때 메모리에 올려야 겠군 하고 올림
		FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		// web.xml이 돌아간다 --> filter들이 먼저 돌아가고
		// 필터 통과하면 디스패처 서블릿이 만들어주는 스프링 컨테이너에 들어감
		// 이 필터에는 기본적으로 작동하는 것들이 있고, 필터를 추가할 수도 있다. 우리는 지금 여기서 필터 추가 하려는 거다.
		filterRegistrationBean.setFilter(new XssEscapeServletFilter()); //얘는 기본으로 설정되어 있지 않아서 필터 추가해주는 거임.
//		filterRegistrationBean.setFilter(new MyXssEscapeServletFilter()); // 재정의해줄 수 있다.
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*");//모든 url에서 이 필터를 실행시킬 거다. -> api만 먹히게 할 거면 "/api/**"
		// xml이든 자바코드든 필터 등록하는 거 똑같다.
		// 여기서는 자바코드로 등록하고 있는 거다.
		return filterRegistrationBean;
	}
	
	
	
 }

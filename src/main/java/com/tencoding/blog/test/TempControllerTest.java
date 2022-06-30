package com.tencoding.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * - RestController는 데이터를 리턴
 * - Controller는 파일을 리턴(viewResolver가 관여)
 * - viewResolver는 스프링 프레임워크에 포함되어 있는 클래스로,
 * Controller를 읽어서 src/main/resources/templates(prefix)와 mustache(suffix)사이에
 *  templates사이에서 만든 .mustache파일을 자동호출해 준다.
 * 
 * @author sodam
 *
 */
@Controller
public class TempControllerTest {	
	
	// http://localhost:9090/blog/temp/home
    @GetMapping("/temp/home")
    public String tempHome() {
    	// 파일 리턴 경로 : src/main/resources/static/home.html
    	// 리턴명 : /home.html

        System.out.println("tempHome()");
        return "/home.html"; 

    }

}
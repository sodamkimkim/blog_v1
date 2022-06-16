package com.tencoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
package com.tencoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencoding.blog.dto.KakaoProfile;
import com.tencoding.blog.dto.OAuthToken;
import com.tencoding.blog.model.User;
import com.tencoding.blog.service.UserService;

@Controller
public class UserController {
	@Autowired
	private HttpSession httpSession;

	@Autowired
	private UserService userService;

	// ../blog/user/login_form
	@GetMapping("/auth/login_form")
	public String loginForm() {
		return "user/login_form";
	}

	@GetMapping("/auth/join_form")
	public String joinForm() {
		return "user/join_form";
	}

	@GetMapping("/logout")
	public String logout() {
		// 세션정보를 제거. (로그아웃 처리)
		httpSession.invalidate();
		return "redirect:/";
	}

	@GetMapping("/user/update_form")
	public String updateForm() {
		return "user/update_form";
	}

	@PostMapping("/auth/joinProc")
	public String save(User user) { // RequestBody어노테이션 쓴ㄴ다는 것은 json으로넣겠다는 건데.
		// application/x-www-form-urlencoded;charset=UTF-8 이거는 키밸류 값으로 던져줌.
		// 여기서 회원가입기능은 자바스크립트 ajax가 아니라 form으로 데이터 주고 받을 거고
		// 이때 데이터 통신 default설정은 application/x-www-form-urlencoded;charset=UTF-8.
		// 여기에 맞춰줘야 해서 매개변수User user앞의 @RequestBody지워주겠음.

		int result = userService.saveUser(user);
		return "redirect:/";
	}

	@GetMapping("/auth/kakao/callback")
	@ResponseBody
	public String kakaoCallback(@RequestParam String code) {
		// 자바 코드로 다른 서버와 통신할 때는 HttpConnection객체 사용
		// 혹은 Retrofit2
		// 혹은 OkHttp
		// RestTemplate --> 스프링에서 많이 쓰고,, retrofit이랑 같음. 스프링부트에 내장되어있음
		RestTemplate rt = new RestTemplate();
		// http메시지 만들때 --> post방식. http header를 만들고, http body를 만들어서 통신 --> is http통신방식.
		// RestTemplate도 오브젝트를 만들어서 던져주면 됨

		// header생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// body생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "f2eb833ca286b75414d92ce28cdb554a");
		params.add("redirect_uri", "http://localhost:9090/auth/kakao/callback");
		params.add("code", code);

		// header와 body를 하나의 object로 담아야 한다.
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청 --> post방식, 응답도 받아야함
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenRequest, String.class);

		// response -> Object 타입으로 변환 (Gson, Json Simple, ObjectMapper)
        // 파싱 처리 
        OAuthToken authToken = null;
        ObjectMapper objectMapper = new ObjectMapper();
        // String ---> Object (클래스 생성)
        try {
            authToken = objectMapper.readValue(response.getBody(), OAuthToken.class);

        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 액세스 토큰 사용 
        RestTemplate rt2 = new RestTemplate(); 

        HttpHeaders headers2 = new HttpHeaders();
        // 주의 Bearer 다음에 무조건 한 칸 띄우기 !!! 
        headers2.add("Authorization", "Bearer " + authToken.getAccessToken());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // 바디 
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);

        ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class);

        System.out.println(response2);

        
        //////해보기
        
     // response -> Object 타입으로 변환 (Gson, Json Simple, ObjectMapper)
        // 파싱 처리 
        KakaoProfile kp = null;
       objectMapper = new ObjectMapper();
        // String ---> Object (클래스 생성)
        try {
        	kp = objectMapper.readValue(response.getBody(), KakaoProfile.class);

        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
        RestTemplate rt3 = new RestTemplate();
        HttpHeaders header3 = new HttpHeaders();
        header3.add("Authorization", "Bearer " + kp.get());
        
        HttpEntity<MultiValueMap<String, String>> what = new HttpEntity<>(header3);
        ResponseEntity<String> response3 = rt3.exchange("https://kapi.kakao.com/v1/user/update_profile",
        		HttpMethod.POST,
        		what,
        		String.class);
        
//		return "카카오 인가코드 요청 : " + code; // 인가code받아서 화면단에 뿌려봄. 코드 받았으니까 그다음엔 access token 받아야함.
		return "카카오 프로필정보 요청 : " + response3;
	}

}

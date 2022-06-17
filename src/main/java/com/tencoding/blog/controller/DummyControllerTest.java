package com.tencoding.blog.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.model.User;
import com.tencoding.blog.repository.UserRepository;

@RestController()
public class DummyControllerTest {
	// UserRepository 는 메모리에 올라가 있는 상태이다.
	// 그럼 어떻게 가져 오나요?--> 스프링에서는 DI하면 됨(의존성 주입)
	@Autowired // 스프링 컴포넌트 스캔할 때 자동으로 뉴 때려진다. reference가 이 변수에 담긴다.
	private UserRepository userRepository;

	@GetMapping("/dummy/users")
	public List<User> 전체사용자검색() {
		return userRepository.findAll();// findAll보면 이미 User타입 알고 있다.
	}

	// 페이징처리. 쿼리파라미터방식 사용해봄(물음표, 키 밸류 구조로 값써서 서버에 값전달). 키는 page, 값은 0
	// http://localhost:9090/blog/dummy/user?page=0
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable) { 
		// 한페이지당 값 두개, 정렬은 id, 최신값부터 들고와라.
		
//		Page<User> pageUser = userRepository.findAll(pageable);//pageable들어가는 findAll선택해주기
//		List<User> user = userRepository.findAll(pageable).getContent();

		Page<User> PageUser = userRepository.findAll(pageable);
//		Page<User> pageUser = userRepository.findAll(pageable);//pageable들어가는 findAll선택해주기
//		List<User> user = pageUser.getContent();
		return PageUser;
	}

	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// Optional
		/**
		 * null-safety. null에 안전한 데이터타입 Optional이라는 데이터타입으로 감싸서, User Entity를 가지고 오겠다.
		 */
		// get()에 들어가보면 제네릭임.
		// 컴파일 시점에서 User타입이라는 것을 알고 있음.
		// --> UserRepository,, jpa상속받았고 거기에 User를 넘겨 줬기 때문에. 컴파일 시점에서 어떤 Entity가 들어올 질
		// 알 고 있는 것임
//		User user = userRepository.findById(id).get();
//		User user = userRepository.findById(id).orElseGet(() -> {
//			return new User();
//		});
		// orElseThrow는 예외처리 해주는 거.
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 유저는 없는 사용자 입니다." + id);
		});
		////// User user = userRepository.findById(id); // 이건 안됨.User타입으로 들고오려면 제네릭 갖다주는
		////// get() 필요
		return user;
	}

	// 로그인은 겟방식 x, post방식 써야한다 privacy 때문에
	@PostMapping("/dummy/join")
	public String join(@RequestBody User user) {
		System.out.println("--------------------");
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		System.out.println("--------------------");

		System.out.println(user.getId());
		System.out.println(user.getCreateDate());
		System.out.println(user.getRole()); // null나온다. ->default 값 불가.
//		user.setRole(RoleType.USER.toString());// 데이터타입 일치화 이렇게 하거나..이넘 쓰거나
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입 완료되었습니다.";
	}



	@Transactional
	@PutMapping("/dummy/user/{id}") // put방식은 브라우저로 절대 조회할 수 없다. 탈렌트 써야한다.
	public User updateUser(@PathVariable int id, @RequestBody User reqUser) {
		System.out.println("id: " + id);
		System.out.println("password: " + reqUser.getPassword());
		System.out.println("email: " + reqUser.getEmail());
		// username값 없으면 에러난다.
		// 고로 , 먼저 select먼저 해와야 한다.
		User userEntity = userRepository.findById(id).orElseThrow(() -> { // 영속성 컨텍스트 단에서 들고온 상태. (1)
			return new IllegalArgumentException("해당 유저는 없는 사용자 입니다." + id); //e.getMessage찍어보면 이거 나옴
		});
		// --> 값 받았으니.. DB저장해야됨.
		userEntity.setPassword(reqUser.getPassword()); // (2) 여기서 값수정하면 더티
		userEntity.setEmail(reqUser.getEmail());
//		User user = userRepository.save(userEntity); //없으면 insert, 있으면 자동 update. // 더티체킹할 땐 save안쓰고 @ 트랜젝선, return  null해줌
//  return user;
		return null;
	}

	@DeleteMapping("/dummy/user/{id}") // RestAPI에서 get과 delete는 바디를 받을 수 없다.
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return "해당 유저는 없습니다. 삭제 할 수 없습니다.";
		}
		return id + "는 삭제 되었습니다.";
	}


}

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<!-- loginProc를 만들지 않음. 왜냐면 스프링 시큐리티가 가로채서 진행해 줄 것임. -->
	<form action="/auth/loginProc" method="post">
		<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"><!-- post할땐 무조건 csrf토큰 넣어야함. get방식은 상관없다. -->
		<div class="form-group">
			<label for="username">UserName:</label> <input type="text"
				class="form-control" value="222" placeholder="Enter UserName" id="username"
				name="username" />
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password"
				class="form-control" value ="222" placeholder="Enter password" id="password"
				name="password" />
		</div>
		<div class="form-group form-check">
			<label class="form-check-label"> <input
				class="form-check-input" type="checkbox" /> Remember me
			</label>
		</div>

		<!-- 자바스크립트 이벤트 바인딩으로 처리할 거라서 form밖으로 뺌 -->
		<button type="submit" id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=f2eb833ca286b75414d92ce28cdb554a&redirect_uri=http://localhost:9090/auth/kakao/callback&response_type=code">
			<img src="/image/kakao_login.png" width="74" height="38">
		</a>
	</form>

</div>
<br />
<!-- <script src="/js/user.js"></script> -->
<%@ include file="../layout/footer.jsp"%>

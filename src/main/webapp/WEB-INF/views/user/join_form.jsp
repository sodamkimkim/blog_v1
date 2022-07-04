<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form action="/auth/joinProc" method="post">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
		<div class="form-group">
			<label for="username">UserName:</label> <input type="text"
				class="form-control" placeholder="Enter UserName" id="username"
				name="username" />
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password"
				class="form-control" placeholder="Enter password" id="password"
				name="password" />
		</div>
		<div class="form-group">
			<label for="email">Email Address:</label> <input type="email"
				class="form-control" placeholder="Enter email" id="email"
				name="email" />
		</div>

		<button id="btn-save" type="submit" class="btn btn-primary">회원가입</button>
	</form>
</div>
<br />
<!-- /blog하면 자동으로 static에 먼저 간다 -->
<!-- <script src="/js/user.js"></script>-->
<!--  시큐리티. csrf에 안걸리기 위해서 js가 아니라 form 으로 값 던짐. 폼과 폼 사이에 값던지기 위해서는 타입이 submit이여야함.그리고 이 폼으로 값 던지기 위해서는 application/x-www-form 어쩌고 이 타입임.
json타입이 아니기 때문에 서버에서는 어떤 타입이 들어오냐 약속이 되어있어야함. 그래서 @Requestbody어노테이션 지워줌. 키 밸류 값던져줘야 하기 때문에 각 input태그에 name ="password"이런식으로 키 지정해줌.
이 방식으로 매핑되는 주소가 userApiController의 save()인데,, postMapping. ㅓ우리가 처음에 데이터 타입 제이슨으로 해놔서..

스프링부트의 기본 데이터 파싱전략 key=value 를 따르기 위해서 어노테이션지워줌.(@requestbody어노테이션 지우면 기본) => application/x-www즉, 키밸류 방식으로 자동 파싱처리함.
 -->
<%@ include file="../layout/footer.jsp"%>


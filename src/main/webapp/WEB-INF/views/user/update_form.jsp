<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form action="#" method="post">
		<input type="hidden" id="id" value = "${principal.user.id}">
		<div class="form-group">
			<label for="username"> username: </label> <input type="text" value="${principal.user.username}"
				name="username" id="username" class="form-control"
				readonly="readonly">
		</div>

		<div class="form-group">
			<label for="password"> password: </label> <input type="text" value=""
				name="password" id="password" class="form-control">
		</div>

		<div class="form-group">
			<label for="email"> email: </label> <input type="text" value="${principal.user.email}"
				name="email" id="email" class="form-control">
		</div>
		
		<button type = "button" class = "btn btn-primary" id = "btn-update">회원수정완료</button>
		
	</form>
</div>
<br/>
<br/>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
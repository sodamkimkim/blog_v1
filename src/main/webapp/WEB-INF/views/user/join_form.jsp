<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
	<div class="container">
		<form action="#">
		  <div class="form-group">
		    <label for="username">UserName:</label>
		    <input type="text" class="form-control" placeholder="Enter UserName" id="username" />
		  </div>
		  <div class="form-group">
		    <label for="pwd">Password:</label>
		    <input type="password" class="form-control" placeholder="Enter password" id="password" />
		  </div>
		  <div class="form-group">
		    <label for="email">Email Address:</label>
		    <input type="email" class="form-control" placeholder="Enter email" id="email" />
		  </div>
	
		  <button id="btn-save" type="button" class="btn btn-primary">회원가입</button>
		</form>
	</div>
	<br/>
<!-- /blog하면 자동으로 static에 먼저 간다 -->
<script src="/blog/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
	  <div class="form-group">
	    <label for="username">UserName:</label>
	    <input type="text" class="form-control" placeholder="Enter UserName" id="username" />
	  </div>
	  <div class="form-group">
	    <label for="pwd">Password:</label>
	    <input type="password" class="form-control" placeholder="Enter password" id="password" />
	  </div>
	  <div class="form-group form-check">
	    <label class="form-check-label">
	    	<input class="form-check-input" type="checkbox" /> Remember me </label>
	  </div>

	</form>
	<!-- 자바스크립트 이벤트 바인딩으로 처리할 거라서 form밖으로 뺌 -->
	<button type="button" id="btn-login" class="btn btn-primary">로그인</button>
	
</div>
<br/>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>

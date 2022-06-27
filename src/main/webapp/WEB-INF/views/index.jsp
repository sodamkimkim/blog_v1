<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>
	
<div class="container">
	<c:forEach var="board" items="${pageable.content}"> <!-- 이 board는 리스트. content는 boarderlist 임-->
		<div class = "card m-2">
			<div class="card-body">
				<h4 class = "card-title">${board.title}</h4>
				<a href = "#" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</c:forEach>
</div>
	
	
	<br/>
	<ul class="pagination justify-content-center">
	  <c:set var="isDisabled" value="disabled"></c:set>
	  <c:set var="isNotDisabled" value=""></c:set>
	  
	  <li class="page-item ${pageable.first ? isDisabled : isNotDisabled}">
	  	<a class="page-link" href="/?page=${pageable.number -1}">Previous</a></li> <!-- isDisabled는 c:set의 var(변수)의 value값이 들어간다. -->
	  <li class="page-item ${pageable.last ? isDisabled : isNotDisabled}"><a class="page-link" href="/?page=${pageable.number +1}">Next</a></li>
	</ul>
	<br/>
	
	<%@ include file="layout/footer.jsp" %>
	
	
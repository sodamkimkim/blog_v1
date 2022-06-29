<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<button class="btn bg-secondary" onclick="history.back();">돌아가기</button>
	<c:if test="${board.userId.id == principal.user.id}">
		<!-- el표현식. board.userId.id이렇게 쓰면 자동으로 getter setter찾아감. -->
		<a href="/board/${board.id}/update_form" class="btn btn-warning">수정</a>
		<!-- button -> a로 변경하면 get방식으로 매핑 페이지 이동이기 때문에-->
		<button class="btn btn-danger" id="btn-delete">삭제</button>
	</c:if>
	<br />
	<br />
	<div>
		글 번호 : <span id="board-id"><i>${board.id}</i></span><br /> 글 작성자 : <span
			id="board-id"><i>${board.userId.username}</i></span>
	</div>
	<div class="form-group m-2">
		<h3>${board.title}</h3>
	</div>
	<hr />
	<div class="form-group m-2">
		<h3>${board.content}</h3>
	</div>
	<br /> <br />
	<hr />

	<div class="card">
		<div>
			<div class="card-body">
				<textArea rows="1" class="form-control" id="reply-content"></textArea>
			</div>
			<div class="card-footer">
				<button type = "button" class="btn btn-primary" id="reply-save">등록</button>
			</div>
			<div></div>
		</div>
	</div>
	<br />
	<div class="card">
		<div class="card-header">댓글 목록</div>
	</div>
	<ul class = "list-group" id="reply--box">
		<c:forEach var="reply" items="${board.replys}">
			<li class = "list-group-item d-flex justify-content-between" id="reply--1">
				<div>${reply.content}</div>
				<div class = "d-flex justify-content-between">
					<div>작성자 : ${reply.user.username} &nbsp &nbsp</div> 
					<button class = "badge badge-danger">삭제</button>
				</div>
			</li>
		</c:forEach>
	</ul>
	<br/>
	<br/>


</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>

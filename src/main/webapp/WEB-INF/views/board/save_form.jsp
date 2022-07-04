<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<!-- 여기서 자바스크립트  ajax통신 하고 있기 때문에  lucy라이브러리 처리가 안된다. -->
<!-- 방어하려면 form태그 데이터통신으로 바꿔주거나 자바스크립트로 방어코드 작성해주기 -->

<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<div class="container">
	<form action="">
		<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"><!-- post할땐 무조건 csrf토큰 넣어야함. get방식은 상관없다. -->
		<div class="form-group">
			<label for="title">title</label> <input type="text"
				class="form-control" placeholder="Enter title" name="title"
				id="title">
		</div>

		<div class="form-group">
			<label for="content">content</label>
			<textarea class="form-control summernote" rows="5" id="content" name="content"></textarea>
		</div>

	</form>
		<button type="button" id="btn-save" class="btn btn-primary">글쓰기 완료</button>
</div>
<br />
<br />
	<script type = "text/javascript">
		$(".summernote").summernote({
			placeholder: '내용을 입력해 주세요',
			tabsize: 5,
			height: 300
		});
	</script>
	<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp"%>


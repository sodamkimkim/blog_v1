
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<main class="container py-5">
	<div>
		<form action="/story/image/upload" enctype="multipart/form-data"
			method=post>
			<!-- mime타입 변경됨. 동시에 문자열과 바이너리타입을 보내야 하기 때문에 이거 써야함. 반드시 method는 post.
		다른 문자같은건 문자열, 파일은 바이너리 타입으로 넘어감
		기본적인 mime타입은 application어쩌고 저쩌고 키밸류 던지는거.
		 -->
			<!-- 파일은 서버로 던질 때 문자열로 주게 되면 알아볼 수가 없다.
		원래  http통신은 buffedreader-wrter - 문자열
		파일은 바이너리.. 마임타입 중
		 -->
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}">
			<!-- csrf토큰 달아주기 -->
			<div class="input-group mt-3">
				<input type="file" name="file" class="custom-file-input"
					id="customFile" required="required"> <label
					class="custom-file-label" for="customFile"></label>
				<!-- 인풋태그로 데이터 던질 때(submit) required적어줘에 데이터 넘어간다. -->
			</div>
			<div class="input-group mt-3">
				<div class="input-group-prepend">
					<span class="input-group-text">스토리 설명</span>
				</div>
				<input type="text" name="storyText" class="form-control"
					required="required">

			</div>
			<div class="input-group mt-3">
				<button class="btn btn-info" type="submit">스토리 등록</button>
			</div>
		</form>
	</div>
</main>
<script>
	// Add the following code if you want the name of the file appear on select
	$(".custom-file-input").bind(
			"change",
			function() {
				console.log($(this).val())
				let fileName = $(this).val().split("\\").pop();
				$(this).siblings(".custom-file-label").addClass("selected")
						.html(fileName);
			});
</script>
<%@ include file="../layout/footer.jsp"%>


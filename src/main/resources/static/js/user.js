let index = {
	
	init: function() {
		$("#btn-save").bind("click", ()=>{
			this.save();
		});
	},
	
	save: function() {
		let data = {
			username: $("#username").val(), 
			password: $("#password").val(),
			email: $("#email").val()
			}
			console.log(data);
			// ajax 호출
			$.ajax({
				//서버 측에 회원가입 요청
				type: "POST",
				url: "/blog/api/user",
				data: JSON.stringify(data), // 자동으로 함수가 JSON형식으로 바뀜.
				contentType: "application/json; charset = utf-8",
				dataType: "json" // 응답이 왔을 때, 기본 데이터 타입(Buffered  문자열) => js object 자동 변환
			});
	}
	
}

index.init();

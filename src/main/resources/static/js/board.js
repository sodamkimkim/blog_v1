let index = {
	init: function() {
		$("#btn-save").bind("click",()=>{
			this.save();
		});	
	},
	save: function() {
		// 데이터 가져오기
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
			
		}
		console.log("데이터 나옴? : " +data);
		$.ajax({
			type: "POST",
			url: "/api/board", //매핑해주기 in BoardApiController
			data: JSON.stringify(data), // 보내는 데이터 타입
			contentType: "application/json; charset=utf-8",// mimetype으로 데이터 타입 알려줌
			dataType: "json" // 응답받는 데이터타입
			
		})
		.done(function(data, textStatus, xhr) {
			if(data.status){
				alert("글쓰기가 완료되었습니다.");
				 location.href="/";
			}
			
			
		}).fail(function(error){
			alert("글쓰기에 실패하였습니다.");
		});
	}
}

index.init();

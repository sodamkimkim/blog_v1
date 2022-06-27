let index = {
	init: function() {
		$("#btn-save").bind("click",()=>{
			this.save();
		});	
		$("#btn-delete").bind("click",()=>{
			this.deleteById();
		});	
		
		$("#btn-update").bind("click",()=>{
			this.update();
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
	},
	deleteById: function() {
		let id = $("#board-id").text(); // board-id의 value가 아니라 content를 들고와야 하기 때문에 .val()이 아니라 .text()
		$.ajax({
			type:"DELETE",
			url: "/api/board/"+id//data를 return이라서 api에 요청 
		}).done(function(data){
			if(data.status){
				alert("삭제가 완료되었습니다.");
				 location.href = "/";
			}
		})
		.fail(function(){
			alert("삭제에 실패하였습니다.")
		});
	},
	

	update: function(){
		let boardId = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}	
		$.ajax({
			type: "PUT",
			url: "/api/board/" + boardId,
			data: JSON.stringify(data),
			contentType:"application/json; charset=utr-8",
			dataType: "json",
			async: false
			
		}).done(function(data) {
			if(data.status) {
				alert("글 수정이 완료 되었습니다.");
				location.href = "/";
			}
		}).fail(function(error){
			alert("글 수정에 실패하였습니다.");
		});
	}
	
}

index.init();

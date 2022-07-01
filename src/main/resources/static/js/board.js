let index = {
	init: function() {
		$("#btn-save").bind("click", () => {
			this.save();
		});
		$("#btn-delete").bind("click", () => {
			this.deleteById();
		});

		$("#btn-update").bind("click", () => {
			this.update();
		});

		$("#btn-reply-save").bind("click", () => {
			this.replySave();
		});
		$("#searchBtn").bind("click", () => {
			this.searchBtnReset();
		});
	},
	save: function() {
		// 데이터 가져오기
		let data = {
			title: $("#title").val(),
			content: $("#content").val()

		}
		console.log("데이터 나옴? : " + data);
		$.ajax({
			type: "POST",
			url: "/api/board", //매핑해주기 in BoardApiController
			data: JSON.stringify(data), // 보내는 데이터 타입
			contentType: "application/json; charset=utf-8",// mimetype으로 데이터 타입 알려줌
			dataType: "json" // 응답받는 데이터타입

		})
			.done(function(data, textStatus, xhr) {
				if (data.status) {
					alert("글쓰기가 완료되었습니다.");
					location.href = "/";
				}


			}).fail(function(error) {
				alert("글쓰기에 실패하였습니다.");
			});
	},
	deleteById: function() {
		let id = $("#board-id").text(); // board-id의 value가 아니라 content를 들고와야 하기 때문에 .val()이 아니라 .text()
		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id//data를 return이라서 api에 요청 
		}).done(function(data) {
			if (data.status) {
				alert("삭제가 완료되었습니다.");
				location.href = "/";
			}
		})
			.fail(function() {
				alert("삭제에 실패하였습니다.")
			});
	},


	update: function() {
		let boardId = $("#id").val();

		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		$.ajax({
			type: "PUT",
			url: "/api/board/" + boardId,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utr-8",
			dataType: "json",
			async: false

		}).done(function(data) {
			if (data.status) {
				alert("글 수정이 완료 되었습니다.");
				location.href = "/";
			}
		}).fail(function(error) {
			alert("글 수정에 실패하였습니다.");
		});
	},

	// 댓글 등록
	replySave: function() {
		// 데이터 가져오기 (boardId: 해당게시글의 아이디)
		let data = {
			boardId: $("#board-id").text(),
			content: $("#reply-content").val()
		}

		// ``백틱(자바스크립트 변수를 문자열안에 넣어서 사용할 수 있다.)
		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`, //매핑해주기 in BoardApiController
			data: JSON.stringify(data), // 보내는 데이터 타입
			contentType: "application/json; charset=utf-8",// mimetype으로 데이터 타입 알려줌
			dataType: "json" // 응답받는 데이터타입
		})
			.done(function(response) {
				if (response.status) {
					//response - int status, T data
					console.log(response.data)
					addReplyElement(response.data, response.user);
				}

			}).fail(function(error) {
				alert("댓글작성에 실패하였습니다.");
			});
	}, // end of replySave

	replyDelete: function(boardId, replyId) {
		alert("boardId : " + boardId);
		alert("replyId : " + replyId);
		// 댓글에 대한 Id값 필요, boardID도 필요
		$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: "json"
		}).done(function(response) {
			console.log(response);
			alert("댓글 삭제 성공");
			location.href = `/board/${boardId}`;
		}).fail(function(error) {
			console.log(error);
			alert("댓글 삭제 실패");
		});

	},
	
	searchBtnReset

}

function addReplyElement(reply, userId) {
	let principalId = $("#principal--id").val();
	let childElement = `<li class = "list-group-item d-flex justify-content-between" id="reply--${reply.id}">
					<div>${reply.content}</div>
					<div class = "d-flex justify-content-between">
						<div>작성자 : ${reply.user.username} &nbsp &nbsp</div> 
						<c:if test = "${reply.user.id == principalId}">
							<button class = "badge badge-danger" onClick = "index.replyDelete(${reply.board.id},${reply.id});">삭제</button>
						</c:if>
					</div>
				</li>`;

	$("#reply--box").prepend(childElement);

}


index.init();

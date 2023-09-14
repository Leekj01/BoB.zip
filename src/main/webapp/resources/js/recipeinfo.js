function deleteComment(commentNo) {
	  if (confirm("정말로 이 댓글을 삭제하시겠습니까?")) {
	    $.ajax({
	      type: "POST", 
	      url: "${contextPath}/recipe/deleteComment", 
	      data: {
	        commentNo: commentNo 
	      },
	      success: function (response) {
	        alert("댓글이 삭제되었습니다.");
	        location.reload();
	      },
	      error: function (error) {
	    	alert("댓글 삭제에 실패했습니다. 다시 시도해주세요.");
	        console.error("댓글 삭제 실패: " + error);
	        location.reload();
	      }
	    });
	 }
}
//댓글 수정 폼 열기 함수
function openEditCommentModal(commentNo, replyComments) {
	// 기존 댓글 데이터로 수정 폼 채우기
	const editCommentText = document.getElementById('editCommentText');
	const editCommentNo = document.getElementById('editCommentNo');
	editCommentText.value = replyComments;
	editCommentNo.value = commentNo;
	
	// 댓글 수정 폼 표시하기
	const editCommentForm = document.getElementById('editCommentForm_'+commentNo);
	editCommentForm.style.display = 'block';
}

// 댓글 수정 취소 함수
function cancelEdit(commentNo) {
	// 댓글 수정 폼 감추기
	const editCommentForm = document.getElementById('editCommentForm_'+commentNo);
	editCommentForm.style.display = 'none';
}

// 수정된 댓글 제출 함수
function editComment() {
	const editCommentText = document.getElementById('editCommentText').value;
	const editCommentNo = document.getElementById('editCommentNo').value;
	
	// 댓글 업데이트를 위한 AJAX 요청
	$.ajax({
	  type: "POST",
	  url: "${contextPath}/recipe/editComment",
	  data: {
	    commentNo: editCommentNo,
	    replyComment: editCommentText
	  },
	  success: function (response) {
	    alert("댓글이 수정되었습니다.");
	    location.reload();
	  },
	  error: function (error) {
	    alert("댓글 수정에 실패했습니다. 다시 시도해주세요.");
	    console.error("댓글 수정 실패: " + error);
	    location.reload();
	  }
	});
}
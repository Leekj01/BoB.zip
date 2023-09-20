<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	 isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://kit.fontawesome.com/3e352a9905.js" crossorigin="anonymous"></script>
<script>
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

//댓글 수정 취소 함수
function cancelEdit(commentNo) {
	// 댓글 수정 폼 감추기
	const editCommentForm = document.getElementById('editCommentForm_'+commentNo);
	editCommentForm.style.display = 'none';
}

//수정된 댓글 제출 함수
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
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/recipeInfo.css">
<%@ include file="/WEB-INF/views/common/CommonHeader.jsp" %>
<body>
	<div class="containner">
		<div class="summary">
			<div class="summaryContainner">
				<div class="thumbnail">
					<img src="${contextPath}/resources/img/recipe/${recipeSummary.image}">
				</div>
				<div class="introduce">
					<h1>${recipeSummary.recipeName}</h1>
					<p>${recipeSummary.summary}</p>
					<div class="icons">
						<div class="nation">
							<i class="fa-solid fa-earth-americas"></i>
							<p>${recipeSummary.nationName}</p>
						</div>
						<div class="level">
							<i class="fa-solid fa-dumbbell"></i>
							<p>${recipeSummary.levelName}</p>
						</div>
						<div class="level">
							<i class="fa-solid fa-utensils"></i>
							<p>${recipeSummary.typeName}</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="ingredient">
			<h3>재료</h3><br>
			<ul>
				<c:forEach items="${ingredient}" var="ingredient">
					<li>${ingredient.ingredientName}</li>
				</c:forEach>
			</ul>
		</div>
		<div class="recipeInfo">
			<h3>조리순서</h3><br>
			<c:forEach items="${recipeInfo}" var="recipe" varStatus="loop">
    			<p>${loop.index + 1}. ${recipe.cookingStep}</p>
 			</c:forEach>
		</div>
	
  		<!-- 댓글입력폼 -->
		<c:choose>
			<c:when test="${not empty memberLoggedIn}">
				<div id="replysession">
					<form action="${contextPath}/recipe/recipeInfoComments" method="post" >
					<c:forEach items="${recipeInfo}" var="recipeInfo">
					<input name="recipeId" value="${recipeInfo.recipeId}" type="hidden" />
					</c:forEach>
					<input name="memberId" value="${memberLoggedIn}" type="hidden" />
	                <input name="memberNick" value="${memberNick}" type="hidden" /><br>
	                <textarea name="replyComment" id="replyComment" rows="5" cols="50" placeholder="댓글을 입력해주세요"></textarea>
	                <input type="submit" class="submitCommentButton" value="댓글등록" />
					</form>
				</div>
			</c:when>
		</c:choose>
		<div class="commentBox">
			<div class="comment">
				<h3>댓글</h3>
				<ul>
					<c:forEach items="${recipeComment}" var="comment">
						<div class="commentContent">
							<li id="nickName"><span>${comment.memberNick}</span></li>
							<c:if test="${comment.memberId eq memberLoggedIn}">
								<button onclick="openEditCommentModal('${comment.commentNo}', '${comment.replyComment}')">수정</button>  |
								<button onclick="deleteComment('${comment.commentNo}')">삭제</button>
							</c:if>
							<li>${comment.replyComment}</li>
						</div>
						<div class="editForm">
							<li id="editCommentForm_${comment.commentNo}" style="display: none;">
			    				<form id="commentForm">
			        				<input type="hidden" id="editCommentNo" name="commentNo" value="${comment.commentNo}" />
			        				<textarea id="editCommentText" name="replyComment" rows="5" cols="50" placeholder="댓글을 수정해주세요"></textarea>
			        				<input type="button" value="수정 완료" onclick="editComment()" />
			        				<input type="button" value="취소" onclick="cancelEdit()" />
			    				</form>
							</li>
						</div>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</html>
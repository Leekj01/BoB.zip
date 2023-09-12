<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	 isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	var commentNumber = "<c:out value='${comment.commetNo}'/>";
	var replyComments = "<c:out value='${comment.replyComment}'/>";
</script>
<script type="text/javascript">
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


//댓글 수정 폼 열기
function openEditCommentModal(commentNo, replyComment) {
    // 수정 폼을 보이도록 설정
    const editCommentForm = document.getElementById(`editCommentForm_${commentNo}`);
    editCommentForm.style.display = 'block';
    
    // 댓글 번호와 내용을 폼에 설정
    const editCommentNoInput = document.getElementById('editCommentNo');
    editCommentNoInput.value = commentNo;
    
    const editCommentTextInput = document.getElementById('editCommentText');
    editCommentTextInput.value = replyComment;
}

// "취소" 버튼 클릭 시 호출되는 함수
function cancelEdit(commentNo) {
    // 수정 폼을 숨기도록 설정
    const editCommentForm = document.getElementById(`editCommentForm_${commentNo}`);
    editCommentForm.style.display = 'none';
}

// 댓글 수정
function editComment(commentNo) {
    const commentNoValue = document.getElementById('editCommentNo').value;
    const commentTextValue = document.getElementById('editCommentText').value;

    // AJAX 요청을 보냅니다.
    $.ajax({
        type: "POST",
        url: "${contextPath}/recipe/editComment", // 수정 요청을 처리할 컨트롤러 매핑 URL
        data: {
            commentNo: commentNoValue,
            replyComment: commentTextValue
        },
        success: function (response) {
            if (response.success) {
                // 수정이 성공적으로 처리되면 수정된 내용을 댓글에 반영
                const commentElement = document.getElementById(`comment_${commentNo}`);
                commentElement.innerHTML = commentTextValue;

                // 수정 폼을 감추기
                const editCommentForm = document.getElementById(`editCommentForm_${commentNo}`);
                editCommentForm.style.display = 'none';

                alert("댓글이 수정되었습니다.");
            } else {
                alert("댓글 수정에 실패했습니다. 다시 시도해주세요.");
                console.error("댓글 수정 실패: " + response.message);
            }
        },
        error: function (error) {
            alert("댓글 수정에 실패했습니다. 다시 시도해주세요.");
            console.error("댓글 수정 실패: " + error);
        }
    });
}


    
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<body>
	<c:forEach items="${ingredient}" var="ingredient">
		<p>${ingredient.ingredientName}</p>
	</c:forEach>
	<c:forEach items="${recipeInfo}" var="recipeInfo">
		<p>${recipeInfo.cookingStep}</p>
	</c:forEach>
	
	<!-- 댓글이력폼 -->
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

                <input type="submit" value="댓글등록" />
                <input type="reset" value="다시작성" />
				</form>
			</div>
		</c:when>
	</c:choose>
	<h3>댓글</h3>
		<ul>
			<c:forEach items="${recipeComment}" var="comment">
				<li>${comment.commentNo}</li>
				<li>${comment.memberNick}</li>
				<li>${comment.replyComment}</li>
				<c:if test="${comment.memberId eq memberLoggedIn}">
				<button onclick="openEditCommentModal(commentNumber,replyComments)">수정</button>
				<button onclick="deleteComment('${comment.commentNo}')">삭제</button>
				</c:if>
				
				<li id="editCommentForm_${comment.commentNo}" style="display: none;">
    				<form id="commentForm">
        				<input type="hidden" id="editCommentNo" name="commentNo" value="${comment.commentNo}" />
        				<textarea id="editCommentText" name="replyComment" rows="5" cols="50" placeholder="댓글을 수정해주세요"></textarea>
        				<input type="button" value="수정 완료" onclick="editComment()" />
        				<input type="button" value="취소" onclick="cancelEdit()" />
    				</form>
				</li>

			</c:forEach>
		</ul>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</html>
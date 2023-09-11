<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	 isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
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
				<li>${comment.memberNick}</li>
				<li>${comment.replyComment}</li>
				<c:if test="${comment.memberId eq memberLoggedIn}">
				<button onclick="updateComment">수정</button>
				<button onclick="deleteComment">삭제</button>
				</c:if>
			</c:forEach>
		</ul>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</html>
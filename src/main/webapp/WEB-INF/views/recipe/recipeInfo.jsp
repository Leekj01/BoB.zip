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
		<!-- 로그인시 댓글 입력창 -->
		<c:when test="${not empty memberLoggedIn}">
			<div id="replysession">
				<form action="${contextPath}/recipe/recipeInfoComments" method="post" >
				<input name="recipeId" value="${recipeId}" type="hidden" />
				<input name="memberId" value="${memberLoggedIn}" type="hidden" />
                <input name="memberNick" value="${memberNick}" type="hidden" /><br>
                <textarea name="replyComment" id="replyComment" rows="5" cols="50" placeholder="댓글을 입력해주세요"></textarea>

                <input type="submit" value="댓글등록" />
                <input type="reset" value="다시작성" />
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<h3>댓글</h3>
			<ul>
				<c:forEach items="${comments}" var="comment">
					<li>${comment}</li>
				</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</html>
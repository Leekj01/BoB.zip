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
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</html>
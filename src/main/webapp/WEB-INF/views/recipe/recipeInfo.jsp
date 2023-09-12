<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	 isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<script src="https://kit.fontawesome.com/3e352a9905.js" crossorigin="anonymous"></script>
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
	</div>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</html>
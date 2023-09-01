<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <h1>나의 냉장고</h1>
    <table border="1">
        <tr>
            <th>품목</th>
        </tr>
        <c:forEach var="myFridge" items="${myFridge}">
            <tr>
                <td>${myFridge.fridgeName}</td>
            </tr>
        </c:forEach>
    </table>
    <input type="button" value="재료추가">
    <form action="${contextPath}/fridge/addIngredients.do" method="post" id="ingredientsForm">
    	<c:forEach var="">
    	
    	</c:forEach>
    </form>
</body>
</html>
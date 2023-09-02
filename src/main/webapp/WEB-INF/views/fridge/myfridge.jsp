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
            <th>저장날짜</th>
            <th>저장일</th>
        </tr>
        <c:forEach var="myFridge" items="${myFridge}">
            <tr>
                <td>${myFridge.ingredientName}</td>
                <td>${myFridge.storageDate}</td>
                <td>${dDay}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

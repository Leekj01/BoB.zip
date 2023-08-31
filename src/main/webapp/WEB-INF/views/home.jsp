<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="true" %>

<html>
<head>
<c:if test='${not empty message }'>
<script>
window.onload=function()
{
  result();
}

function result(){
	alert("아이디나  비밀번호가 틀립니다. 다시 로그인해주세요");
}
</script>
</c:if>
	<title>Home</title>
</head>
<body>
	<h1>
		Hello world!  
	</h1>
	
	<P>  The time on the server is ${serverTime}. </P>
	<c:if test="${memberLoggedIn != null}">
	    <p>${memberNick}님 반갑습니다.</p>
	    <a href="${contextPath}/fridge/myFridge.do?memberId=${memberLoggedIn}">마이 냉장고</a>
	    <a href="${contextPath}/member/updateForm?memberId=${memberLoggedIn}">회원수정</a>
	    <a href="${contextPath}/member/logout.do">로그아웃</a>
    </c:if>
	<c:if test="${memberLoggedIn == null}">
		<a href="${contextPath}/member/loginForm">로그인</a>
	</c:if>
</body>
</html>

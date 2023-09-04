
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/header.css">
	<script type="text/javascript" src="${contextPath}/resources/js/header.js"></script>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<header id="top">
        <div class="container">
            <div class="logo">
                <a href=""><img src="img/logo-baikal.png" alt=""></a>
            </div>
            <div class="nav">
                <ul class="menu">
                	<c:if test="${memberLoggedIn != null}">
	                    <li>
	                        <a href="#">Homes</a>
	                    </li>
	                    <li>
	                        <a href="${contextPath}/fridge/myFridgeForm">MyFridge</a>
	                    </li>
	                    <li>
	                        <a href="${contextPath}/member/updateForm?memberId=${memberLoggedIn}">Edit Profile</a>
	                    </li>
	                    <li>
	                        <a href="${contextPath}/member/logout.do">Logout</a>
	                    </li>
                    </c:if>
                    <c:if test="${memberLoggedIn == null}">
                    	<a href="${contextPath}/member/loginForm">Login</a>
                    </c:if>
                </ul>
            </div>
        </div>
    </header>
</body>
</html>
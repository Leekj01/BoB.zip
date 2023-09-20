
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/footer.css">
	<meta charset="UTF-8">
	<title>Insert title here</title>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section id="icons">
        <div class="container">
            <a href="#"><i class="fa-brands fa-twitter"></i></a>
            <a href="#"><i class="fa-brands fa-instagram"></i></a>
            <a href="#"><i class="fa-brands fa-github"></i></a>
            <a href="#"><i class="fa-brands fa-youtube"></i></a>
        </div>
    </section>
    <footer>
        <div class="footer">
	        <div class="logo">
	        	<img src="${contextPath}/resources/img/white_logo.png">
	        </div>
	        <div class="content">
	        	<br>
	        	<h2>BoB.zip</h2>
	        	<br>
	        	<h5>Phone : 01-1234-5678</h5>
            	<h5>Location : 459 BROADWAY, NEW YORK(212) 555-0123</h5>
            	<h5>Email : BoBzip@GMAIL.COM</h5>
            	<h5>© 1996-2023, BoBzip.com, Inc</h5>
            	<p>Powered by BoB.zip</p>
            </div>
        </div>
    </footer>
</body>
</html>
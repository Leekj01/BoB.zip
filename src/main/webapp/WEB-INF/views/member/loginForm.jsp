<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	 isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<script src="https://kit.fontawesome.com/3e352a9905.js" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/loginForm.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
</head>

<body>
	<div class="containner">
		<div class="loginImage">
			<img src="${contextPath}/resources/img/loginImage.jpg">
		</div>
		<div class="loginForm">
			<a href="${contextPath}/"><img src="${contextPath}/resources/img/reallogo.png"></a>
			<H3>Log in</H3>
			<form action="${contextPath}/member/login.do" method="post">
				<TABLE>
					<TBODY>
						<TR class="dot_line">
							
							<TD><input name="memberId" type="text" size="20" placeholder="ID" /></TD>
						</TR>
						<TR class="solid_line">
							
							<TD><input name="memberPwd" type="password" size="20" placeholder="PASSWORD" /></TD>
						</TR>
					</TBODY>
				</TABLE>
				<br><br>
				<INPUT	type="submit" value="로그인"> 
				<Br><br>
				   <span>  회원이 아니신가요? </span><a href="${contextPath}/member/addMemberForm">회원가입</a>   
			</form>
		</div>
	</div>		
</body>
</html>
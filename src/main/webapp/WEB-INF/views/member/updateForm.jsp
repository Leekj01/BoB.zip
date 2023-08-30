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
<body>
<h3>회원정보</h3>
	<form action="${contextPath}/member/updateMember.do" method="post">	
	<div id="detail_table">
		<table>
			<tbody>
				<tr class="dot_line">
					<td class="fixed_join">아이디</td>
					<td>
					  <input type="text" name="member_Id"  id="_member_id" value="${memberInfo.getMemberId()}" size="20" maxlength=30 disabled/>
					  <input type="hidden" name="memberId"  id="member_id" value="${memberInfo.getMemberId()}"/>
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">비밀번호</td>
					<td><input name="memberPwd" type="password" value="${memberInfo.getMemberPwd()}" size="20" maxlength=30/></td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">별명</td>
					<td><input name="memberNick" type="text" value="${memberInfo.getMemberNick()}" size="20" maxlength=20/></td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">휴대폰번호</td>
					<td><input size="5px" type="text" name="phone1" value="010" maxlength=3> 
					- <input size="10px"  type="text" name="phone2" value="${memberInfo.getPhone2()}" maxlength=4> 
					- <input size="10px"  type="text"name="phone3"  value="${memberInfo.getPhone3()}" maxlength=4></td> 
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">이메일<br>(e-mail)</td>
					<td><input size="10px"   type="text" name="email1" value="${memberInfo.getEmail1()}"/> 
					@ <input  size="10px"  type="text"name="email2" value="${memberInfo.getEmail2()}"/> 
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<div class="clear">
		<br><br>
		<table align=center>
		<tr >
			<td >
				<input type="submit"  value="회원정보 수정">
			</td>
		</tr>
	</table>
	</div>
</form>	
</body>
</html>
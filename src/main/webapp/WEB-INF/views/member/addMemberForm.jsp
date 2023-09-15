<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	 isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<script src="https://kit.fontawesome.com/3e352a9905.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
function fn_overlapped(){
    var _id=$("#_member_id").val();
    if(_id==''){
   	 alert("ID를 입력하세요");
   	 return;
    }
    $.ajax({
       type:"post",
       async:false,  
       url:"${contextPath}/member/overlapped.do",
       dataType:"text",
       data: {memberId:_id},
       success:function (data,textStatus){
          if(data=='false'){
       	    alert("사용할 수 있는 ID입니다.");
       	    $('#member_id').val(_id);
          }else{
        	alert("사용할 수 없는 ID입니다.");
          }
       },
       error:function(data,textStatus){
          alert("에러가 발생했습니다.");ㅣ
       },
       complete:function(data,textStatus){
          //alert("작업을완료 했습니다");
       }
    });  //end ajax	 
 }	
</script>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/addMemberForm.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="containner">
		<div class="signinImage">
			<img src="${contextPath}/resources/img/signInForm.jpg">
		</div>
		<div class="signinForm">
		<a href="${contextPath}/"><img src="${contextPath}/resources/img/reallogo.png"></a>
		<h3>Sign in</h3>
			<form action="${contextPath}/member/addMember.do" method="post">
				<div id="detail_table">
					<table>
						<tbody>
							<tr class="dot_line">
								<td>
									<input type="text" name="memberId" id="_member_id" size="20" maxlength=30  placeholder="ID"/>
									<input type="hidden" name="member_id" id="member_id" /> <input type="button" id="btnOverlapped" value="중복체크" onClick="fn_overlapped()"  />
								</td>
							</tr>
							<tr class="dot_line">
								<td><input name="memberPwd" type="password" size="20" maxlength=30 placeholder="Password"/></td>
							</tr>
							<tr class="dot_line">
								<td><input name="memberNick" type="text" size="20" maxlength=20 placeholder="NickName" /></td>
							</tr>
							<tr class="dot_line">
								<td>
									<input size="5px" type="text" name="phone1" value="010" maxlength=3 > - 
									<input size="10px" type="text" name="phone2" maxlength=4 placeholder="Phone"> - 
									<input size="10px" type="text" name="phone3" maxlength=4 placeholder="Number"></td>
								</tr>
							<tr class="dot_line">
								<td>
									<input size="10px" type="text" name="email1"  placeholder="Email"/> @ 
									<input size="10px" type="text" name="email2" id="email_input" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="clear">
					<br>
					<table align=center>
						<tr>
							<td>
								<input type="submit" value="회원 가입"> 
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
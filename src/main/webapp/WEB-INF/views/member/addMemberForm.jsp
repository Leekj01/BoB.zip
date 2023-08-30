<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	 isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
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
       	    $('#btnOverlapped').prop("disabled", true);
       	    $('#_member_id').prop("disabled", true);
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
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>필수입력사항</h3>
	<form action="${contextPath}/member/addMember.do" method="post">	
	<div id="detail_table">
		<table>
			<tbody>
				<tr class="dot_line">
					<td class="fixed_join">아이디</td>
					<td>
					  <input type="text" name="memberId"  id="_member_id"  size="20" maxlength=30/>
					  <input type="hidden" name="member_id"  id="member_id" />
					  
					  <input type="button"  id="btnOverlapped" value="중복체크" onClick="fn_overlapped()" />
					</td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">비밀번호</td>
					<td><input name="memberPwd" type="password" size="20" maxlength=30/></td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">별명</td>
					<td><input name="memberNick" type="text" size="20" maxlength=20/></td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">휴대폰번호</td>
					<td><input size="5px" type="text" name="phone1" value="010" maxlength=3> 
					- <input size="10px"  type="text" name="phone2" maxlength=4> - <input size="10px"  type="text"name="phone3" maxlength=4></td> 
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">이메일<br>(e-mail)</td>
					<td><input size="10px"   type="text" name="email1" /> @ <input  size="10px"  type="text"name="email2" /> 
						<select name="email2" onChange=""	title="직접입력">
							<option value="non">직접입력</option>
							<option value="hanmail.net">hanmail.net</option>
							<option value="naver.com">naver.com</option>
							<option value="yahoo.co.kr">yahoo.co.kr</option>								<option value="hotmail.com">hotmail.com</option>
							<option value="paran.com">paran.com</option>
							<option value="nate.com">nate.com</option>
							<option value="google.com">google.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="empal.com">empal.com</option>
							<option value="korea.com">korea.com</option>
							<option value="freechal.com">freechal.com</option>
						</select>
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
				<input type="submit"  value="회원 가입">
				<input  type="reset"  value="다시입력">
			</td>
		</tr>
	</table>
	</div>
</form>	
</body>
</html>
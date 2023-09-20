<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	 isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<style>
#recipeForm {
	margin-top:70px;
}
</style>
<script>
function addInputButtonClicked(type) {
    var containerDiv = document.getElementById(type);
    var newInputGroup = document.createElement("div");
    newInputGroup.className = type === "ingredient" ? "ingredient-group" : "cooking-step";
    
    if (type === "ingredient") {
        var inputElement = document.createElement("input");
        inputElement.setAttribute("name", "ingredientName");
        inputElement.setAttribute("class", "form-control input-field");
        inputElement.setAttribute("placeholder", "재료를 입력해주세요");
        inputElement.setAttribute("required", true);
        
        var deleteButton = document.createElement("button");
        deleteButton.innerText = "삭제";
        deleteButton.className = "delete-button";
        deleteButton.onclick = function() {
            containerDiv.removeChild(newInputGroup);
        };
        
        newInputGroup.appendChild(inputElement);
        newInputGroup.appendChild(deleteButton);
        
    } else if (type === "cookingorder") {
        var textareaElement = document.createElement("textarea");
        textareaElement.setAttribute("name", "cookingStep");
        textareaElement.setAttribute("class", "form-control input-field");
        textareaElement.setAttribute("rows", "3");
        textareaElement.setAttribute("placeholder", "요리과정을 입력해주세요");
        textareaElement.setAttribute("required", true);
        
        var deleteButton = document.createElement("button");
        deleteButton.innerText = "삭제";
        deleteButton.className = "delete-button";
        deleteButton.onclick = function() {
            containerDiv.removeChild(newInputGroup);
        };
        
        newInputGroup.appendChild(textareaElement);
        newInputGroup.appendChild(deleteButton);
    }
    
    containerDiv.appendChild(newInputGroup);
}
</script>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/recipeUploadForm.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="containner">
		<div class="loginImage">
			<img src="${contextPath}/resources/img/loginImage.jpg">
		</div>
		<div class="loginForm" >
			<a id="img" href="${contextPath}/"><img src="${contextPath}/resources/img/reallogo.png"></a>
			<h3>Recipe Upload</h3>
			<div class="recipeForm">
				<form action="${contextPath}/recipe/recipeUpload" method="post" enctype="multipart/form-data">
					<div id="recipeHeader">
			        	<input name="memberId" type="text" value="${memberLoggedIn}" placeholder="작성자" readonly/> <br>
						<input name="recipeName" type="text" class="form-control" placeholder="요리명" aria-label="Username" aria-describedby="basic-addon1" required><br>
						<input name="summary" type="text" placeholder="요리에 대한 짧은 설명" required><br>
						<select name="typeName" placeholder="요리 캍" required>
									<option value="">카테고리</option>
									<option value="구이">구이</option>
									<option value="국">국</option>
									<option value="떡/한과">떡/한과</option>
									<option value="만두/면류">만두/면류</option>
									<option value="밑반찬/김치">밑반찬/김치</option>
									<option value="밥">밥</option>
									<option value="볶음">볶음</option>
									<option value="조림">조림</option>
									<option value="튀김/커틀릿">튀김/커틀릿</option>
								</select><br>
						<select name="nationName" required>
			  					<option value="">나라</option> 
			  					<option value="한식">한식</option> 
			  					<option value="중국">중국</option>
			  					<option value="일본">일본</option>
			  					<option value="동남아시아">동남아시아</option>
			  					<option value="서양">서양</option>
			  					<option value="이탈리아">이탈리아</option>
							  </select><br>     
			            <select name="levelName" required>
								<option value="">난이도</option> 
								<option value="초보환영">초보환영</option>
								<option value="보통">보통</option>
								<option value="중급">중급</option>
								<option value="고급">고급</option>
							  </select><br>
						<div class="imageTab">
							<label for="imageFile">요리사진:</label>
							<input type="file" name="imageFile">
						</div>
			        </div>
			        <div id="ingredient">
			        <div class="ingredient-group">
			            <input name="ingredientName" type="text" class="form-control input-field" placeholder="재료를 입력해주세요" required>
			        </div>
			    </div>
			    <button type="button" onclick="addInputButtonClicked('ingredient')">재료추가</button><br><br>
			    <div id="cookingorder">
			        <div class="cooking-step">
			            <textarea name="cookingStep" class="form-control input-field" rows="3" placeholder="요리순서를 입력해주세요" required></textarea>     
			        </div>
			    </div>
			    <button type="button" onclick="addInputButtonClicked('cookingorder')">요리순서추가</button><br>
				<input type="submit" value="레시피 등록">
			    <input type="reset" value="취소">
				</form>
			</div>
		</div>
	</div>
</body>
</html>
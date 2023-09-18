<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	 isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
#recipeForm {
	margin-top:70px;
}
</style>
<script>
function deleteComment(image,recipeId) {
	  if (confirm("이미지를 삭제하시겠습니까?")) {
	    $.ajax({
	      type: "POST", 
	      url: "${contextPath}/recipe/myrecipeImageDelete", 
	      data: {
	    	  image: image
	    	  recipeId: recipeId
	      },
	      success: function (response) {
	        alert("이미지가 삭제되었습니다. 새로운 이미지를 입력해주세요.");
	        location.reload();
	      },
	      error: function (error) {
	    	alert("이미지 삭제에 실패했습니다. 다시 시도해주세요.");
	        console.error("myrecipeupdate 이미지삭제 실패: " + error);
	        location.reload();
	      }
	    });
	  }
	}


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
        textareaElement.setAttribute("placeholder", "요리순서를 입력해주세요");
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
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div id="recipeForm" >
	<form action="${contextPath}/recipe/myrecipeUpdate" method="post" enctype="multipart/form-data">
		<div id="recipeHeader">
			<input name="recipeId" value="${recipeSummary.recipeId}" type = "hidden" />
        	작성자: <input name="memberId" value="${memberLoggedIn}" readonly/> <br>
			요리명: <input name="recipeName" type="text" class="form-control" value="${recipeSummary.recipeName}" aria-label="Username" aria-describedby="basic-addon1" required><br>
			요리소개: <input name="summary" type="text" value="${recipeSummary.summary}" required><br>
			카테고리: <select name="typeName" required>
						<option value="${recipeSummary.typeName}">${recipeSummary.typeName}</option>
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
			나라 : <select name="nationName" required>
  					<option value="${recipeSummary.nationName}">${recipeSummary.nationName}</option> 
  					<option value="한식">한식</option> 
  					<option value="중국">중국</option>
  					<option value="일본">일본</option>
  					<option value="동남아시아">동남아시아</option>
  					<option value="서양">서양</option>
  					<option value="이탈리아">이탈리아</option>
				  </select><br>     
            난이도: <select name="levelName" required>
					<option value="${recipeSummary.levelName}">${recipeSummary.levelName}</option> 
					<option value="초보환영">초보환영</option>
					<option value="보통">보통</option>
					<option value="중급">중급</option>
					<option value="고급">고급</option>
				  </select><br>
			
			현재 요리사진: ${recipeSummary.image}  <button onclick="deleteComment('${recipeSummary.image}','${recipeSummary.recipeId }')">삭제</button><br>
			요리사진: <input type="file" name="imageFile">

        </div>
        
        <div id="ingredient">
        재료
        <div class="ingredient-group">
        <c:forEach items="${ingredient}" var="ingredient">
       	<input name="ingredientName" type="text" class="form-control input-field" value="${ingredient.ingredientName}" required><br>
       	<input name="rowNumber" type="hidden" value="${ingredient.rowNumber}">
        </c:forEach><br>
        
        
        </div>
    	</div>
    	<!--  
    	<button type="button" onclick="addInputButtonClicked('ingredient')">재료추가</button><br><br>
		-->
    <div id="cookingorder">
    요리순서
        <div class="cooking-step">
        	<c:forEach items="${recipeInfo}" var="recipe" varStatus="loop">
            <textarea name="cookingStep" class="form-control input-field" rows="3" placeholder="요리순서를 입력해주세요" required>${loop.index + 1}. ${recipe.cookingStep}</textarea><br>     
            </c:forEach>
        </div>
    </div>
    <!--  
    <button type="button" onclick="addInputButtonClicked('cookingorder')">요리순서추가</button><br>
	-->
	<input type="submit" value="레시피 등록">
    <input type="reset" value="다시 작성">
    <input type="reset" value="취소">
	</form>
	</div>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</html>
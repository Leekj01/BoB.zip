<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="true" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/home.css">
<script src="https://kit.fontawesome.com/3e352a9905.js" crossorigin="anonymous"></script>
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
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<body>
	<section class="main">
        <div class="container">
            <p>배달어플 끄자!</p>
            <h1>이제 만들어먹자!</h1>
            <form id="foodSearchForm" action="${contextPath}/recipe/searchRecipe.do" method="GET">
	            <div class="search-container">
	                <input type="text" id="foodSearch" name="inputedRecipeName" placeholder="만들고 싶은 음식 레시피를 검색하세요">
	                <button type="submit" id="searchButton">검색</button>
	            </div>
        	</form>
        </div>
    </section>
    <section class="introduce">
        <div class="container">
            <div class="boot">
                <h1>레시피와 냉장고 관리를 한번에!</h1>
                <p>배달음식 규모는 26조 시대! 우리가 배달음식을 먹는동안 건강은 더 나빠졌고,<br> 
                일회용품의 사용으로 환경도 파괴되고 있습니다. 우리 밥집 서비스를 통해<br> 
                냉장고에 남는 식재료를 관리하고, 그에맞는 레시피도 검색해보세요!</p>
                </div>
            </div>
            <div id="abouts">
                <div class="about">
                    <img src="${contextPath}/resources/img/refridge.jpg" alt="">
                    <h5>냉장고 관리하기</h5>
                    <p>여러분의 냉장고에는 어떤 식재료가 있나요?<br>
                    지금 냉장고에 식재료를 업로드 해주세요. <br>
                    알맞은 레시피를 추천해드릴께요</p>
                    <c:choose>
                    	<c:when test="${memberLoggedIn != null}">
                    		<a href="${contextPath}/fridge/myFridgeForm"><p>냉장고 관리하기 ⟶</p></a>
                    	</c:when>
                    	<c:otherwise>
                    		<a href="${contextPath}/member/loginForm"><p>냉장고 관리하기 ⟶</p></a>
                    	</c:otherwise>
                    </c:choose>
                </div>
                <div class="about">
                    <img src="${contextPath}/resources/img/recipe.jpg" alt="">
                    <h5>레시피 찾기</h5>
                    <p>다양한 음식 레시피가 준비되어 있습니다. <br>
                    음식이름 입력하면 레시피를 찾아드려요 <br>
                    오늘 저녁메뉴를 밥집에서 찾아보세요!</p>
                    <a href="#"><p>레시피 검색하기 ⟶</p></a>
                </div>
            </div>
        </div>
    </section>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</html>

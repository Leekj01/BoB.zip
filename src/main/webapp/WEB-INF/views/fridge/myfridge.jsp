<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<style>
	span:hover {
		cursor:pointer;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    // 선택한 재료들을 저장할 배열
    let selectedIngredients = [];

    // 클릭한 span 요소의 텍스트 값을 배열에 추가
    function addSelectedBox(event) {
        let selectedIngredient = event.target.textContent;
        selectedIngredients.push(selectedIngredient);
        updateSelectedIngredients(); // 선택한 재료 표시 업데이트
    }

    // 선택한 재료들을 화면에 표시
    function updateSelectedIngredients() {
        let selectedIngredientsText = selectedIngredients.join(', ');
        document.getElementById('selectedIngredientsText').textContent = selectedIngredientsText;

        // 새로운 input hidden 태그 생성 및 추가
        let hiddenInput = document.createElement('input');
        hiddenInput.type = 'hidden';
        hiddenInput.name = 'selectedIngredients';
        hiddenInput.value = selectedIngredient;
        document.getElementById('form').appendChild(hiddenInput);
    }

    // 검색 필터 함수 
    function filter() {
        let search = document.getElementById("search").value.toLowerCase();
        let listInner = document.getElementsByClassName("listInner");
        for (let i = 0; i < listInner.length; i++) {
            ingredient = listInner[i].getElementsByClassName("ingredient");
            if (ingredient[0].innerHTML.toLowerCase().includes(search)) {
                listInner[i].style.display = "flex"
            } else {
                listInner[i].style.display = "none"
            }
        }
    }
</script>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
	<h1>나의 냉장고</h1>
		<table border="1">
        <tr>
            <th>품목</th>
            <th>저장날짜</th>
        </tr>
        <c:forEach var="myFridge" items="${myFridge}">
            <tr>
                <td>${myFridge.ingredientName}</td>
                <td>${myFridge.storageDate}</td>
            </tr>
        </c:forEach>
    </table>
	<div class="searchbox">
        <label>재료: <input type="text" id="search" onkeyup="filter()" placeholder="재료를 입력하세요"></label>
    </div>
    <div class="listbox">
        <c:forEach var="ingredient" items="${allIngredients}">
            <div class="listInner">
                <!-- span 클릭 시 addSelectedBox 함수 호출 -->
                <span class="ingredient" onclick="addSelectedBox(event)">${ingredient}</span>
            </div>
        </c:forEach>
    </div>
    <div class="selectedbox">
        <!-- 선택한 재료들을 화면 상단에 표시 -->
        <div>선택한 재료: <span id="selectedIngredientsText"></span></div>
        <!-- 폼 태그로 감싸기 -->
        <form id="form" action="${contextPath}/fridge/addFridge.do" method="post">
            <!-- "냉장고에 넣기" input submit으로 변경 -->
            <input type="submit" value="냉장고에 넣기">
        </form>
    </div>
</body>
</html>

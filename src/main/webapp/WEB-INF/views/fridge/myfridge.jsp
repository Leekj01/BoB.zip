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
	.listbox {
    display: flex;
    flex-wrap: wrap;
	}

	.listInner {
    width: 20%; 
    box-sizing: border-box;  
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	//선택재료 담을 배열
	let selectedIngredients = [];
	
	//재료 클릭시 hidden input 추가
	function addHiddenInput(ingredient) {
    	let hiddenInput = document.createElement('input');
    	hiddenInput.type = 'hidden';
  	  	hiddenInput.name = 'selectedIngredients';
  		hiddenInput.value = ingredient;
  		document.getElementById('form').appendChild(hiddenInput);
	}

	// 클릭한 span 요소의 텍스트 값을 배열에 추가 및 hidden input 추가
	function toggleSelectedBox(event) {
	    let selectedIngredient = event.target.textContent;
	    let index = selectedIngredients.indexOf(selectedIngredient);
	
	    if (index === -1) {
	        selectedIngredients.push(selectedIngredient); // 선택한 재료 추가
	        addHiddenInput(selectedIngredient); // hidden input 추가
	    } else {
	        selectedIngredients.splice(index, 1); // 선택한 재료 제거
	        removeHiddenInput(selectedIngredient); // hidden input 제거
	    }
	
	    updateSelectedIngredients(); // 선택한 재료 표시 업데이트
	}

	// 선택한 재료 제거
	function removeSelectedIngredient(index) {
	    let removedIngredient = selectedIngredients.splice(index, 1)[0]; // 선택한 재료 제거 및 반환
	    removeHiddenInput(removedIngredient); // hidden input 제거
	    updateSelectedIngredients(); // 선택한 재료 표시 업데이트
	}

	// hidden input 제거 함수
	function removeHiddenInput(ingredient) {
	    let inputElements = document.getElementsByName('selectedIngredients');
	    for (let i = 0; i < inputElements.length; i++) {
	        if (inputElements[i].value === ingredient) {
	            inputElements[i].remove(); // 해당 재료에 대한 hidden input 제거
	            break; // 하나만 제거하면 되므로 루프 종료
	        }
	    }
	}

	// 선택한 재료들을 화면에 표시
	function updateSelectedIngredients() {
	    let selectedIngredientsTable = document.getElementById('selectedIngredientsTable');
	    selectedIngredientsTable.innerHTML = ''; // Clear the table
	
	    for (let i = 0; i < selectedIngredients.length; i++) {
	        let row = selectedIngredientsTable.insertRow(i);
	        let cell1 = row.insertCell(0);
	        let cell2 = row.insertCell(1);
	
	        cell1.innerHTML = selectedIngredients[i];
	        cell2.innerHTML = '<span style="cursor: pointer;" onclick="removeSelectedIngredient(' + i + ')">X</span>';
	    }
	}
	// 선택한 재료 제거
    function removeSelectedIngredient(index) {
	    let removedIngredient = selectedIngredients.splice(index, 1)[0]; // 선택한 재료 제거 및 반환
	    removeHiddenInput(removedIngredient); // hidden input 제거
	    updateSelectedIngredients(); // 선택한 재료 표시 업데이트
	}
	
	// hidden input 제거 함수
    function removeHiddenInput(ingredient) {
        let inputElements = document.getElementsByName('selectedIngredients');
        for (let i = 0; i < inputElements.length; i++) {
            if (inputElements[i].value === ingredient) {
                inputElements[i].remove(); // 해당 재료에 대한 hidden input 제거
                break; // 하나만 제거하면 되므로 루프 종료
            }
        }
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
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<body>
	<h1>나의 냉장고</h1>
		<table border="1">
        <tr>
            <th>품목</th>
            <th>삭제</th>
        </tr>
        <c:forEach var="myFridge" items="${myFridge}">
            <tr>
                <td>${myFridge.ingredientName}</td>
                <td><a href="${contextPath}/fridge/deleteFridge.do?rowNumber=${myFridge.rowNumber}">X</a></td>
            </tr>
        </c:forEach>
    </table>
    <div class="selectArea" style="border: 1px solid black;">
		<div class="searchbox">
	        <label>재료: <input type="text" id="search" onkeyup="filter()" placeholder="재료를 입력하세요"></label>
	    </div>
	    <div class="selectedbox">
		    <!-- 선택한 재료들을 화면 상단에 표시 -->
		    <div>
		        선택한 재료:
		        <table id="selectedIngredientsTable" border="0"></table>
		    </div>
		    <!-- 폼 태그로 감싸기 -->
		    <form id="form" action="${contextPath}/fridge/addFridge.do" method="post">
		        <!-- span 클릭 시 input hidden 태그 추가, X 클릭시 삭제 -->
		        <input type="submit" value="냉장고에 넣기">
		    </form>
		</div>
		<div class="listbox">
		    <c:forEach var="ingredient" items="${allIngredients}">
		        <div class="listInner">
		            <!-- span 클릭 시 toggleSelectedBox 함수 호출 -->
		            <span class="ingredient" onclick="toggleSelectedBox(event)">${ingredient}</span>
		        </div>
		    </c:forEach>
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</html>

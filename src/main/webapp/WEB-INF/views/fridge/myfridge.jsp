<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
        $("#searchInput").keyup(function() {
            var filter = $(this).val().toLowerCase();
            var checkboxContainer = $("#checkboxContainer");
            var searchedContainer = $("#searchedContainer");
            
            // 검색 결과를 초기화
            searchedContainer.empty();
            
            checkboxContainer.find("label").each(function() {
                var labelText = $(this).text().toLowerCase();
                if (labelText.indexOf(filter) > -1) {
                    // 검색어와 일치하는 체크박스를 searchedContainer에 추가
                    searchedContainer.append($(this).clone());
                }
            });
            
            // checkboxContainer 숨김
            checkboxContainer.hide();
            // searchedContainer 보임
            searchedContainer.show();
        });
    });
</script>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <h1>나의 냉장고</h1>
    <table border="1">
        <tr>
            <th>품목</th>
        </tr>
        <c:forEach var="myFridge" items="${myFridge}">
            <tr>
                <td>${myFridge.fridgeName}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <form action="${contextPath}/fridge/addIngredients.do" method="post">
        <input type="text" id="searchInput" placeholder="음식 검색">
        <div id="searchedContainer"></div>
        <div id="checkboxContainer">
            <c:forEach items="${allIngredients}" var="ingredient">
                <label><input type="checkbox" name="selectedIngredients" value="${ingredient}">${ingredient}</label><br>
            </c:forEach>
        </div>
    </form>
</body>
</html>

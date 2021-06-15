<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


        <form action="/result" method="post">
            <div>
                연도 :
                <select name="deal_year">
                    <c:forEach var="year" begin="2000" end="2021">
                        <option value="${year}">${year}년</option>
                    </c:forEach>
                </select>

                월 :
                <select name="deal_month">
                    <c:forEach var="mon" begin="1" end="12">
                        <option value="${mon}">${mon}월월</option>
                   </c:forEach>
                </select>

                지역 : 대구시
                <select name="external_cd">
                    <c:forEach var="item" items="${requestScope.locationList}">
                        <option value="${item.external_cd}">${item.local_nm}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="검색">
            </div>
        </form>



    <h1>도흠쌤하이</h1>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>제품 정보 삭제</title>
</head>
<body>
    <h1>제품 정보 삭제</h1>
    <p>
        ID: ${product.id}<br>
        제품명: ${product.name}<br>
        설명: ${product.description}<br>
        가격: ${product.price}<br>
    </p>
    <p>
        삭제하시겠습니까?
    </p>
    <form action="/product" method="post">
        <input type="hidden" name="action" value="delete">
        <input type="hidden" name="id" value="${product.id}">
        <input type="submit" value="삭제">
    </form>
    <%@ include file="/footer.jsp"%>
</body>
</html>

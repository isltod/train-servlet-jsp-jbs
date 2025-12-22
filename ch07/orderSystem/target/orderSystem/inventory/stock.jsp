<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>제품 입고</title>
    <link rel="stylesheet" href="../styles/main.css" type="text/css"/>
</head>
<body>
    <h1>제품 입고</h1>
    <form action="/inventory" method="post">
        <input type="hidden" name="action" value="stock">
        <label>ID: </label>
        <input type="text" name="id" value="${inventory.id}" readonly><br>
        <label>제품명: </label>
        <input type="text" name="name" value="${inventory.name}" readonly><br>
        <label>가격: </label>
        <input type="text" name="price" value="${inventory.price}" readonly><br>
        <label>재고 수량: </label>
        <input type="text" name="quantity" value="${inventory.quantity}" readonly><br>
        <label>입고 수량: </label>
        <input type="text" name="stock" value="0"><br>
        <input type="submit" value="저장">
    </form>
    <c:import url="../footer.jsp" />
</body>
</html>
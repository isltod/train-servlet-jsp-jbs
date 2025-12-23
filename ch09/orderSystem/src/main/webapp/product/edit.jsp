<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>제품 정보 등록</title>
    <link rel="stylesheet" href="../styles/main.css" type="text/css"/>
</head>
<body>
    <h1>제품 정보 등록</h1>
    <form action="/product" method="post">
        <input type="hidden" name="action" value="save">
        <label>ID: </label>
        <input type="text" name="id" value="${product.id}" readonly><br>
        <label>제품명: </label>
        <input type="text" name="name" value="${product.name}"><br>
        <label>설명: </label>
        <input type="text" name="description" value="${product.description}"><br>
        <label>가격: </label>
        <input type="text" name="price" value="${product.price}"><br>
        <input type="submit" value="저장">
    </form>
    <c:import url="../footer.jsp" />
</body>
</html>
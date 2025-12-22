<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <title>제품 목록 조회</title>
    <link rel="stylesheet" type="text/css" href="../styles/main.css" />
</head>
<body>
    <h1>제품 목록 조회</h1>
    <table>
        <tr><th>제품명</th><th>제품 설명</th><th>가격</th></tr>
        <c:forEach var="inventory" items="${products}">
            <tr>
                <td>${inventory.name}</td>
                <td>${inventory.description}</td>
                <td>${inventory.price}</td>
                <td>
                    <a href="<c:url value='/product?action=update&id=${inventory.id}' />">
                        변경
                    </a>
                    <a href="<c:url value='/product'>
                        <c:param name="action" value='delete' />
                        <c:param name="id" value='${inventory.id}' /></c:url>">
                        삭제
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:import url="../footer.jsp"/>
</body>
</html>
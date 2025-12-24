<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <title>제품 재고 조회</title>
    <link rel="stylesheet" type="text/css" href="../styles/main.css" />
</head>
<body>
    <h1>제품 재고 조회</h1>
    <table>
        <tr><th>제품명</th><th>가격</th><th>재고 수량</th></tr>
        <c:forEach var="inventory" items="${inventories}">
            <tr>
                <td>${inventory.name}</td>
                <td>${inventory.price}</td>
                <td>${inventory.quantity}</td>
                <td>
                    <%
                        if (request.isUserInRole("ROLE_MANAGER")) {
                    %>
                    <a href="<c:url value='/inventory'>
                        <c:param name="action" value="stock"/>
                        <c:param name="id" value='${inventory.id}'/>
                    </c:url>">
                        입고
                    </a>
                    <%
                        }
                    %>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:import url="../footer.jsp"/>
</body>
</html>
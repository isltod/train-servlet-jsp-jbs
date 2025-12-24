<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>주문 상세</title>
        <link rel="stylesheet" type="text/css" href="../styles/main.css">
    </head>
    <body>
        <c:choose>
            <c:when test="${empty order}">
                <jsp:forward page="/" />
            </c:when>
            <c:otherwise>
                <h1>주문 상세</h1>
                고객명: ${order.customer.name}<br>
                주문 일자: ${order.date}<br>
                <h2>주문 현황:</h2>
                <table>
                    <tr>
                        <th>제품명</th><th>가격</th><th>주문 수량</th>
                    </tr>
<%--                    한국 통화 기호 쓸 수 있도록 구글링--%>
                    <fmt:setLocale value="ko_KR"/>
                    <c:forEach var="item" items="${order.items}">
                        <tr>
                            <td>${item.product.name}</td>
                            <td><fmt:formatNumber value="${item.product.price}" type="currency" /> 원</td>
                            <td><fmt:formatNumber value="${item.quantity}" type="number"/> 개</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>합계</td>
                        <td><fmt:formatNumber value="${item.total}" type="currency"/> 원</td>
                    </tr>
                </table>
            </c:otherwise>
        </c:choose>
        <button onclick="history.back()">뒤로 가기</button>
        <c:import url="../footer.jsp"/>
    </body>
</html>

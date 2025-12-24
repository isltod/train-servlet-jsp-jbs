<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.ordersystem.domain.Product" %>
<html>
    <head>
        <title>제품 주문</title>
        <link rel="stylesheet" type="text/css" href="../styles/main.css" />
    </head>
    <body>
        <h1>제품 선택</h1>
        <%
            List<Product> products = (List<Product>) session.getAttribute("products");
            if (products == null) {
                response.sendRedirect("/");
            } else {
        %>
        <form method="POST" action="/order">
            <input type="hidden" name="action" value="add_to_cart" />
            제품:
            <select name="product">
                <%
                    for (Product product : products) {
                        out.println("<option value='" + product.getId() + "'>" + product.getName() + "</option>");
                    }
                %>
            </select>
            수량:
            <input class="right" type="text" name="quantity" size="3" value="1" />
            <input type="submit" value="장바구니 추가"/>
        </form>
        <%
            }
        %>
        <c:choose>
            <c:when test="${fn:length(order.items) != 0}">
                <h2>장바구니:</h2>
                <table>
                    <tr>
                        <th>제품명</th><th>가격</th><th>수량</th><th></th>
                    </tr>
                    <c:forEach var="item" items="${order.items}" varStatus="status">
                        <tr>
                            <form action="/order" method="post">
                                <input type="hidden" name="action" value="remove_from_cart" />
                                <input type="hidden" name="index" value="${status.index}" />
                                <td>${item.product.name}</td>
                                <td>${item.product.price}</td>
                                <td>${item.quantity}</td>
                                <td><input type="submit" value="삭제"></td>
                            </form>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
        </c:choose>
        <form method="POST" action="/order">
            <input type="hidden" name="action" id="action" value="place_order" />
            <input type="submit" value="주문 확인">
            <input type="submit" value="취소" onclick="setActionValue('cancel_order')">
        </form>
        <c:import url="../footer.jsp"/>
        <script>
            function setActionValue(actionString) {
                let actionElement = document.getElementById("action");
                actionElement.value = actionString;
            }
        </script>
    </body>
</html>

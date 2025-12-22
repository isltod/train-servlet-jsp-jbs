<%@ page import="com.mycompany.ordersystem.domain.Order" %>
<%@ page import="com.mycompany.ordersystem.domain.OrderItem" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>주문 확인</title>
        <link rel="stylesheet" type="text/css" href="../styles/main.css" />
    </head>
    <body>
        <%
            Order order = (Order) session.getAttribute("order");
            if (order == null) response.sendRedirect("/");
        %>
        <h1>주문 확인</h1>
        고객명: <%= order.getCustomer().getName()%><br>
        고객 주소: <%= order.getCustomer().getAddress()%><br>
        고객 이메일: <%= order.getCustomer().getEmail()%><br>
        <h2>주문 현황:</h2>
        <table>
            <tr>
                <th>제품명</th><th>가격</th><th>수량</th>
            </tr>
            <%
                List<OrderItem> items = order.getItems();
                for (OrderItem item : items) {
            %>
            <tr>
                <td><%= item.getProduct().getName() %></td>
                <td><%= item.getProduct().getPrice() %> 원</td>
                <td><%= item.getQuantity() %> 개</td>
            </tr>
            <%
                }
            %>
            <tr>
                <td>합계</td>
                <td><%= order.getTotal() %> 원</td>
            </tr>
        </table>
        <form action="/order" method="post">
            <input type="hidden" name="action" id="action" value="confirm_order" />
            <input type="submit" value="주문">
            <input type="submit" value="주문 취소" onclick="setActionValue('cancel_order')">
        </form>
        <c:import url="../footer.jsp"/>
        <script>
            function setActionValue(action) {
                let actionElement = document.getElementById(action);
                actionElement.value = action;
            }
        </script>
    </body>
</html>

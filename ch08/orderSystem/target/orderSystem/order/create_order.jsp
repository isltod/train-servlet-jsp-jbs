<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.ordersystem.domain.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>주문 고객</title>
        <link rel="stylesheet" type="text/css" href="../styles/main.css" />
    </head>
    <body>
        <h1>주문 고객</h1>
        <p>고객 이름을 입력하세요: </p>
        <form action="/order" method="post">
            <input type="hidden" id="action" name="action" value="input_customer" />
            <label>고객명: </label>
            <input type="text" name="customer_name" value="${order.customer.name}" />
            <input type="submit" value="조회"/>
            <input type="submit" onclick="setActionValue('cancel_order')" value="취소">
        </form>
        <%
            List<Customer> customers = (List<Customer>) request.getAttribute("customers");
            if (customers != null && customers.size() > 0) {
        %>
        <h2>고객 목록:</h2>
        <table>
            <tr>
                <th>이름</th><th>주소</th><th>이메일</th>
            </tr>
            <%
                for (Customer customer : customers) {
            %>
            <tr>
                <td><%= customer.getName() %></td>
                <td><%= customer.getAddress() %></td>
                <td><%= customer.getEmail() %></td>
                <td>
                    <form action="/order" method="post">
                        <input type="hidden" name="customer_id" value="<%= customer.getId()%>" />
                        <input type="hidden" name="action" value="select_customer" />
                        <input type="submit" value="선택">
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            }
        %>
        <c:import url="../footer.jsp"/>
        <script>
            function setActionValue(actionString) {
                let actionElement = document.getElementById("action");
                actionElement.value = actionString;
            }
        </script>
    </body>
</html>

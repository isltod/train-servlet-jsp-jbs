<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.ordersystem.domain.Customer" %>
<!doctype html>
<html>
<head>
    <title>고객 목록 조회</title>
    <link rel="stylesheet" type="text/css" href="../styles/main.css" />
</head>
<body>
    <%
        List<Customer> customers = (List<Customer>) request.getAttribute("customers");
    %>
    <h1>고객 목록 조회</h1>
    <table>
        <tr><th>이름</th><th>주소</th><th>메일</th></tr>
        <%
            for (Customer customer : customers) {
        %>
        <tr>
            <td><%= customer.getName()%></td>
            <td><%= customer.getAddress()%></td>
            <td><%= customer.getEmail()%></td>
            <td>
                <a href="/customer?action=update&id=<%= customer.getId() %>">변경</a>
                <a href="/customer?action=delete&id=<%= customer.getId() %>">삭제</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <%@ include file="/footer.jsp"%>
</body>
</html>
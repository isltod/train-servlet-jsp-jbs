<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mycompany.ordersystem.domain.Customer" %>
<html>
<head>
    <title>고객 정보 삭제</title>
</head>
<body>
    <h1>고객 정보 삭제</h1>
    <%
        Customer customer = (Customer) request.getAttribute("customer");
    %>
    <p>
        ID: <%= customer.getId() %><br>
        고객명: <%= customer.getName() %><br>
        주소: <%= customer.getAddress() %><br>
        이메일: <%= customer.getEmail() %><br>
    </p>
    <p>
        삭제하시겠습니까?
    </p>
    <form action="/customer" method="post">
        <input type="hidden" name="action" value="delete">
        <input type="hidden" name="id" value="<%= customer.getId() %>">
        <input type="submit" value="삭제">
    </form>
    <%@ include file="/footer.jsp"%>
</body>
</html>

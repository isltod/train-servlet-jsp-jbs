<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mycompany.ordersystem.domain.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <title>고객 정보 등록</title>
    <link rel="stylesheet" href="../styles/main.css" type="text/css" />
</head>
<body>
    <%
        Customer customer = (Customer) request.getAttribute("customer");
    %>
    <h1>고객 정보 등록</h1>
    <form action="/customer" method="post">
        <input type="hidden" name="action" value="save" />
        <label>ID: </label>
        <input type="text" name="id" readonly value="<%=customer.getId()%>"/><br>
        <label>이름: </label>
        <input type="text" name="name" value="<%=customer.getName()%>"/><br>
        <label>주소: </label>
        <input type="text" name="address" value="<%=customer.getAddress()%>"/><br>
        <label>이메일: </label>
        <input type="text" name="email" value="<%=customer.getEmail()%>"/><br>
        <input type="submit" value="저장">
    </form>
    <%@ include file="../footer.jsp" %>
</body>
</html>
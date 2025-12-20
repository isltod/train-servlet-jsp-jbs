<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>고객 정보 등록</title>

</head>
<body>
    <%
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
    %>
    <h1>고객 등록 정보</h1>
    이름: <%= name%> <br/>
    주소: <%= address%> <br/>
    이메일: <%= email%>
    <%@ include file="/footer.jsp" %>
</body>
</html>
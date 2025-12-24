<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>사용자 에러</title>
        <link rel="stylesheet" type="text/css" href="../styles/main.css" />
    </head>
    <body>
        <h1>사용자 에러</h1>
        <p><%= request.getUserPrincipal().getName()%>님은 사용할 수 없습니다.</p>
        <c:import url="../footer.jsp"/>
    </body>
</html>

<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>고객 정보 등록</title>
    <link rel="stylesheet" href="../styles/main.css" type="text/css" />
</head>
<body>
    <h1>고객 정보 등록</h1>
    <form action="/result.jsp">
        <input type="hidden" name="action" value="save" />
        <label>ID: </label>
        <input type="text" name="id" readonly /><br />
        <label>이름: </label>
        <input type="text" name="name" /><br />
        <label>주소: </label>
        <input type="text" name="address" /><br />
        <label>이메일: </label>
        <input type="text" name="email" /><br />
        <input type="submit" value="저장">
    </form>
    <%@ include file="../footer.jsp" %>
</body>
</html>
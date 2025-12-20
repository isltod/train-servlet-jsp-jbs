<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>주문 예제 시스템</title>
</head>
<body>
    <h1> <%= "주문 예제 시스템" %> </h1>
    <br />
    <p>
        <h2>고객 정보 관리</h2>
        <a href="/customer?action=edit">고객 정보 입력</a><br />
        <a href="/customer?action=list">고객 정보 조회</a>
    </p>
</body>
</html>
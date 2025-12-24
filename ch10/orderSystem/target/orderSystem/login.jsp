<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>로그인</title>
        <link rel="stylesheet" type="text/css" href="styles/main.css" />
        <link rel="short icon" href="#">
    </head>
    <body>
        <h1>로그인</h1>
        <form action="j_security_check" method="post">
            <label>사용자 ID: </label>
            <input type="text" name="j_username" /><br>
            <label>비밀번호: </label>
            <input type="password" name="j_password" /><br>
            <input type="submit" value="로그인"/>
        </form>
    </body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>주문 예제 시스템</title>
    </head>
    <body>
        <h1><%= "주문 예제 시스템" %>
        </h1>
        <br>
        <p>
            <h2>고객 정보 관리</h2>
            <a href="/customer?action=edit">고객 정보 입력</a><br>
            <a href="/customer?action=list">고객 정보 조회</a>
        </p>
        <p>
            <h2>제품 정보 관리</h2>
            <a href="<c:url value='/product'><c:param name="action" value='edit' /></c:url>">
                제품 정보 입력
            </a><br>
            <a href="<c:url value='/product'><c:param name='action' value='list' /></c:url>">
                제품 정보 조회
            </a><br>
        </p>
        <p>
            <a href="<c:url value='/inventory'><c:param name='action' value='list' /></c:url>">
                재고 정보 조회
            </a>
        </p>
        <p>
            <h2>주문 관리</h2>
            <a href="<c:url value='/order'>
                    <c:param name='action' value='create_order' />
                </c:url>">
                제품 주문
            </a><br>
            <a href="<c:url value='/order'>
                    <c:param name='action' value='list_order' />
                </c:url>">
                주문 조회
            </a>
        </p>
    </body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <title>주문 목록</title>
    </head>
    <body>
        <h1>주문 목록</h1>
        <h2>주문 고객</h2>
        <p>고객 이름을 입력하세요:</p>
        <form action="order" method="post">
            <input type="hidden" name="action" value="list_order_input_customer" />
            <label>고객명: </label>
            <input type="text" name="customer_name" value="${customer.name}" />
            <input type="submit" value="조회"/>
        </form>
        <c:choose>
            <c:when test="${fn:length(customers) != 0}">
                <h2>고객 목록</h2>
                <table>
                    <tr>
                        <th>이름</th><th>주소</th><th>이메일</th>
                    </tr>
                    <c:forEach var="customer" items="${customers}">
                        <tr>
                            <td>${customer.name}</td>
                            <td>${customer.address}</td>
                            <td>${customer.email}</td>
                            <td>
                                <form action="/order" method="post">
                                    <input type="hidden" name="customer_id" value="${customer.id}" />
                                    <input type="hidden" name="action" value="list_order_select_customer" />
                                    <input type="submit" value="선택"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
        </c:choose>
        <c:choose>
            <c:when test="${fn:length(orders) != 0}">
                <table>
                    <tr>
                        <th>주문 ID</th><th>주문 일자</th><th></th>
                    </tr>
                    <c:forEach var="order" items="${orders}" varStatus="status">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.date}</td>
                            <td>
                                <form action="/order" method="post">
                                    <input type="hidden" name="order_id" value="${order.id}" />
                                    <input type="hidden" name="index" value="${status.index}" />
<%--                                    원래는 list_cancel_order 할건데, 아래 주문 상세 누르면 show_order로 바뀐다--%>
                                    <input type="hidden" name="action" id="asdf" class="action" value="list_cancel_order" />
                                    <input type="submit" value="주문 취소">
                                    <input type="submit" value="주문 상세" onclick="setActionValue('show_order')">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
        </c:choose>
        <script>
            function setActionValue(action) {
                let actionElements = document.querySelectorAll(".action");
                for (let element of actionElements) {
                    element.value = action;
                }
            }
        </script>
        <c:import url="../footer.jsp"/>
    </body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>orders</title>
</head>
<body>

<sec:authorize access="hasRole('USER')">
    <h1>YOUR CARD BALANCE IS:${user.accumulativeCard.balance}</h1>
</sec:authorize>

<table border="3">
    <thead>
    <th>DATE</th>
    <th>PRICE</th>
    <th>ITEMS</th>
    <sec:authorize access="hasRole('ADMIN')">
        <th>USER</th>
    </sec:authorize>

    </tr>
    </thead>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.date}</td>
            <td>${order.price}</td>
            <td><c:forEach items="${order.items}" var="entry">
                ${entry.key},Count: ${entry.value} items <br>
            </c:forEach></td>
            <sec:authorize access="hasRole('ADMIN')">
            <td>${order.user.username}</td>
        </sec:authorize>
        </tr>
    </c:forEach>
</table>

<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out"/>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

</body>
</html>

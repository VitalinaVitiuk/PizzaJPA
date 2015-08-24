<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>pizzas</title>
</head>
<br>

User name: ${name} </br>
User roles: ${roles} </br>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Type</th>
        <th>Price</th>
    </tr>
    </thead>

    <c:forEach var="pizza" items="${pizzas}">
        <tr>
            <td>${pizza.id}</td>
            <td>${pizza.name}</td>
            <td>${pizza.type}</td>
            <td>${pizza.price}</td>

            <td>
                <sec:authorize access="hasRole('ADMIN')">
                <form method="get" action="edit">
                    <input type="hidden" name="pizzaid" value="${pizza.id}"/>
                    <input type="submit" value="Edit"/>
                </form>
                </sec:authorize>
            </td>

        </tr>
    </c:forEach>
</table>

<sec:authorize access="hasRole('ADMIN')">
    <a href="create"> Create new pizza </a> <br/>
</sec:authorize>

<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out" />
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

</body>
</html>

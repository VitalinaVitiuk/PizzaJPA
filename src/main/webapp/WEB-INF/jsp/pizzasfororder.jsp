
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>pizzasfororder</title>
</head>
<body>
<c:set var="map" value="${sessionScope.order.items}"/>
<c:set var="price" value="${sessionScope.order.price}"/>
<table border="3">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Type</th>
        <th>Price</th>
        <th>In Order</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach var="pizza" items="${pizzas}">
        <tr>
            <td>${pizza.id}</td>
            <td>${pizza.name}</td>
            <td>${pizza.type}</td>
            <td>${pizza.price}</td>
            <td align="center">${map[pizza]}</td>
            <td>
            <form method="post" action="addpizza">
                <input type="hidden" name="pizzaid" value="${pizza.id}"/>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <input type="submit" value="ADD"/>
            </form>
        </td>
            <td>
                <form method="post" action="removepizza">
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <input type="hidden" name="pizzaid" value="${pizza.id}"/>
                    <input type="submit" value="REMOVE"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>


<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out" />
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>


<h1>TOTAL PRICE IS ${price}</h1>
<a href="save"> DONE </a><br/>
</body>
</html>


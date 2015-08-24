<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New/Update pizza</title>
    </head>
    <body>
        <form action="addnew" method="post">
                   <input type="hidden" name="id" value="${pizza.id}"/>
            Name : <input type="text" name="name" value="${pizza.name}"/></br>
            Type : <input type="text" name="type" value="${pizza.type}"/></br>
            Price : <input type="text" name="price" value="${pizza.price}"/></br>
            <input type="submit" value="Save"/></br>
            <sec:csrfInput/>
        </form>
    </body>
    
    
</html>

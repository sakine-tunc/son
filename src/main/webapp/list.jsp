<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>list</title>
</head>
<body>
<table border="1px solid red;">
    Hello, <%= session.getAttribute( "session" ) %>
    <tr>
        <th>id</th>
        <th >username</th>
        <th >password</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td><a href="/delete?id=<c:out value="${user.id}"/>">Delete</a></td>
            <td><a href="/update?id=<c:out value="${user.id}"/>">Update</a></td>
            <td><a href="/FindById?id=<c:out value="${user.id}"/>">Detail</a></td>
        </tr>
    </c:forEach>
</table>
<input type="button" value="insert" style="background-color: red" onclick="window.location.href='insert'">
</body>
</html>

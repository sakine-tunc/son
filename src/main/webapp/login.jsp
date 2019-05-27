<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<c:if test="${message}">
    <span style="color: red">${message}</span><br>
</c:if>
<form method="post">
    <label for="username">Username:</label> <input type="text" id="username" name="username"><br><br>
    <label for="password">Password:</label><input type="password" id="password" name="password"><br><br>
    <input type="submit">
</form>
</body>
</html>
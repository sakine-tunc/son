<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update</title>
</head>
<body>
<form method="post">
    <label for="username">Username:</label> <input type="text" id="username" name="username" value="${user.username}"><br>
    <label for="password">Password:</label><input type="text" id="password" name="password" value="${user.password}"><br><br>
    <input type="submit">
</form>
</body>
</html>

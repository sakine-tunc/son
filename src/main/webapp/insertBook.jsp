<%--
  Created by IntelliJ IDEA.
  User: LEGION
  Date: 3/27/2019
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>insertBook</title>
</head>
<body>
<form method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <label for="title">Title:</label> <input type="text" id="title" name="title"><br>
    <input type="submit" name="submit" value="submit">
</form>
</body>
</html>

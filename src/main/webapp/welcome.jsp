<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="request" type="com.example.bookstore.models.User"/>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h1>Welcome ${user.username}</h1>
    Hello, <%= session.getAttribute( "session" ) %>

   hii, <a href>unset session <c:remove var="session"/></a>
    xxxx:<%= session.getAttribute( "session" ) %>
</body>
</html>

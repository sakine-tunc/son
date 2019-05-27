<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Books</title>
</head>
<body>
<table border="1px solid red;">
    <tr>
        <th>id</th>
        <th >title</th>
    </tr>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td><a href="/image?id=${book.id}" target="blank"><img src="/image?id=${book.id}" width="40"></a></td>
            <td><a href="/deleteBook?id=<c:out value="${book.id}"/>">Delete</a></td>
            <td><a href="/upload">Upload</a></td>

        </tr>
    </c:forEach>
</table>
</body>
</html>

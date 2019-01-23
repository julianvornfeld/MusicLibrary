<%--
  Created by IntelliJ IDEA.
  User: vornfeld
  Date: 23.01.2019
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <%@ include file="menu.jsp" %>
    <title>Edit Genre ${genre.name}</title>
</head>
<body>
<h1>${mode} Genre ${genre.name}</h1>

<br/><br/>
<form action="/genres/edit" method="POST">
    <input type="hidden" name="mode" value="${mode}">
    <input type="hidden" name="genreId" value="${genre.id}">
    Name:<br>
    <input type="text" name="name" value="${genre.name}"><br>
    <br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>

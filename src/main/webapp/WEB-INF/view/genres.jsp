<%--
  Created by IntelliJ IDEA.
  User: vornfeld
  Date: 23.01.2019
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <%@ include file="menu.jsp" %>
    <title>Genres</title>
</head>
<body>
<h1>Genre List</h1>

<br/><br/>
<div>
    <table border="1" id="fancytable">
        <tr>
            <th>Name</th>
        </tr>
        <c:forEach  items="${genres}" var ="genre">
            <tr>
                <td><a class="table-link" href="/genres/${genre.id}">${genre.name}</a></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <form action="/genres/new">
        <input class="button-normal" type="submit" value="create Genre" />
    </form>
</div>
</body>
</html>

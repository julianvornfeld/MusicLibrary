<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="menu.jsp" %>
    <meta charset="UTF-8">
    <title>Hello World!</title>
</head>
<body>
<h1>Artist List</h1>

<br/><br/>
<div>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Founded</th>
            <th>Genre</th>
        </tr>
        <c:forEach  items="${artists}" var ="artists">
            <tr>
                <td> <a href="/artists/${artists.id}">${artists.name}</a></td>
                <td>${artists.founded}</td>
                <td> <a href="/genres/${artists.genre.id}">${artists.genre.name}</a></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <form action="/artists/new">
        <input type="submit" value="create Artist" />
    </form>
</div>
</body>
</html>
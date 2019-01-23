<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="menu.jsp" %>
    <meta charset="UTF-8">
    <title>Albums</title>
</head>
<body>
<h1>Album List</h1>

<br/><br/>
<div>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Released</th>
            <th>Artist</th>
        </tr>
        <c:forEach  items="${albums}" var ="album">
            <tr>
                <td> <a href="/albums/${album.id}">${album.name}</a></td>
                <td>${album.released}</td>
                <td><a href="/artists/${album.artist.id}">${album.artist.name}</a></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <form action="/albums/new">
        <input type="submit" value="create Album" />
    </form>
</div>
</body>
</html>
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
    <form action="/albums/filter" class="js-example-basic-multiple" method="GET">
        Artist:<br>
        <select name="artistId">
            <c:forEach items="${artists}" var ="artist">
                <option value="${artist.id}" ${(album.artist.name==artist.name)?"selected":""}>${artist.name}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Filter Albums" />
    </form>
    <br><br>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Released</th>
            <c:if test="${filter==null}">
            <th>Artist</th>
            </c:if>
        </tr>
        <c:forEach  items="${albums}" var ="album" >
            <tr>
                <td> <a href="/albums/${album.id}">${album.name}</a></td>
                <td>${album.released}</td>
                <c:if test="${filter==null}">
                    <td><a href="/artists/${album.artist.id}">${album.artist.name}</a></td>
                </c:if>
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
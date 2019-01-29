<%--
  Created by IntelliJ IDEA.
  User: vornfeld
  Date: 23.01.2019
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <%@ include file="menu.jsp" %>
    <title>${mode} Album ${album.name}</title>
</head>
<body>
<h1>${mode} Album ${album.name}</h1>

<br/><br/>

<form action="/albums/edit" class="js-example-basic-multiple" method="POST">
    <br>
    <input type="hidden" name="mode" value="${mode}">
    <input type="hidden" name="albumId" value="${album.id}">
    Name:<br>
    <input type="text" name="name" value="${album.name}"><br>
    Released:<br>
    <input type="date" name="released" value="${album.released}"><br>
    Artist:<br>
    <select name="artistId">
        <c:forEach items="${artists}" var ="artist">
            <option value="${artist.id}" ${(album.artist.name==artist.name||ArtistId==artist.id)?"selected":""}>${artist.name}</option>
        </c:forEach>
    </select>
    <br><br>
    <input type="submit" value="Submit">
</form>

</body>
</html>

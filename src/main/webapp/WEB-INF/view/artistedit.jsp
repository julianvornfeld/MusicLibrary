<%--
  Created by IntelliJ IDEA.
  User: vornfeld
  Date: 23.01.2019
  Time: 08:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <%@ include file="menu.jsp" %>
    <title>${mode} Artist ${artist.name}</title>
</head>
<body>
<h1>${mode} Artist ${artist.name}</h1>

<br/><br/>

<form action="/artists/edit" class="js-example-basic-multiple" method="POST">
    <br>
    <input type="hidden" name="mode" value="${mode}">
    <input type="hidden" name="artistId" value="${artist.id}">
    Name:<br>
    <input type="text" name="name" value="${artist.name}"><br>
    Founded:<br>
    <input type="text" name="founded" value="${artist.founded}"><br>
    Genre:<br>
    <c:forEach items="${genres}" var ="genre">
        <label for="${genre.name}">
            <input type="checkbox" name="genreIds" value="${genre.id}" ${(artist.isGenreInArtist(genre))?"checked":""}/>${genre.name}</label>
        <br>
    </c:forEach>
    <%--<select name="genreId" multiple>--%>
        <%--<c:forEach items="${genres}" var ="genres">--%>
            <%--<option value="${genres.id}" ${(genres.id==artist.genre.id)?"selected":""}  >${genres.name}</option>--%>
        <%--</c:forEach>--%>
    <%--</select>--%>
    <br><br>
    <input type="submit" value="Submit">
</form>

</body>
</html>

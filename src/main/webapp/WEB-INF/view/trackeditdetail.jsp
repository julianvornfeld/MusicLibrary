<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="menu.jsp" %>
    <meta charset="UTF-8">
    <title>Edit Track ${track.name}</title>
</head>
<body>

<br/><br/>
<h1>${track.album.name} - ${track.album.artist.name}</h1>
<br/><br/>
<form action="/tracks/savedetail/${track.id}" class="js-example-basic-multiple" method="POST">
    <input type="hidden" name="trackId" value="${track.id}">

    Nr:<br>
    <input type="number" name="nr" id="nr" value="${track.nr}"><br>
    Name:<br>
    <input type="text" name="name" id="name" value="${track.name}"><br>
    <br>

    featuring:<br>
    <c:forEach items="${artists}" var ="artist">
        <label for="${artist.name}">
            <input type="checkbox" name="artistIds" value="${artist.id}" ${(track.isCoArtist(artist))?"checked":""}/>${artist.name}</label>
        <br>
    </c:forEach>
    <input type="submit" value="Submit" />

</form>
</body>
</html>
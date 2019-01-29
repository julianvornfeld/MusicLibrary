<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="menu.jsp" %>
    <meta charset="UTF-8">
    <title>Track List</title>
</head>
<body>

<br/><br/>
<h1>${album.name} - ${album.artist.name}</h1>
<br/>
<div>
    <table border="1" id="fancytable">
        <tr>
            <th>Nr</th>
            <th>Name</th>
            <th></th>
        </tr>
        <c:forEach  items="${tracks}" var ="track">
            <c:if test="${track.album.id==album.id}">
            <tr>
                <td>${track.nr}.</td>
                <td>${track.name}

                    <c:if test="${track.getArtistString()!=''}">
                        (featuring:

                        <c:forEach  items="${track.artists}" var ="trackartists">
                            <a class="table-link" href="/artists/${trackartists.id}">${trackartists.name}</a>
                        </c:forEach>
                        )
                    </c:if>
                </td>

                <form action="/tracks/delete/${album.id}" method="POST">
                    <input type="hidden" name="albumId" value="${album.id}">
                    <input type="hidden" name="trackId" value="${track.id}">
                    <td><input class="button-delete" type="submit" value="-" /></td>
                </form>
            </tr>
            </c:if>
        </c:forEach>
        <form action="/tracks/save/${album.id}" method="POST">
            <input type="hidden" name="albumId" value="${album.id}">
            <input type="hidden" name="nr" value="${nextTrackNr}">
            <td>${nextTrackNr}</td>
            <td><input type="text" name="name" id="name" value="${artist.name}"><input class="button-add" type="submit" value="+" /></td>

        </form>
    </table>
    <br>
</div>
</body>
</html>
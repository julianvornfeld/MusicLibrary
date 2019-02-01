<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="menu.jsp" %>
    <meta charset="UTF-8">
    <title>Track List</title>
</head>
<body>
<h1>Track List</h1>

<br/><br/>
<c:forEach  items="${albums}" var ="album">
        <form action="/tracks/edit/${album.id}" method="POST">
        <input type="hidden" name="albumId" value="${album.id}">
        <h2>${album.name} - ${album.artist.name}
        <input class="button-edit" type="submit" value="" />
        </h2>
    </form>
    <br/>
    <div>
        <table border="1" id="fancytable">
            <tr>
                <th>Nr</th>
                <th>Name</th>
                <%--<th>Album</th>--%>
                <%--<th>Artist</th>--%>
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
                    <%--<td>${track.album.getName()}</td>--%>
                    <%--<td>${track.getArtistString()}</td>--%>
                </tr>
                </c:if>
            </c:forEach>
        </table>
        <br>
    </div>
</c:forEach>
</body>
</html>
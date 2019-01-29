<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="menu.jsp" %>
    <meta charset="UTF-8">
    <title>Albums of ${ArtistName}</title>
</head>
<body>
<h1>Album List</h1>

<br/><br/>
<div>
    <form action="/albums/filter" class="js-example-basic-multiple" method="GET" id="filter-form">
        Artist:<br>
        <select name="artistId" onchange="startFilter();">
            <option value="-1">All</option>
            <c:forEach items="${artists}" var ="artist">
                <option value="${artist.id}" ${(ArtistId==artist.id)?"selected":""}>${artist.name}</option>
            </c:forEach>
        </select>
    </form>
    <br><br>
    <table border="1" id="fancytable">
        <tr>
            <th>Name</th>
            <th>Released</th>
            <c:if test="${ArtistId==null}">
            <th>Artist</th>
            </c:if>
        </tr>
        <c:forEach  items="${albums}" var ="album" >
            <tr>
                <td> <a class="table-link" href="/albums/${album.id}">${album.name}</a></td>
                <td>${album.released}</td>
                <c:if test="${ArtistId==null}">
                    <td><a class="table-link" href="/artists/${album.artist.id}">${album.artist.name}</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <br>
    <form action="/albums/new">
        <input type="hidden" name="ArtistId" value="${ArtistId}">
        <input class="button-normal" type="submit" value="create Album" />
    </form>
</div>
<script>
    function startFilter() {
        document.getElementById("filter-form").submit();
    }
</script>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="menu.jsp" %>
    <meta charset="UTF-8">
    <title>Artist List</title>
</head>
<body>
<h1>Artist List</h1>

<br/><br/>
<div>
    <table border="1" id="fancytable">
        <tr>
            <th>Name</th>
            <th>Founded</th>
            <th>Genre</th>
        </tr>
        <c:forEach  items="${artists}" var ="artists">
            <tr>
                <td> <a class="table-link" href="/artists/${artists.id}">${artists.name}</a></td>
                <td>${artists.founded}</td>
                <td>
                    <c:forEach  items="${artists.genre}" var ="genre">
                        <a class="table-link" href="/genres/${genre.id}">${genre.name}</a>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <form action="/artists/new">
        <input class="button-normal" type="submit" value="create Artist" />
    </form>
</div>
</body>
</html>
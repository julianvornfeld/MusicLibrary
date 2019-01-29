<%--
  Created by IntelliJ IDEA.
  User: vornfeld
  Date: 23.01.2019
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="/css/table.css" rel="stylesheet" type="text/css">
<link href="/css/text.css" rel="stylesheet" type="text/css">
<link href="/css/menu.css" rel="stylesheet" type="text/css">
<link href="/css/links.css" rel="stylesheet" type="text/css">
<link href="/css/button.css" rel="stylesheet" type="text/css">
<ul>
    <li><a class="${(activepage=="artists")?"active":""}" href="/artists">Artists</a></li>
    <li><a class="${(activepage=="albums")?"active":""}" href="/albums">Albums</a></li>
    <li><a class="${(activepage=="tracks")?"active":""}" href="/tracks">Tracks</a></li>
    <li><a class="${(activepage=="genres")?"active":""}" href="/genres">Genres</a></li>
</ul>
<%--<a href="/artists">Artists</a>--%>
<%--<a href="/albums">Albums</a>--%>
<%--<a href="/tracks">Tracks</a>--%>
<%--<a href="/genres">Genres</a>--%>
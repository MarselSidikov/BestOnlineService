<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tommy
  Date: 21.05.2017
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Все фильмы</title>
    <meta charset="UTF-8">
</head>
<body>
    <h1>Все фильмы</h1>
    <table>
        <tr>
            <th>id</th>
            <th>название</th>
            <th>жанр</th>
            <th>страна</th>
            <th>продолжительность в мин</th>
            <th>описание</th>
            <th>дата выхода</th>
            <th>актеры</th>
            <th>режиссер</th>
            <th>изображение</th>
        </tr>
        <c:forEach items="${requestScope.films}" var="film">
            <tr>
                <td><c:out value="${film.id}"></c:out> </td>
                <td><c:out value="${film.name}"></c:out> </td>
                <td>
                    <c:forEach items="${film.genres}" var="genre">
                        <c:out value="${genre.genre}"></c:out>
                    </c:forEach>
                </td>
                <td><c:out value="${film.country}"></c:out> </td>
                <td><c:out value="${film.lasting}"></c:out> </td>
                <td><c:out value="${film.description}"></c:out> </td>
                <td><c:out value="${film.releaseDate}"></c:out> </td>
                <td>
                    <%--<c:forEach items=" ${film.actors}" var = "actor">--%>
                        <%--<c:out value="${actor.actorName}"></c:out>--%>
                    <%--</c:forEach>--%>
                </td>
                <td><c:out value="${film.producer}"></c:out> </td>
                <td><c:out value="${film.picture}"></c:out> </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

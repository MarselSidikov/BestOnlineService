<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="models.User" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<table>
    <c:forEach items="${requestScope.security}" var="user">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.password}"/></td>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.age}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
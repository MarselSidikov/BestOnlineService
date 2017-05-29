<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.ru.itis.models.Product" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/css/table-style.css"/>" rel="stylesheet">
</head>
<body>
<%--<table>--%>
<%--<% ArrayList<Product> ProductList = (ArrayList<Product>) request.getAttribute("products"); %>--%>
<%--<% for (Product product : productList) { %>--%>
<%--<tr>--%>
<%--<td><%=product.getId()%>--%>
<%--</td>--%>
<%--<td><%=product.getName()%>--%>
<%--</td>--%>
<%--<td><%=product.getprice()%>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<%}%>--%>
<%--</table>--%>
<%--<hr>--%>
<table>
    <c:forEach items="${requestScope.product}" var="product">
        <tr>
            <td><c:out value="${product.id}"/></td>
            <td><c:out value="${product.name}"/></td>
            <td><c:out value="${product.price}"/></td>
            <td><c:out value="${product.dateRelease}"/></td>
            <td><c:out value="${product.manufacturer}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Cart List</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="message" var="message" />
<fmt:bundle basename="message">
    <h1><fmt:message key="wishlist"/></h1>

<table border="1">
    <tr>
        <td><fmt:message key="foodName"/></td>
        <td><fmt:message key="foodCount"/></td>
    </tr>
    <c:forEach var="food" items="${sessionScope.wishlist}">
        <tr>
            <td><fmt:message key = "${food.key.getName()}"/></td>
            <td>${food.value}</td>
        </tr>
    </c:forEach>
</table>
    <fmt:message key = "wishListTotalPrice"/>: ${sessionScope.totalWishListPrice}<br/>
    <a href = "foodList.jsp"><fmt:message key="go-to-foodlist"/></a>
    <a href = "/pay.do"><fmt:message key="go-to-payscreen"/></a>
</fmt:bundle>

</body>
</html>


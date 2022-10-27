<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Pay Screen</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="message" var="message" />
<fmt:bundle basename="message">
    <h1><fmt:message key="payscreen"/></h1>
    <form method = "post" action = "/pay.do">
        <c:forEach var="food" items="${sessionScope.wishlist}">
            <input type = "checkbox" name="willpayfood" value="${food.key.getName()}"><fmt:message key = "${food.key.getName()}"/>
            <br />
        </c:forEach>
    <input type = "submit" value = "결제">
    </form>
    <a href = "foodList.jsp"><fmt:message key="go-to-my-wishlist"/></a>
</fmt:bundle>

</body>
</html>
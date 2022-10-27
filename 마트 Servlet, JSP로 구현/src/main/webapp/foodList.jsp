<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="message" var="message" />
<fmt:bundle basename="message">
<head>
    <title><fmt:message key = "foodList"/></title>
</head>
<body>
<table border = "1">
    <tr>
        <td><fmt:message key ="foodName"/></td>
        <td><fmt:message key = "foodCount"/></td>
        <td><fmt:message key="foodPrice"/></td>
    </tr>
<c:forEach items="${applicationScope.foodList}" var="food">
    <tr>
        <td><fmt:message key = "${food.getName()}"/></td>
        <td>${food.getCount()}</td>
        <td>${food.getPrice()}</td>
    </tr>
</c:forEach>
</table>
<form method = "post" action = "/cart.do">
    <select name = "fruits">
    <c:forEach items="${applicationScope.foodList}" var="food">
        <option value = "${food.getName()}"><fmt:message key = "${food.getName()}"/></option>
    </c:forEach>
    </select>
    <input type = "text" name = "foodCnt" />
    <button><fmt:message key = "put-in-wish-list"/></button>
</form>
</fmt:bundle>

</body>
</html>


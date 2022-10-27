<%@ page import="com.nhnacademy.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}" />
    <fmt:setBundle basename="message" var="message" />
    <fmt:bundle basename="message">
    <title>Main Page</title>
</head>
<body>
<a href="/foodList.do"><fmt:message key ="food-List-Link"/></a></br>
<a href="/cart.do"><fmt:message key ="wish-List-Link"/></a></br>
<a href = "/language.do?lang=ko"><fmt:message key ="ko"/></a>
<a href = "/language.do?lang=en"><fmt:message key ="en"/></a><br/>
<fmt:message key = "change"/>: ${sessionScope.user.getMoney()}
</fmt:bundle>
</body>
</html>

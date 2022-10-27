<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="message" var="message" />
<fmt:bundle basename="message">
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/login.do">
 <fmt:message key="doLogin"/><br />
<fmt:message key="id"/>
 <input type="text" name="id"/><br />
  <fmt:message key="pw"/><input type="password" name="password"/><br />
    <input type="submit"/>
</form>
</fmt:bundle>
</body>
</html>

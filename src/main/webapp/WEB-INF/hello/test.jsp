<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>TestDodo</title>
</head>
<body>
    <p>Hello murata</p>
    <c:set var="hello" value="${3 + 12 / 5}" />
    <c:out value="${hello}" />
</body>
</html>

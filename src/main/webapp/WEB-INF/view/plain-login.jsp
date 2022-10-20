<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dennis
  Date: 10/20/22
  Time: 12:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        .failed{
            color: red;
        }
    </style>
</head>
<body>
<form:form action="${pageContext.request.contextPath}/authenticateUser" method="post">
    <c:if test="${param.error != null}">
        <i class="failed">Sorry ! invalid username or password</i>
    </c:if>
    <p>
        User name: <input type="text" name="username">
    </p>
    <p>
        Password: <input type="password" name="password">
    </p>
    <input type="submit" value="Login">
</form:form>
</body>
</html>

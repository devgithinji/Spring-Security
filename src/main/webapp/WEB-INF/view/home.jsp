<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
    <title>Title</title>
</head>
<body>
home page
<%--display username--%>
<p>hello: <security:authentication property="principal.username"/></p>
<%--display user roles--%>
<p>Roles: <security:authentication property="principal.authorities"/></p>

<security:authorize access="hasRole('MANAGER')">
    <p>
        <a href="${pageContext.request.contextPath}/leaders">Leadership Meeting (Only for manager)</a>
    </p>
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
    <p>
        <a href="${pageContext.request.contextPath}/systems">System Control (Only for admin)</a>
    </p>
</security:authorize>
<hr>
<form:form action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="Logout">
</form:form>
</body>
</html>

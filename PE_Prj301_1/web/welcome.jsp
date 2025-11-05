<%-- 
    Document   : welcome
    Created on : Apr 26, 2025, 8:58:34 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty sessionScope.USER_INFO}">
    <c:redirect url="login.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
    </head>
    <body>
        Welcome, ${sessionScope.USER_INFO.fullName}!<br/>

        <a href="search.jsp">Click here to serarch</a><br/>


        <c:url var="logoutLink" value="MainController">
            <c:param name="action" value="Logout"/>
        </c:url>
        <a href="${logoutLink}">Click here to logout</a>
    </body>
</html>

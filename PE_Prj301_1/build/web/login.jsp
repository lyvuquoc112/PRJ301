<%-- 
    Document   : login
    Created on : Apr 26, 2025, 8:58:20 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <!--your code here-->
        <form action="MainController" method="POST">
            UserID: <input type="text" name="txtUserId" value="" required=""/><br/>
            Password: <input type="password" name="txtPassword" value="" required="" /><br/>
            <input type="submit" value="Login" name="action" /><br/><!-- comment -->
            <input type="reset" value="Rest" />
        </form>
        <c:if test="${not empty requestScope.ERROR}">
            <font color ="red"> 
            ${requestScope.ERROR}
            </font>
        </c:if>
    </body>
</html>

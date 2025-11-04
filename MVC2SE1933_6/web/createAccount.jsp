<%-- 
    Document   : createAccount
    Created on : Nov 3, 2025, 7:44:39 AM
    Author     : hanly
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %> <!--thêm thư viện-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <form action="DispatchServlet" method="POST">
            <c:set var="erros" value="${requestScope.CREATE_ERROR}"/>
            Username* <input type="text" name="txtUsername" 
                             value="${param.txtUsername}" />(6-20 chars)<br/> <!--có dấu sao là giá trị bắt buộc-->
            <c:if test="${not empty erros.usernameLenghtErr}">
                <font color ="red"><!-- comment -->
                ${erros.usernameLenghtErr}
                </font><br/>
            </c:if>
            <c:if test="${not empty erros.usernameIsExisted}">
                <font color ="red"><!-- comment -->
                ${erros.usernameIsExisted}
                </font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" />(6-30 chars)<br/>
            <c:if test="${not empty erros.passwordLenghtErr}">
                <font color ="red"><!-- comment -->
                ${erros.passwordLenghtErr}
                </font><br/>
            </c:if>
            Confirm* <input type="password" name="txtConfrim" value="" /><br/>
            <c:if test="${not empty erros.confirmLenghtErr}">
                <font color ="red"><!-- comment -->
                ${erros.confirmLenghtErr}
                </font><br/>
            </c:if>
            Full name* <input type="text" name="txtFullname" 
                              value="${param.txtFullname}" />(2-50 chars)<br/>   
            <c:if test="${not empty erros.fullNameLenghtErr}">
                <font color =" red"><!-- comment -->
                ${erros.fullNameLenghtErr}
                </font><br/>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="submit" value="Reset" />
        </form>
        <br/><!-- comment -->
       
    </body>
</html>

<%-- 
    Document   : update
    Created on : Apr 26, 2025, 8:59:02 AM
    Author     : Computing Fundamental - HCM Campus
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${empty sessionScope.USER_INFO}">
    <c:redirect url="login.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!--your code here-->
        Welcome, ${sessionScope.USER_INFO.fullName}!<br/>

        <c:set var="cur" value="${empty dto ? param : dto}"/>

        <c:if test="${not empty ERROR_MESSAGE}">
            <p style="color:red">${ERROR_MESSAGE}</p>
        </c:if>

        <form action="UpdateController" method="post">
            <input type="hidden" name="id" value="${cur.id}"/>
            <input type="hidden" name="lastValue" value="${empty SEARCH_VALUE ? param.lastValue : SEARCH_VALUE}"/>

            Name:
            <input type="text" name="name" value="${cur.name}"/>
            <c:if test="${not empty ERRORS.nameError}">
                <span style="color:red">${ERRORS.nameError}</span>
            </c:if><br/>

            Description:
            <textarea name="des">${cur.des != null ? cur.des : cur.description}</textarea>
            <c:if test="${not empty ERRORS.descriptionError}">
                <span style="color:red">${ERRORS.descriptionError}</span>
            </c:if><br/>

            Price:
            <input type="number" step="0.01" name="price" value="${cur.price}"/>
            <c:if test="${not empty ERRORS.priceError}">
                <span style="color:red">${ERRORS.priceError}</span>
            </c:if><br/>

            Size:
            <input type="text" name="size" value="${cur.size}"/>
            <c:if test="${not empty ERRORS.sizeError}">
                <span style="color:red">${ERRORS.sizeError}</span>
            </c:if><br/>

            Status:
            <label><input type="radio" name="status" value="1"
                          ${ (cur.status == '1' || cur.status == true) ? 'checked' : '' }/> In business</label>
            <label><input type="radio" name="status" value="0"
                          ${ !(cur.status == '1' || cur.status == true) ? 'checked' : '' }/> Suspended</label>
                <c:if test="${not empty ERRORS.statusError}">
                <span style="color:red">${ERRORS.statusError}</span>
            </c:if><br/><br/>

            <button type="submit">Save</button>
        </form>
    </body>
</html>

<%-- 
    Document   : search
    Created on : Oct 24, 2025, 7:38:25 PM
    Author     : hanly
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <h2>Search Page</h2>
        <form action="DispatchServlet">
            Name <input type="text" name="txtSearchValue" 
                        value="${param.txtSearchValue}" /> <br/><!-- comment -->
            <input type="submit" value="Search" name="btAction" />
        </form></br>
        <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Lastname</th>
                        <th>isAdmin</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.username}</td>
                            <td>${dto.password}</td>
                            <td>${dto.lastname}</td>
                            <td>${dto.isAdmin}</td>
                            <td>
                                <c:url var="deleteLink" value="DispatchServlet"> 
                                    <c:param name="btAction" value="Delete"/>
                                    <c:param name="pk" value="${dto.username}"/>
                                    <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                                </c:url>
                                <a href="${deleteLink}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test ="${empty result}">
            <font color ="red"> Not Matched!!! </font>
        </c:if>
    </body>
</html>

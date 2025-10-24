<%-- 
    Document   : search
    Created on : Oct 24, 2025, 4:07:24 PM
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
        </form></br><!-- comment -->
        <c:set var="result" value="${requestScope.RESULT_SEARCH}" />
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
                    <form action="DispatchServlet" method="POST">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                ${dto.username}
                                <input type="hidden" name="txtUsername" value="" />
                            </td>
                            <td>
                                ${dto.password}
                                <input type="text" name="txtPassword" value="" />
                            </td>
                            <td>${dto.lastname}</td>
                            <td>${dto.isAdimn}
                                <input type="checkbox" name="chkAdmin" value="ON"
                                       <c:if test="${dto.isAdimn}">
                                           checked="checked"
                                       </c:if>/>   
                            </td>
                            <td>
                                <c:url var="deleteLink" value="DispatchServlet">
                                    <c:param name="btAction" value="Delete"/>
                                    <c:param name="pk" value="${dto.username}" />
                                    <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                                </c:url>
                                <a href="${deleteLink}">Delete</a>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty result}">
        <font color ="red"> Not Mathced!!! </font>
    </c:if>
</body>
</html>

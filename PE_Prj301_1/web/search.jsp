<%--
    Document   : search
    Created on : Apr 26, 2025, 8:59:02 AM
    Author     : Computing Fundamental - HCM Campus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${empty sessionScope.USER_INFO}">
    <c:redirect url="login.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <!--your code here-->
        Welcome, ${sessionScope.USER_INFO.fullName}!<br/>
        <form action="MainController" method="get">
            Search value:
            <input type="text" name="txtSearchValue"
                   value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="action" />
        </form>

        <c:if test="${not empty requestScope.ERROR_MESSAGE}">
            <h2 style="color:red;">Something went wrong!</h2>
            <p>${requestScope.ERROR_MESSAGE}</p>
            <a href="MainController?btAction=Search&txtSearchValue=${param.txtSearchValue}">Back to Search</a>
        </c:if>


        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">

            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Size</th>
                            <th>Status</th>
                            <th>Update</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="MainController" method="POST">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.id}</td>
                                <td>${dto.name}</td>
                                <td>${dto.description}</td>
                                <td>${dto.price}</td>
                                <td>${dto.size}</td>
                                <td>${dto.status}</td>
                                <td>
                                    <form action="update.jsp" method="get" style="display:inline;">
                                        <input type="hidden" name="id"    value="${dto.id}"/>
                                        <input type="hidden" name="name"  value="${dto.name}"/>
                                        <input type="hidden" name="des"   value="${dto.description}"/>
                                        <input type="hidden" name="price" value="${dto.price}"/>
                                        <input type="hidden" name="size"  value="${dto.size}"/>
                                        <input type="hidden" name="status" value="${dto.status ? 1 : 0}"/>
                                        <input type="hidden" name="lastValue" value="${param.txtSearchValue}"/>
                                        <button type="submit">Update</button>
                                    </form>               
                                </td>
                            </tr>
                        </form>
                    </c:forEach>

                </tbody>
            </table>

        </c:if>

        <c:if test="${empty result}">
            <h2> 
                <font color ="red">
                No data matching the search criteria found!
                </font>
            </h2>
        </c:if>
    </c:if>


</body>
</html>

<%-- 
    Document   : search
    Created on : Oct 13, 2025, 8:51:27 AM
    Author     : hanly
--%>

<%-- <%@page import="java.util.List"%> 
<%@page import="huylvq.registration.RegistrationDTO"%> --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body> 
        
        <h1>Search Page</h1>
        <form action="DispatchServlet">
            Search value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" /> <br/>
            <input type="submit" value="Search" name="btAction" />
        </form> <br/>
        <c:set var="result" value="${requestScope.SEARCH_RESULT}"/> <%--result o page scope, do default la page scop--%>
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Full name</th>
                        <th>Role</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}                             
                            </td>
                            <td>
                                ${dto.username}     
                            </td>
                            <td> 
                                ${dto.password}
                            </td>
                            <td> 
                                ${dto.fullname}
                            </td>
                            <td> 
                                ${dto.role}
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>
                <font color ="red">
                No record is mathced!!!
                </font>
            </h2>
        </c:if>
        <%-- <form action="DispatchServlet">
            Search value <input type="text" name="txtSearchValue" 
                                value="<%= request.getParameter("txtSearchValue")%>" /> <br/>
            <input type="submit" value="Search" name="btAction" />
        </form> <br/>

        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null)
            {
                List<RegistrationDTO> result = (List<RegistrationDTO>)request.getAttribute("SEARCH_RESULT");
                if (result != null) {// reach found 
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th> 
                    <th>Password</th>
                    <th>Full name</th>
                    <th>Role</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                    String url = "DispatchServlet"
                    + "?btAction=delete"
                    + "&pk="+dto.getUsername() // sau khi xoa thi phai refers, refresh la nhac lai,add them parameter so luong
                    // tuong ung voi voi o nhap lieu cua truc nang truoc do
                    +"&lastSearchValue="+searchValue;
                %>
                <tr>
                    <td>
                      <%= ++count%>   
                    </td>
                    <td>
                     ́̃<%=dto.getUsername()%>
                    </td>
                    <td>
                    ́̃<%=dto.getPassword()%>
                    </td>
                    <td>
                    ́̃<%=dto.getFullname()%>
                    </td>
                    <td>
                    ́̃<%=dto.isRole()%>
                    </td>
                     <td>
                         <a href ="<%= url %>">Delete</a>
                    </td>
                </tr>    
                <%
                    }//traveser result
                %>
            </tbody>
        </table>

        <%        } else { // not found
        %>
        <h2>
            <font color = "red">
            No record is matched!!!!
            </font>
        </h2>    <%
                }
            }// search value must be valid
        %>--%>

    </body>
</html>

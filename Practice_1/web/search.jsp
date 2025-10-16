<%-- 
    Document   : search
    Created on : Oct 15, 2025, 4:41:22 PM
    Author     : hanly
--%>

<%@page import="java.util.List"%>
<%@page import="huylvq.registration.RegistrationDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <h1>Search page</h1>
        <form action="DispatcherServlet">
            <input type="text" name="txtsearchValue" 
                   value="<%= request.getParameter("txtsearchValue")%>" /> 
            <input type="submit" value="Search" name="btAction" />
        </form>
        <%
            String valueSearch = request.getParameter("txtsearchValue");
            if (valueSearch != null) {
                List<RegistrationDTO> dto = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                if (dto != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>NO</th>
                    <th>username</th>
                    <th>password</th>
                    <th>lastname</th>
                    <th>isAdmin</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO re : dto) {
                %>
                <tr>
                    <td><%= ++count%></td>
                    <td><%= re.getUsername()%></td>
                    <td><%= re.getPassword()%></td>
                    <td><%= re.getLastname()%></td>
                    <td><%= re.isIsAdmin()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <%
        } else {
        %><h2>
            <font color = "red">
            No matched!!!
            </font>

        </h2>

        <%
                }
            }
        %>
    </body>
</html>

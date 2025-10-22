<%-- 
    Document   : search
    Created on : Oct 19, 2025, 4:28:15 PM
    Author     : hanly
--%>

<%@page import="huylvq.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h2>Search Page</h2>
        <form action="DispatchServlet">
            Name: <input type="text" name="txtSearchValue" 
                         value="<%= request.getParameter("txtSearchValue")%>" /> 
            <input type="submit" value="Search" name="btAction" />
        </form>
        <% String valueSearch = request.getParameter("txtSearchValue");
            if (valueSearch != null) {
                List<RegistrationDTO> list = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                if (list != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>NO</th>
                    <th>username</th>
                    <th>password</th>
                    <th>lastname</th>
                    <th>isAdmin</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO res : list) {
                        String url = "DispatchServlet?"
                                + "btAction=Delete&"
                                + "pk=" + res.getUsername()
                                + "&lastValue=" + valueSearch;
                %>
                <tr>
                    <td><%= ++count%></td>
                    <td><%= res.getUsername()%></td>
                    <td><%= res.getPassword()%></td>
                    <td><%= res.getLastname()%></td>
                    <td><%= res.isIsAdmin()%></td>
                    <td> <a href="url">Delete</a> </td>
                </tr>
                <%
                    }
                %>

            </tbody>
        </table>

        <%
        } else {
        %>
        <h2> 
            <font color ="red"> NO MATCHED IS FOUND!!!</font>
        </h2>
        <%
                }
            }
        %>

    </body>
</html>

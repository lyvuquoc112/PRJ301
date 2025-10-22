<%-- 
    Document   : search.jsp
    Created on : Oct 17, 2025, 5:59:30 PM
    Author     : hanly
--%>

<%@page import="java.util.List"%>
<%@page import="huylvq.registration.RegistraionDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <h2><font color ="red">
            Welcome to DB Servlet
            </font>
        </h2>
        <form action="DispatchServlet">
            Name <input type="text" name="txtSearchValue" 
                        value="<%= request.getParameter("txtSearchValue")%>" /> <br><!-- comment -->
            <input type="submit" value="Search" name="btAction" />
        </form>
        <%
            String valueSearch = request.getParameter("txtSearchValue");
            if (valueSearch != null) {
                List<RegistraionDTO> dto = (List<RegistraionDTO>) request.getAttribute("SEARCH_RESULT");
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
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistraionDTO res : dto) {
                        String url = "DispatchServlet?"
                                + "btAction=delete&"
                                + "pk=" + res.getUsername()
                                + "&lastValue=" + valueSearch;
                %>
                <tr>
                    <td><%= ++count%></td>
                    <td><%= res.getUsername()%></td>
                    <td><%= res.getPassword()%></td>
                    <td><%= res.getLastname()%></td>
                    <td><%= res.isIsAdmin()%></td>
                    <td>Delete</td>
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
            <font color = "red">
            NO MATCHED IS FOUND!!!
            </font><!-- comment -->
        </h2>
        <%
                }
            }
        %>
    </body>
</html>

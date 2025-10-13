<%-- 
    Document   : search
    Created on : Oct 13, 2025, 8:51:27 AM
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
        <h1>Search Page</h1>
        <form action="DispatchServlet">
            Search value <input type="text" name="txtSearchValue" 
                                value="<%= request.getParameter("txtSearchValue")%>" /> <br/>
            <input type="submit" value="Search" name="btAction" />
        </form> <br/>

        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null)
            {
                List<RegistrationDTO> result = (List<RegistrationDTO>
                )request.getAttribute("SEARCH_RESULT");
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

                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
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
        %>

    </body>
</html>

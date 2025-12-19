<%-- 
    Document   : search
    Created on : Oct 13, 2025, 8:51:27 AM
    Author     : hanly
--%>

<%-- <%@page import="java.util.List"%> 
<%@page import="huylvq.registration.RegistrationDTO"%> --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty sessionScope.USERINFO}">
    <c:redirect url="login.html"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body> 
        <font color ="red">
        Weclome,${sessionScope.USERINFO.fullname} <!--USERINFO có giá trị là registrationDTO-->
        </font>

        <c:url var="logoutLink" value="DispatchServlet"> <!--tạo một đường link để logout-->
            <c:param name="btAction" value="logout"/>
        </c:url>
        <a href="${logoutLink}">Click here to Sign Out</a>

        <h1>Search Page</h1>
        <form action="DispatchServlet">
            Search value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" /> <br/>
            <input type="submit" value="Search" name="btAction" />
        </form> <br/>
        <c:set var ="searchValue" value="${param.txtSearchValue}"/> <!-- lấy value của parama -->
        <c:if test ="${not empty searchValue}"> <!-- kiểm tra xem có value không, nếu không có nghĩa là người dùng 
            không nhập gì cả, nên không làm gì hết. Việc load lại trang được thực hiện ở searchLastnameServlet rồi-->
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
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatchServlet" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}                             
                                </td>
                                <td>
                                    ${dto.username}    
                                    <input type="hidden" name="txtUsername" 
                                           value="${dto.username}"/> <!-- Để hidden thì user sẽ không thấy được,
                                    tuy rằng không thể thay đổi, nhưng lí do để bên trong là để đưa đến DB, 
                                    nếu không có primary key thì cột password và isAdmin đều sẽ bị thay đổi-->
                                </td>
                                <td> 
                                    <input type="text" name="txtPassword" 
                                           value="${dto.password}" /> <!-- ky thuat pasting control 
                                    Cai nao sua duoc thi nam trong, khong sua duoc thi nam ngoai control-->
                                </td>
                                <td> 
                                    ${dto.fullname}
                                </td>
                                <td> 
                                    <input type="checkbox" name="chkAdmin" value="ON"
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           /> 
                                    <!-- check duoc check thi param moi ton tai, 
                                    neu ko duoc check thi param khong ton tai
                                    Checkbox duoc check thi phai co checked="checked"
                                    trong database neu la true thi la checked-->
                                </td>
                                <td> 
                                    <c:url var ="deleteLink" value="DispatchServlet">
                                        <c:param name ="btAction" value ="Delete"/> <!--<!-- ctr shif mui ten len de copy va past dong do -->
                                        <c:param name ="pk" value ="${dto.username}"/>
                                        <c:param name ="lastSearchValue" value ="${param.txtSearchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td> 
                                    <input type="submit" value="Update" name="btAction" />    
                                    <input type="hidden" name="lastSearchValue" value="${param.txtSearchValue}" />
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
                No record is mathced!!!
                </font>
            </h2>
        </c:if>
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

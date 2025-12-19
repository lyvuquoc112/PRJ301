/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package huylvq.controller;

import huylvq.registration.RegistrationCreateError;
import huylvq.registration.RegistrationDAO;
import huylvq.registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hanly
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "createAccount.jsp";
    private final String LOGIN_PAGE = "login.html";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Step 1. get all user's infomation
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confrim = request.getParameter("txtConfrim");
        String fullname = request.getParameter("txtFullname");

        String url = ERROR_PAGE;
        boolean foundErr = false; // kiểm xem có lỗi không
        RegistrationCreateError errors = new RegistrationCreateError();
        try {
            // Step 2. Verify all user's erros, các chức năng khác cũng sẽ bắt lỗi nếu có
            // có nhiều lỗi thì đặt trong javabean
            if (username.trim().length() < 6
                    || username.trim().length() > 20) {
                foundErr = true;
                errors.setUsernameLenghtErr("Username is required typing from 6 to 20 characters");
                // show hết tất cả các lỗi
            }
            if (password.trim().length() < 6
                    || password.trim().length() > 30) {
                foundErr = true;
                errors.setPasswordLenghtErr("Password is required typing from 6 to 30 characters");
                // show hết tất cả các lỗi
            } else if (!confrim.trim().equals(password.trim())) {
                foundErr = true;
                errors.setConfirmLenghtErr("Confirm must match password");

            }
            if (fullname.trim().length() < 2
                    || fullname.trim().length() > 50) {
                foundErr = true;
                errors.setFullNameLenghtErr("Full name is required typing from 2 to 50 characters");
                // show hết tất cả các lỗi
            }
            if (foundErr) {
                // catching errors to attribute of request scope to show
                request.setAttribute("CREATE_ERROR", errors);
            } else {
                // Step 3. Controller call method of Model
                // Step 3.1 controller news DAO object
                RegistrationDAO dao = new RegistrationDAO();
                // Step 3.2 controller calls method of DAO's object
                RegistrationDTO account = new RegistrationDTO(username, password, fullname, false);
                boolean result = dao.createAccount(account);
                // Step 4 Process result   
                if(result){
                    url = LOGIN_PAGE;
                    request.setAttribute("CREATE_MESSAGE", "Created successfully");
                }// create account is successful
            }// no erro

        } catch (SQLException ex) {
            String errMsg = ex.getMessage();
            log("CreateNewAccountServlet_SQL" + errMsg);
            if(errMsg.contains("duplicate")){ // lỗi hệ thống thì đọc ở SQLException
                errors.setUsernameIsExisted(username +" is existed"); // đang gán lỗi cho errors object
                request.setAttribute("CREATE_ERROR", errors); // bất kỳ có sự thay đổi nào thì 
                //phải update lại attributew
            }
        } catch (ClassNotFoundException ex) {
            log("CreateNewAccountServlet_ClassNotFound" + ex.getMessage());
        } finally {
            // không dùng sendRedirect bởi nó sẽ xóa requestScope, mà chúng ta đang lưu vào requestScope
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

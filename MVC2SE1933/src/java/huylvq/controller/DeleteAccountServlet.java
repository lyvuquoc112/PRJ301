/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package huylvq.controller;

import huylvq.registration.RegistrationDAO;
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
@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "errors.html";

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
        //Servlet không được viết giao diện tĩnh
        String url = ERROR_PAGE;
        //Parameter đang ở trong request message ở container
        //Lấy dữ liệu xuống bằng name của parameter (name của parameter là kiểu String)
        // hạn chế ghi, nên copy và paste để tránh sai tên
        //Step 1. get all user's infomation
        String username = request.getParameter("pk");
        String searchValue = request.getParameter("lastSearchValue");
        try {
            //Step 2. controll call method's model
            // Step 2.1: controller news DAO object
            RegistrationDAO dao = new RegistrationDAO();
            // Step 2.2: controller calls method of DAO's object
            boolean result = dao.deleteAccount(username); // method delete nay nem ve 2 loi (SQLException
            //va ClassNotFoundException)
            // Step 3: controller process result   

            if (result) {
                // refresh --> cal previous functional again
                // -->reminded --> using url rewriting by adding number of request parameters
                // same as number of input control of previous functional 
                url = "DispatchServlet"
                        + "?btAction=Search"
                        + "&txtSearchValue="+searchValue; // All request must through via DispatchServlet
                // DispatchServlet distinct user'sactions by paramName btAction, in DispatchServlet
            }// delete is ok
        } catch (SQLException ex) { // them catch la boi bi khong duoc them tham so hay
            // exceoption
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect(url);
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

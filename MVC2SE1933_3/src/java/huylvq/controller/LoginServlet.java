/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package huylvq.controller;

import huylvqregistration.RegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hanly
 */
public class LoginServlet extends HttpServlet {
    private final String SEARCH_PAGE ="search.html";
    private final String INVALID_PAGE ="invalid.html";
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
        PrintWriter out = response.getWriter();
        String url = INVALID_PAGE;
        //Servlet không được viết giao diện tĩnh
       
        //Parameter đang ở trong request message ở container
        //Lấy dữ liệu xuống bằng name của parameter (name của parameter là kiểu String)
        // hạn chế ghi, nên copy và paste để tránh sai tên
        //Step 1. get all user's infomation
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String button = request.getParameter("btAction");
        try{
        //Step 2. controll call method's model
        // Step 2.1: controller news DAO object
            RegistrationDAO dao = new RegistrationDAO();
        // Step 2.2: controller calls method of DAO's object
            boolean result = dao.checkLogin(username, password);
        // Step 3: Process result   
        
        if(result){
            url = SEARCH_PAGE;
        }// username and password are existed
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        finally {
            response.sendRedirect(url);
               out.close();
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

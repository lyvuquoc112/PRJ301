/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package huylvq.controller;

import huylvq.registration.RegistrationDAO;
import huylvq.registration.RegistrationDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hanly
 */
@WebServlet(name = "CheckCookisServlet", urlPatterns = {"/CheckCookisServlet"})
public class CheckCookiesServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String SEARCH_PAGE = "search.jsp";

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
        String url = LOGIN_PAGE;
        //1. get all cookies
        Cookie[] cookies = request.getCookies(); // dùng để lấy danh sách cookies
        // đây là code đọc, do sử dụng getCookies

        try {
            //2. Check existed cookies
            if (cookies != null) { // nếu danh sách cookies có tồn tại, lấy cookies mới nhất, lấy name và value  của cookies
                // đó để check xem có tài khoảng không, đổi url sang search.jsp nếu có  tài khoản
                                    // lần đầu tiên thì cho người dùng vào trang login

                //3. Get newest cookies, --> cookies == username,password
                Cookie newestCookie = cookies[cookies.length - 1]; // lấy phần tử cuối cùng thì lấy kích thước trừ đi 1
                String username = newestCookie.getName();
                String password = newestCookie.getValue();
                //4. Controller calls method of Model
                //4.1  controller news DAO object
                RegistrationDAO dao = new RegistrationDAO();

                //4.2 controller calls method of DAO's object
                RegistrationDTO result = dao.checkLogin(username, password);
                //5. Controller process result
                if (result != null) {
                    url = SEARCH_PAGE; // khi đã xác thục người dùng thì cho người dùng vào trang search động, 
                    // Có câu Hello, username, username này sẽ có sự thay đổi do username dùng để login
                    // nên dùng trang động
                }// nếu không có tài khoản thì giữ nguyên url là login.html
            }// more than one
            // nếu danh sách cookies là null thì giữ vẫn url là login.html
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            // hiện tại cookie đang lưu giữ ở file trong server
            // nên dùng sendRedirect hay RequestDispatcher cũng đươc
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

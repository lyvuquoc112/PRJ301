package pe.controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import pe.model.FashionDao;
import pe.model.FashionDto;
import pe.model.UpdateError;

@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    private static final String UPDATE_PAGE = "update.jsp";
    private static final String ERROR_PAGE = "update.jsp"; // lỗi validation/hệ thống đều quay lại update.jsp

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Chỉ dùng POST để lưu cập nhật
        if (!"POST".equalsIgnoreCase(request.getMethod())) {
            request.setAttribute("ERROR_MESSAGE", "Invalid method.");
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
            return;
        }

        String id = request.getParameter("id");          // hidden từ update.jsp
        String name = request.getParameter("name");
        String des = request.getParameter("des");
        String priceStr = request.getParameter("price");
        String size = request.getParameter("size");
        String statusStr = request.getParameter("status"); // "1"/"0"
        String lastValue = request.getParameter("lastValue");

        UpdateError errors = new UpdateError();
        boolean foundErr = false;

        if (name == null || name.trim().isEmpty() || name.length() > 50) {
            errors.setNameError("Name is required (1..50).");
            foundErr = true;
        }
        if (des == null || des.trim().isEmpty() || des.length() > 500) {
            errors.setDescriptionError("Description is required (1..500).");
            foundErr = true;
        }
        double price = 0;
        try {
            price = Double.parseDouble(priceStr);
            if (price <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            errors.setPriceError("Price must be a positive number.");
            foundErr = true;
        }
        if (size == null || size.trim().isEmpty() || size.length() > 50) {
            errors.setSizeError("Size is required (1..50).");
            foundErr = true;
        }
        boolean status = "1".equals(statusStr); // radio "1"/"0"

        // Dựng DTO để giữ giá trị nếu có lỗi
        FashionDto dto = new FashionDto(id, name, des, price, size, status);

        if (foundErr) {
            request.setAttribute("dto", dto);
            request.setAttribute("ERRORS", errors);
            request.setAttribute("SEARCH_VALUE", lastValue);
            request.getRequestDispatcher(UPDATE_PAGE).forward(request, response);
            return;
        }

        try {
            boolean ok = new FashionDao().update(dto);
            if (ok) {
                String kw = (lastValue == null) ? "" : lastValue;
                String target = "MainController?btAction=Search&txtSearchValue="
                        + URLEncoder.encode(kw, StandardCharsets.UTF_8.name());
                response.sendRedirect(target);
                return;
            } else {
                // Không update được (ví dụ id không còn tồn tại)
                errors.setStatusError("Update failed. Please try again.");
                request.setAttribute("dto", dto);
                request.setAttribute("ERRORS", errors);
                request.setAttribute("SEARCH_VALUE", lastValue);
                request.getRequestDispatcher(UPDATE_PAGE).forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            log("UpdateController_SQL: " + ex.getMessage());
            request.setAttribute("ERROR_MESSAGE", "Database error: " + ex.getMessage());
            request.setAttribute("dto", dto);
            request.setAttribute("SEARCH_VALUE", lastValue);
            request.getRequestDispatcher(UPDATE_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}

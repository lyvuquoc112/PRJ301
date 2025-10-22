/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huylvq.registration;

import huylvq.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hanly
 * ở đây thực hiện mọi code liên quan đến dữ liệu ở database
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection(); // B1. mở kết nối đến DB
            if (con != null) {
                String sql = "select username " //B2. Viết câu lệnh SQL
                        + "from Registration "
                        + "where username=? "
                        + "and password=?";
                pst = con.prepareStatement(sql); //B3. tô đen hoặc chuẩn bị cho câu lệnh SQL thực thi
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery(); //B4. Thực thi câu lệnh SQL
                if (rs.next()) {
                    result = true;
                }
            }
        } finally {// B5. đóng lại tất cả những gì được mở
            if (rs != null) { 
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    
    private List<RegistraionDTO> accounts;

    public List<RegistraionDTO> getAccounts() {
        return accounts;
    }
       
     public void search(String value) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection(); // B1. mở kết nối đến DB
            if (con != null) {
                String sql = "select username, password, lastname, isAdmin " //B2. Viết câu lệnh SQL
                        + "from Registration "
                        + "where lastname like ? ";
                pst = con.prepareStatement(sql); //B3. tô đen hoặc chuẩn bị cho câu lệnh SQL thực thi
                pst.setString(1,"%"+value+"%");
                rs = pst.executeQuery(); //B4. Thực thi câu lệnh SQL
                while (rs.next()) { //B5. Trả về mảng, tạo thuộc tính để hứng nó
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    if(this.accounts == null){ //B6. Kiểm tra cái rổ có chưa, nếu chưa có thì tạo
                        this.accounts = new ArrayList<>();
                    }
                    RegistraionDTO dto = new RegistraionDTO(username, password, lastname, isAdmin);
                    this.accounts.add(dto); //B7. Bỏ vào cái rổ
                }
            }
        } finally {// B8. đóng lại tất cả những gì được mở
            if (rs != null) { 
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}

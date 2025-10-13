/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huylvq.controller.registrationDAO;

import huylvq.controller.util.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author hanly
 */
public class RegistrationDAO {
    public boolean checkLogin(String username, String password) throws SQLException {
        //1. dau tien la ket noi voi database truoc
        //2. tao cau truy van
        //3. to den cau truy van
        //4. truyen tham so vao cau truy van
        //5. thuc hien cau truy van
        boolean result = false;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            String sql = "select username from Registration "
                    + "where username=? "
                    + "and password=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if(rs.next()){
                result =  true;
            }
        } catch (Exception e) {
        } finally {
            if(rs!=null){
                rs.close();
            }
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
        }
        return result;
    }
}

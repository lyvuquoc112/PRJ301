/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huylvqregistration;

import huylvq.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author hanly
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException {
        // truyen hai tham so username va password
        // return result de de chinh sua

        boolean result = false;

        //1. a phai khai bao bien va gan luon
        Connection con = null;// delcare variableand set null 
        PreparedStatement stm = null; // Cau lenh truyen tham so va co menh de where. Khong chay nap lai tu dau
        ResultSet rs = null;
        // chi hay doi tham so
        try {
            //1. Model connect DB
            con = DBHelper.getConnection(); // code process object 
            if (con != null) {
                //2. Model query Data from DB 
                // 2.1 create SQL String
                String sql = "select username "
                        + "from Registration "
                        + "where username = ?"
                        + "and password = ?";// liet ke user name trong bang resistraion voi dieu kien username = A va password = B
                // 2.2 load SQL String into Statement object (cau lenh khong co tham so, moi lan goi la nap vao)
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                // 2.3 Execute Query (R--> ResultSet,voi cau lenh CUD -> integer of row update )
                // ResultSet is a pointer that point to list.
                //First item:BOF, Last item: EOF. Use next method to forward(Forward only)
                rs = stm.executeQuery();
                if (rs.next()) {
                    //3. Model load Data from DB to model
                    //4. Model process to return Result
                    result = true;
                }

            } // end connection is available

        } finally { //close object any way
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();// 1.b dong doi tuong bang moi cach
            }
        }

        return result;
    }
}

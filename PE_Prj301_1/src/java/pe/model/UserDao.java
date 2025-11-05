/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.utils.DbUtils;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class UserDao {

    //-----            your code here   --------------------------------
    public UserDto login(String userId, String password) throws SQLException,ClassNotFoundException{
        UserDto result = null;
        Connection con = null;
        PreparedStatement pst =null;
        ResultSet rs = null;
        try {
            con = DbUtils.getConnection();
            if(con!=null){
                String sql = "select userID, fullName, password, roleID, status "
                        + "FROM [tblUsers] "
                        + "where [userID] = ? "
                        + "and [password] = ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, userId);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if(rs.next()){
                    String fullname = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    Boolean status = rs.getBoolean("status");
                    result = new UserDto(userId, fullname, null, roleID, status);
                }
            }
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

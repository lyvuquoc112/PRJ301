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
public class RegistrationDAO implements Serializable{
    public boolean getLogin(String username, String password) throws SQLException, ClassNotFoundException{
        boolean result = false;
        // Cac buoc su dung doi JDBC
        //1. Khai bao bien va gan luon
        //2. dong bien bang moi cach
        //3. Viet code de xu li
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            //1. Model connect to database
            con = DBHelper.getConnection();
            if(con!=null){
                //2. Model truy van DB
                String sql = "select username "
                        + "from Registration "
                        + "where username=? and password=? ";
                pst = con.prepareStatement(sql);
                pst.setString(1,username );
                pst.setString(2, password);
                rs = pst.executeQuery();
                // result set se tra ve mot bang va vi tri cuoi cua no se luon trong
                if(rs.next()){
                    result = true;
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

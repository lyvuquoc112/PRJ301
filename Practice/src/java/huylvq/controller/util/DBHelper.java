/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huylvq.controller.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hanly
 */
public class DBHelper {
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        //1. dau tien la tao danh sach
        //2. tao duong dan
        //3. ket noi bang duong dan, username, password
        Connection result = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // la danh sach chuyen doi kieu
        // du lieu giua DB va lap trinh
        String url ="jdbc:sqlserver://"
                + "localhost:1433;"
                + "databaseName=PRJSE1933"; // tao duong ket noi
        result = DriverManager.getConnection(url,"sa","12345"); // ket noi
        return result;
    }
}

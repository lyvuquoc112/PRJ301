/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huylvq.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hanly
 */
public class DBHelper {
    // can co 2 cai: connection voi close
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        // ClassNotFoundException se bat loi voi 2 li do:
        // 1. li do dau tien la chua add driver
        // 2. Class for name
        Connection con = null;
        // 1. Load driver (mot danh sach liet ke cac kieu du lieu chuyen doi tu DB sang lap trinh va nguoc lai)
        // 1.1 add driver vao project
        //1.2 Code to load driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. Tao URL
        String url ="jdbc:sqlserver://" 
                + "localhost:1433;" // neu su dung cac OS khac voi window thi local host thay thanh 127.0.0.1
                + "databaseName=PRJSE1933";
        //3. Open connection Driver Manager
        con = DriverManager.getConnection(url, "sa", "12345");
        // SQLException se bat loi khi 1 trong 3 yeu to sau day
        //1. URL bi sai
        //2. password hoac la username sai
        // TCP/IP cua cac ban dang bi dong hoac la nhap khong dung so
        return con;
    }
}

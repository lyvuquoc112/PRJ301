/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huylvq.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hanly
 */
public class DBHelper {
    public static Connection getConnection() throws ClassNotFoundException, SQLException{ 
        // xuat hien loi notfound la do chua add drive hoac class.forname sai
        Connection con = null;
        // 1. Load driver (la 1 danh sach liet ke kieu du lieu chuyen doi tu DMS(data manager system) 
        //den lap trinh va nguoc lai)
        //1.1 Add driver into project
        //1.2 Code to load Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        // 2. create url connection String
        // Syntax: protocal:DMBB_Name://ip:port;databaseName=DB_name
        String url ="jdbc:sqlserver://"
                + "localhost:1433;"
                + "databaseName=PRJSE1933";
        // 3. Open connection Driver Manager (la thanh phan dung de chuyen kieu du lieu giua DMS va lap trinh
        //)
        con = DriverManager.getConnection(url,"sa","12345");
        
        
        
        
        return con;
    }
}

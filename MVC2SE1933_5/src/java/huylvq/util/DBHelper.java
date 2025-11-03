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
        // ClassNotFoundException bắt lỗi khi chưa:
        // Add drive
        // Class.forName sai
        Connection con = null;
        // 1. Load driver (là 1 danh sách liệt kê kiểu dữ liệu chuyển đổi từ DMS(data manager system)
        // đến lập trình và ngược lại)
        //1.1 Add driver into project
        //1.2 Code to load Driver 
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        // 2. create url connection String
        // Syntax: protocal:DMBB_Name://ip:port;databaseName=DB_name
        String url ="jdbc:sqlserver://"
                + "localhost:1433;"
                + "databaseName=PRJSE1933";
        // 3. Open connection Driver Manager (
        //là thành phần dùng để chuyển kiểu dữ liệu giữa DMS và lập trình)
        con = DriverManager.getConnection(url,"sa","12345");

        return con;
    }
}

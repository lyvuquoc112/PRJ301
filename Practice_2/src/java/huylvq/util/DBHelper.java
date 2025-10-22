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
 * DBHelper là dùng để kết nối với database
 */
public class DBHelper {
    // ClassNotFound là bắt trường hợp lỗi khi danh sách bị sai hoặc là chưa có add driver vào lib
    // SQLException bắt lỗi khi url, pss hoặc sa sai, TCP/IP chưa mở
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection con = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // đây là danh sách để chuyển đổi dữ liệu
        // giữa DB và ngôn ngữ lập trình
        String url = "jdbc:sqlserver://"
                + "localhost:1433;"
                + "databaseName=PRJSE1933";
        // đây là địa chỉ để kết nối
        con = DriverManager.getConnection(url,"sa","12345");   
        // thực hiện chuyển đỏi dữ liệu giữa DB và ngôn ngữ lập trình
        // thực hiện kết nối bằng url, username và passoword
        return con;
    }
}

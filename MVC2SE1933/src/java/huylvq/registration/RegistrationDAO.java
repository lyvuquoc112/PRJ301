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
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException {
        //truyền hai tham số username và password
        //return result để chỉnh sửa

        boolean result = false;

        //1.a phải khai báo biến và gán luôn
        Connection con = null;// delcare variableand set null 
        PreparedStatement stm = null; //Dòng cho câu lệnh truyền tham số và có điều kiện where, 
        //prepare không phải chạy lại từ đầu
        ResultSet rs = null;
        // chỉ thay đổi tham số
        try {
            //1. Model connect DB
            con = DBHelper.getConnection(); // code process object 
            if (con != null) {
                //2. Model query Data from DB 
                // 2.1 create SQL String
                String sql = "select username "
                        + "from Registration "
                        + "where username = ? "
                        + "and password = ? ";// Liệt kê user name trong bảng resgistration với điều kiện username = A và password = B
                // 2.2 load SQL String into Statement object (dùng cho câu lệnh không có tham số, mỗi lần chạy là nạp lại từ đầu)
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
                con.close();// 1.b đóng đối tường bằng mọi cách
            }
        }
        return result;
    }

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastname(String searchValue) throws SQLException, ClassNotFoundException {
        //truyền hai tham số username và password
        //return result để chỉnh sửa
        //1.a phải khai báo biến và gán luôn
        Connection con = null;// delcare variableand set null 
        PreparedStatement stm = null; //Dòng cho câu lệnh truyền tham số và có điều kiện where, 
        //prepare không phải chạy lại từ đầu
        ResultSet rs = null;
        // chỉ thay đổi tham số
        try {
            //1. Model connect DB
            con = DBHelper.getConnection(); // code process object 
            if (con != null) {
                //2. Model query Data from DB 
                // 2.1 create SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "where lastname LIKE ? ";// Liệt kê user name trong bảng resgistration với điều kiện username = A và password = B
                // 2.2 load SQL String into Statement object (dùng cho câu lệnh không có tham số, mỗi lần chạy là nạp lại từ đầu)
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                // 2.3 Execute Query (R--> ResultSet,voi cau lenh CUD -> integer of row update )
                // ResultSet is a pointer that point to list.
                //First item:BOF, Last item: EOF. Use next method to forward(Forward only)
                rs = stm.executeQuery();
                while (rs.next()) {
                    //2.3.1 model load data from DB
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //2.3.2 model store data to itself
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }// acocounts have not existed
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullname, role);
                    this.accounts.add(dto);
                }// ned traverse result set
            } // end connection is available
        } finally { //close object any way
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();// 1.b đóng đối tường bằng mọi cách
            }
        }
    }

    public boolean deleteAccount(String username) throws SQLException, ClassNotFoundException {
        boolean result = false;
        //1.a phải khai báo biến và gán luôn
        Connection con = null;// delcare variableand set null 
        PreparedStatement stm = null; //Dòng cho câu lệnh truyền tham số và có điều kiện where, 
        //prepare không phải chạy lại từ đầu
        // xoa het cac dong khi biet het username
        // chỉ thay đổi tham số
        try {
            //1. Model connect DB
            con = DBHelper.getConnection(); // code process object 
            if (con != null) {
                //2. Model query Data from DB 
                // 2.1 create SQL String
                String sql = "Delete "
                        + "from Registration "
                        + "where username = ?";// Liệt kê user name trong bảng resgistration với điều kiện username = A và password = B
                // 2.2 load SQL String into Statement object (dùng cho câu lệnh không có tham số, mỗi lần chạy là nạp lại từ đầu)
                stm = con.prepareStatement(sql);
                stm.setString(1, username); // start 1, because co bao nhieu tham so thi bay nhieu cham hoi, tu trai sang phair

                // 2.3 Execute Query (R--> ResultSet,voi cau lenh CUD(insert, update,delete) -> integer of row update )
                int effectedRows = stm.executeUpdate(); // insert, udate, delete thi execteUpdate
                if (effectedRows > 0) {
                    result = true;
                }
                // CUD never related to ResultSet
                // ResultSet is a pointer that point to list.
                //First item:BOF, Last item: EOF. Use next method to forward(Forward only)
                //3. Model load Data from DB to model
                //4. Model process to return Result
            } // end connection is available
        } finally { //close object any way

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();// 1.b đóng đối tường bằng mọi cách
            }
        }
        return result;
    }

    public boolean updateAccount(String username, String password, boolean isAdmin) throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection con = null;// delcare variableand set null 
        PreparedStatement stm = null; //Dòng cho câu lệnh truyền tham số và có điều kiện where, 
        //prepare không phải chạy lại từ đầu
        // xoa het cac dong khi biet het username
        // chỉ thay đổi tham số
        try {
            //1. Model connect DB
            con = DBHelper.getConnection(); // code process object 
            if (con != null) {
                //2. Model query Data from DB 
                // 2.1 create SQL String
                String sql = "UPDATE [Registration] "
                        + "SET [password] = ?, [isAdmin] = ? "
                        + "WHERE [username] = ?";// Liệt kê user name trong bảng resgistration với điều kiện username = A và password = B
                // 2.2 load SQL String into Statement object (dùng cho câu lệnh không có tham số, mỗi lần chạy là nạp lại từ đầu)
                stm = con.prepareStatement(sql);
                stm.setString(1, password); // start 1, because co bao nhieu tham so thi bay nhieu cham hoi, tu trai sang phair
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                // 2.3 Execute Query (R--> ResultSet,voi cau lenh CUD(insert, update,delete) -> integer of row update )
                int effectedRows = stm.executeUpdate(); // insert, udate, delete thi execteUpdate
                if (effectedRows > 0) {
                    result = true;
                }
                // CUD never related to ResultSet
                // ResultSet is a pointer that point to list.
                //First item:BOF, Last item: EOF. Use next method to forward(Forward only)
                //3. Model load Data from DB to model
                //4. Model process to return Result
            } // end connection is available
        } finally { //close object any way

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();// 1.b đóng đối tường bằng mọi cách
            }
        }

        return result;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huylvq.registration;

import huylvq.utils.DBHelper;
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
public class RegistrationDAO {

    public boolean checkLogin(String username, String password) throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String url = "select [username] "
                        + "from [Registration] "
                        + "where [username] = ? "
                        + "and [password] = ?";
                pst = con.prepareStatement(url);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastname(String value) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String url = "SELECT [username], [password], [lastname], [isAdmin] "
                        + "FROM [Registration] "
                        + "where [lastname] like ?";
                pst = con.prepareStatement(url);
                pst.setString(1, "%" + value + "%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, isAdmin);
                    this.accounts.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String pk) throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String url = "Delete "
                        + "from [Registration] "
                        + "where [username] = ?";
                pst = con.prepareStatement(url);
                pst.setString(1, pk);
                int effectedRows = pst.executeUpdate();
                if (effectedRows > 0) {
                    result = true;
                }

            }
        } finally {

            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean updateAccount(String pk, String password, boolean isAdmin) throws ClassNotFoundException, SQLException{
        boolean result = false;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String url = "UPDATE [Registration] "
                        + "SET [password] = ?, [isAdmin] = ? "
                        + "where [username] = ?";
                pst = con.prepareStatement(url);
                pst.setString(1, password);
                pst.setBoolean(2, isAdmin);
                pst.setString(3, pk);
                int effectedRows = pst.executeUpdate();
                if (effectedRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}

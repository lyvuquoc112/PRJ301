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
public class FashionDao {
    //-----            your code here   --------------------------------

    private List<FashionDto> products;

    public List<FashionDto> getProducts() {
        return products;
    }

    public void searchFashion(String searchValue) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DbUtils.getConnection();
            if (con != null) {
                String sql = "select [id], [name], [description], [price], [size], [status] "
                        + "FROM [tblFashion] "
                        + "where [name] like ? ";
                pst = con.prepareStatement(sql);
                pst.setString(1, "%" + searchValue + "%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");
                    String size = rs.getString("size");
                    Boolean status = rs.getBoolean("status");
                    if (this.products == null) {
                        this.products = new ArrayList<>();
                    }
                    FashionDto dto = new FashionDto(id, name, description, price, size, status);
                    this.products.add(dto);
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

    public FashionDto findByID(String id) throws SQLException, ClassNotFoundException {
        FashionDto dto = null;
        try ( Connection con = DbUtils.getConnection();  PreparedStatement pst = con.prepareStatement(
                "SELECT [id],[name],[description],[price],[size],[status] "
                + "FROM [tblFashion] WHERE [id]=?")) {
            pst.setString(1, id);
            try ( ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    dto = new FashionDto(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getString("size"),
                            rs.getBoolean("status")
                    );
                }
            }
        }
        return dto;
    }

    // 3.4 – save updated info
    public boolean update(FashionDto dto) throws SQLException, ClassNotFoundException {
        int rows;
        try ( Connection con = DbUtils.getConnection();  PreparedStatement pst = con.prepareStatement(
                "UPDATE [tblFashion] "
                + "SET [name]=?,[description]=?,[price]=?,[size]=?,[status]=? "
                + "WHERE [id]=?")) {

            pst.setString(1, dto.getName());
            pst.setString(2, dto.getDescription());
            pst.setDouble(3, dto.getPrice());
            pst.setString(4, dto.getSize());
            pst.setBoolean(5, dto.isStatus());
            pst.setString(6, dto.getId());

            rows = pst.executeUpdate();
        }
        return rows > 0;
    }
}

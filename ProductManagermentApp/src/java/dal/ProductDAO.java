/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import model.Product;

/**
 *
 * @author mitsu
 */
public class ProductDAO extends DBConnect {
    
    public ArrayList<Product> getAllProduct() {
    String sql = "SELECT * FROM Product";
    ArrayList<Product> list = new ArrayList<>();

    try (PreparedStatement stm = c.prepareStatement(sql);
         ResultSet rs = stm.executeQuery()) {
        
        while (rs.next()) {
            String code = rs.getString("code");
            String description = rs.getString("description");
            double price = rs.getDouble("price");
            Product p = new Product(code, description, price);
            p.setId(rs.getInt("id"));
            list.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

    public void createProduct(Product p) {
        String sql ="Insert into Product(code, description, price) values(?,?,?)";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1,p.getCode()); 
            stm.setString(2,p.getDescription());
            stm.setDouble(3,p.getPrice());
            stm.executeUpdate();
        } catch (SQLException e) {
        }
    } 
    
    public void deleteProduct(String id){
    String sql = "DELETE FROM Product WHERE id=?";

        try {
            PreparedStatement stm=c.prepareStatement(sql);
            stm.setString(1,id);
            stm.executeUpdate();
        } catch (Exception e) {        
        }
    }

    public Product getProductById(String id) {
    Product p = null;
    String sql = "SELECT * FROM Product WHERE id=?";
    try (PreparedStatement stm = c.prepareStatement(sql)) {
        stm.setString(1, id);
        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                String code = rs.getString("code");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                p = new Product(code, description, price);
                p.setId(rs.getInt("id"));  // Lấy id từ database thay vì ép kiểu
            }
        }
    } catch (SQLException e) {
    }
    return p;
}
    public void updateProduct(Product product){
         String sql = "Update Product set description=?, price=? , code=? where id = ?";
         try {
            PreparedStatement stm=c.prepareStatement(sql);
            stm.setString(1, product.getDescription());
            stm.setDouble(2, product.getPrice());
            stm.setString(3, product.getCode());
            stm.setString(4, String.valueOf(product.getId()));
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }
}


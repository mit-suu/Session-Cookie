package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Product;
public class ProductDAO extends DBConnect{
    public ArrayList<Product> getAllProduct(){
        String sql = "SELECT * FROM Products";
        ArrayList<Product> list = new ArrayList<>();
        try (PreparedStatement stm = c.prepareStatement(sql);
         ResultSet rs = stm.executeQuery()) {
        while (rs.next()) {
            int id = rs.getInt("id");
            String code = rs.getString("code");
            String description = rs.getString("description");
            double price = rs.getDouble("price");          
            Product p = new Product(id, code,description,price);
            p.setId(rs.getInt("id"));
            list.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
    }
        public boolean createProduct(Product p) {
    String sql = "INSERT INTO Products(code, description, price) VALUES(?, ?, ?)";
    try {
        PreparedStatement stm = c.prepareStatement(sql);
        stm.setString(1, p.getCode());
        stm.setString(2, p.getDescription());
        stm.setDouble(3, p.getPrice());

        int rowsInserted = stm.executeUpdate();
        return rowsInserted > 0;
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi ra console để debug
        return false;
    }
}
public boolean deleteProduct(String id) {
    String sql = "DELETE FROM Products WHERE id=?";
    try {
        PreparedStatement stm = c.prepareStatement(sql);
        stm.setString(1, id);
        int rowsAffected = stm.executeUpdate();
        return rowsAffected > 0; // Kiểm tra nếu có ít nhất 1 dòng bị xóa
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi ra console để debug
        return false;
    }
}
        
    public Product getProductById(String id) {
    Product p = null;
    String sql = "SELECT * FROM Products WHERE id=?";
    try (PreparedStatement stm = c.prepareStatement(sql)) {
        System.out.println("SQL Query: " + sql + " with ID = " + id); // Log SQL query
        stm.setString(1, id);

        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                int id1 = rs.getInt("id");
                String code = rs.getString("code");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                p = new Product(id1, code, description, price);
                System.out.println("Product found in DB: " + p.getCode()); // Log sản phẩm tìm thấy
            } else {
                System.out.println("No product found for ID = " + id);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();  // In lỗi nếu có
    }
    return p;
}

   
    public boolean updateProduct(Product product){
         String sql = "Update Products set description=?, price=? , code=? where id = ?";
         try {
            PreparedStatement stm=c.prepareStatement(sql);
            stm.setString(1, product.getDescription());
            stm.setDouble(2, product.getPrice());
            stm.setString(3, product.getCode());
            stm.setString(4, String.valueOf(product.getId()));
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    public static void main(String[] args) {
        ProductDAO p = new ProductDAO();
        var list = p.getAllProduct();
        for (Product product : list) {
            System.out.println(product.toString());
        }
    }
}

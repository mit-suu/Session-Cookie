package dao;

import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends DBConnect {
    
    // Lấy danh sách tất cả người dùng
    public ArrayList<User> getAllUsers() {
        String sql = "SELECT * FROM Users";
        ArrayList<User> list = new ArrayList<>();
        try (PreparedStatement stm = c.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");  
                String role = rs.getString("role");
                list.add(new User(id, name, email, password, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Đăng ký tài khoản mới
    public boolean createUser(User user) {
        String sql = "INSERT INTO Users(name, email, password, role) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, user.getName());
            stm.setString(2, user.getEmail());
            stm.setString(3, user.getPassword());  // Lưu mật khẩu dưới dạng plain text
            String role = user.getRole() != null ? user.getRole() : "User";
            stm.setString(4, role);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa người dùng theo ID
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM Users WHERE id=?";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    // Lấy thông tin người dùng theo ID
    public User getUserById(int id) {
        User user = null;
        String sql = "SELECT * FROM Users WHERE id=?";
        try (PreparedStatement stm = c.prepareStatement(sql)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    user = new User(id, name, email, password, role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Cập nhật thông tin người dùng
    public boolean updateUser(User user) {
        String sql = "UPDATE Users SET name=?, email=?, password=?, role=? WHERE id=?";
        try {
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, user.getName());
            stm.setString(2, user.getEmail());
            stm.setString(3, user.getPassword()); // Không mã hóa mật khẩu
            stm.setString(4, user.getRole());
            stm.setInt(5, user.getId());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    // Kiểm tra đăng nhập (so sánh email + mật khẩu)
    public User checkLogin(String email, String password) {
        User user = null;
        String sql = "SELECT * FROM Users WHERE email=? AND password=?";
        try (PreparedStatement stm = c.prepareStatement(sql)) {
            stm.setString(1, email);
            stm.setString(2, password); // So sánh mật khẩu trực tiếp
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String role = rs.getString("role");
                    user = new User(id, name, email, password, role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
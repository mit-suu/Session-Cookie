package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Student;

public class StudentDAO extends DBConnect {
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";

        try (PreparedStatement stmt = c.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getDate("dob")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
public Student getStudentById(int id) {
    String sql = "SELECT * FROM student WHERE id = ?";
    try (PreparedStatement stmt = c.prepareStatement(sql)) {
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("gender"),
                rs.getDate("dob")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

    public void insertStudent(Student student) {
        String sql = "INSERT INTO student (id, name, gender, dob) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getGender());
            stmt.setDate(4, student.getDob());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        String sql = "UPDATE student SET name = ?, gender = ?, dob = ? WHERE id = ?";

        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getGender());
            stmt.setDate(3, student.getDob());
            stmt.setInt(4, student.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM student WHERE id = ?";

        try (PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


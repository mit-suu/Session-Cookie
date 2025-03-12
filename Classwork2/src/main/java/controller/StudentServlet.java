import dal.StudentDAO;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            listStudents(request, response);
        } else {
            switch (action) {
                case "delete":
                    deleteStudent(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "add":
                    showAddForm(request, response);
                    break;
                default:
                    listStudents(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("insert".equals(action)) {
            insertStudent(request, response);
        } else if ("update".equals(action)) {
            updateStudent(request, response);
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentDAO.getAllStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("studentList.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("addStudent.jsp").forward(request, response);
    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            Date dob = Date.valueOf(request.getParameter("dob"));

            Student student = new Student(id, name, gender, dob);
            studentDAO.insertStudent(student);
            response.sendRedirect("student");
        } catch (Exception e) {
            request.setAttribute("error", "Invalid input. Please try again.");
            request.getRequestDispatcher("addStudent.jsp").forward(request, response);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentDAO.getStudentById(id);
        if (student != null) {
            request.setAttribute("student", student);
            request.getRequestDispatcher("updateStudent.jsp").forward(request, response);
        } else {
            response.sendRedirect("student");
        }
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            Date dob = Date.valueOf(request.getParameter("dob"));

            Student student = new Student(id, name, gender, dob);
            studentDAO.updateStudent(student);
            response.sendRedirect("student");
        } catch (Exception e) {
            request.setAttribute("error", "Invalid input. Please try again.");
            request.getRequestDispatcher("updateStudent.jsp").forward(request, response);
        }
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        response.sendRedirect("student");
    }
}
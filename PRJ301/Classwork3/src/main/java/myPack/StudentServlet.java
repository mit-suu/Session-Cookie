package myPack;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String[] NAMES = { "Hiep", "Nam", "Hai", "Tien", "Dang", "Dung" };
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Student> students = (ArrayList<Student>) session.getAttribute("students");

        if (students == null) {
            students = new ArrayList<>();
        }

        String action = request.getParameter("action");

        if ("generate".equals(action)) {
            int count = Integer.parseInt(request.getParameter("count"));
            students.clear();
            generateStudents(students, count);
        } else if ("update".equals(action)) {
            int index = Integer.parseInt(request.getParameter("index"));
            if (index >= 0 && index < students.size()) {
                students.set(index, generateRandomStudent(index + 1));
            }
        }

        session.setAttribute("students", students);
        request.getRequestDispatcher("student.jsp").forward(request, response);
    }

    private void generateStudents(ArrayList<Student> students, int count) {
        for (int i = 0; i < count; i++) {
            students.add(generateRandomStudent(i + 1));
        }
    }

    private Student generateRandomStudent(int id) {
        Random rand = new Random();
        String name = NAMES[rand.nextInt(NAMES.length)];
        boolean gender = rand.nextBoolean();
        Date dob = randomDate();

        return new Student(id, name, gender, dob);
    }

    private Date randomDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse("2000-" + (new Random().nextInt(12) + 1) + "-" + (new Random().nextInt(28) + 1));
        } catch (ParseException e) {
            return new Date();
        }
    }
}

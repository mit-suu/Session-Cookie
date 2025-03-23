/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;

/**
 *
 * @author mitsu
 */
@WebServlet(name = "AuthController", urlPatterns = {"/AuthController"})
public class AuthController extends HttpServlet {
        UserDAO userDAO = new UserDAO();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        
        switch (action) {
            case "login":
                login(request, response, session);
                break;
            case "register":
                register(request, response);
                break;
            case "logout":
                session.invalidate();
                response.sendRedirect("login.jsp");
                break;
            default:
                response.sendRedirect("login.jsp");
        }

    }

    private void login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userDAO.checkLogin(email, password);
        if (user != null) {
            session = request.getSession();
            session.setAttribute("user", user);
            if ("on".equals(request.getParameter("remember"))) {
                Cookie cookie = new Cookie("email", email);
                cookie.setMaxAge(7 * 24 * 60 * 60); // 7 ngày
                response.addCookie(cookie);
            }
            response.sendRedirect("ProductController?action=viewProducts");    
        }else {
            request.setAttribute("error", "Invalid email or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
    
     private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        
        User user = new User(name, email, password, "user");
        if (userDAO.createUser(user)) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "Registration failed!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
     }
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}

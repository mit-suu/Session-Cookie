package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private final String USERNAME_SYSTEM="administrator";
    private final String PASSWORD_SYSTEM="123";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("remember");
        
        if(username.equalsIgnoreCase(USERNAME_SYSTEM)&&password.equalsIgnoreCase(PASSWORD_SYSTEM)){
        //tao 1 phien dang nhap
        HttpSession session =request.getSession();
        session.setAttribute("session_login", username);   
        if(rememberMe !=null) {
        Cookie usernameCookie=new Cookie("user_name", username);
        Cookie passwordCookie=new Cookie("user_pass", password);
        
        //Bac buoc phai set tgian tuoi tac cho no
        usernameCookie.setMaxAge(60*60*24);//second (1 ngay)
        passwordCookie.setMaxAge(60*60*24);
        
        //them cookie vao respone
        response.addCookie(usernameCookie);
        response.addCookie(passwordCookie);          
        }else{
            Cookie[] cookies=request.getCookies();
        
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("user_name")){
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    }
                    if(cookie.getName().equals("user_pass")){
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    }
                }
            }
        }
        response.sendRedirect("Detail.jsp");
        }else{
            request.setAttribute("error", "please enter valid infomation account !!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}

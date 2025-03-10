package controller;

import model.Vote;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/NewCandidateServlet")
public class NewCandidateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Vote vote = (Vote) session.getAttribute("vote");

        if (vote == null) {
            vote = new Vote("Favorite Programming Language");
            session.setAttribute("vote", vote);
        }

        String candidateName = request.getParameter("candidateName");
        if (candidateName != null && !candidateName.trim().isEmpty()) {
            vote.addCandidate(candidateName);
        }

        session.setAttribute("vote", vote);
        response.sendRedirect("vote.jsp");
    }
}

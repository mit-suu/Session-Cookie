package controller;

import model.Candidate;
import model.Vote;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/VoteServlet")
public class VoteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Vote vote = (Vote) session.getAttribute("vote");

        if (vote == null) {
            vote = new Vote("Favorite Programming Language");
            vote.addCandidate("Java");
            vote.addCandidate("Python");
            vote.addCandidate("C++");
            vote.addCandidate("JavaScript");
            session.setAttribute("vote", vote);
        }

        String[] selectedCandidates = request.getParameterValues("candidates");
        if (selectedCandidates != null && selectedCandidates.length < vote.getCandidates().size()) {
            for (String candidateName : selectedCandidates) {
                vote.voteCandidate(candidateName);
            }
        }

        session.setAttribute("vote", vote);
        response.sendRedirect("result.jsp");
    }
}

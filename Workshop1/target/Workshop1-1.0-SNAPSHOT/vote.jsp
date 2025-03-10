<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Vote, model.Candidate" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Vote Now</title>
</head>
<body>
    <h2>Vote for Your Favorite Option</h2>
    <form action="VoteServlet" method="post">
        <%
            Vote vote = (Vote) session.getAttribute("vote");
            if (vote == null) {
                vote = new Vote("Favorite Programming Language");
                vote.addCandidate("Java");
                vote.addCandidate("Python");
                vote.addCandidate("C++");
                vote.addCandidate("JavaScript");
                session.setAttribute("vote", vote);
            }
            for (Candidate c : vote.getCandidates()) {
        %>
            <input type="checkbox" name="candidates" value="<%= c.getName() %>"> <%= c.getName() %><br>
        <%
            }
        %>
        <input type="submit" value="Submit Vote">
    </form>

    <h3>Add a New Candidate</h3>
    <form action="NewCandidateServlet" method="post">
        <input type="text" name="candidateName" required>
        <input type="submit" value="Add Candidate">
    </form>

    <a href="result.jsp">View Results</a>
</body>
</html>

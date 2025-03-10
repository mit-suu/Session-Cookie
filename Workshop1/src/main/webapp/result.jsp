<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Vote, model.Candidate" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Voting Results</title>
</head>
<body>
    <h2>Voting Results</h2>
    <%
        Vote vote = (Vote) session.getAttribute("vote");
        if (vote != null) {
            for (Candidate c : vote.getCandidates()) {
    %>
                <p><%= c.getName() %>: <%= c.getVoteCount() %> votes</p>
    <%
            }
        } else {
    %>
        <p>No votes have been cast yet.</p>
    <%
        }
    %>

    <a href="vote.jsp">Go Back to Voting</a>
</body>
</html>

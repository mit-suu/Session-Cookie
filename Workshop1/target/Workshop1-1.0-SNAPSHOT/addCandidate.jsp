<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add a New Candidate</title>
</head>
<body>
    <h2>Add a New Candidate</h2>
    <form action="NewCandidateServlet" method="post">
        <input type="text" name="candidateName" required>
        <input type="submit" value="Add Candidate">
    </form>
    <a href="vote.jsp">Back to Voting</a>
</body>
</html>

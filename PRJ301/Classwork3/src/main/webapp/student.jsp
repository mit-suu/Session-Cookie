<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.SimpleDateFormat, myPack.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Demo 2!</title>
</head>
<body>

<h2>Student Demo 2!</h2>

<form action="StudentServlet" method="post">
    <input type="number" name="count" min="1" required>
    <input type="hidden" name="action" value="generate">
    <button type="submit">Generate</button>
</form>

<% 
    ArrayList<Student> students = (ArrayList<Student>) session.getAttribute("students");
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
%>

<% if (students != null && !students.isEmpty()) { %>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Gender</th>
            <th>DOB</th>
            <th></th>
        </tr>
        <% for (int i = 0; i < students.size(); i++) { Student s = students.get(i); %>
        <tr>
            <td><%= s.getId() %></td>
            <td><%= s.getName() %></td>
            <td><%= s.isGender() %></td>
            <td><%= sdf.format(s.getDob()) %></td>
            <td>
                <form action="StudentServlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="index" value="<%= i %>">
                    <a href="#" onclick="this.parentNode.submit();">update</a>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
<% } else { %>
    <p>No students available.</p>
<% } %>

</body>
</html>

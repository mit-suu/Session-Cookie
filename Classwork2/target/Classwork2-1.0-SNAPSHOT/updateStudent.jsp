<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Student</title>
</head>
<body>
    <h2>Update Student</h2>
<form action="student" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${student.id}">
    Name: <input type="text" name="name" value="${student.name}" required><br>
    Gender: 
    <select name="gender" required>
        <option value="Male" ${student.gender == 'Male' ? 'selected' : ''}>Male</option>
        <option value="Female" ${student.gender == 'Female' ? 'selected' : ''}>Female</option>
    </select><br>
    DOB: <input type="date" name="dob" value="${student.dob}" required><br>
    <input type="submit" value="Update Student">
</form>
<a href="student">Back to List</a>
</body>
</html>

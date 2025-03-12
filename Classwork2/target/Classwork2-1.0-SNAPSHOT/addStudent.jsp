<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Student</title>
</head>
<body>
    <h2>Add New Student</h2>
    <form action="student" method="post">
    <input type="hidden" name="action" value="insert">
    ID: <input type="number" name="id" required><br>
    Name: <input type="text" name="name" required><br>
    Gender: 
    <select name="gender" required>
        <option value="Male">Male</option>
        <option value="Female">Female</option>
    </select><br>
    DOB: <input type="date" name="dob" required><br>
    <input type="submit" value="Add Student">
</form>
<a href="student">Back to List</a>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>Register</h2>
    <c:if test="${not empty error}">
        <p style="color: red">${error}</p>
    </c:if>
    <form action="AuthController" method="post">
        <input type="hidden" name="action" value="register">
        Name: <input type="text" name="name" required><br>
        Email: <input type="email" name="email" required><br>
        Password: <input type="password" name="password" required><br>
        <input type="submit" value="Register">
    </form>
    <a href="login.jsp">Already have an account? Login</a>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>Login</h2>
    <c:if test="${not empty error}">
        <p style="color: red">${error}</p>
    </c:if>
    <form action="AuthController" method="post">
        <input type="hidden" name="action" value="login">
        Email: <input type="email" name="email" required><br>
        Password: <input type="password" name="password" required><br>
        <label><input type="checkbox" name="remember"> Remember Me</label><br>
        <input type="submit" value="Login">
    </form>
    <a href="register.jsp">Register</a>
</body>
</html>
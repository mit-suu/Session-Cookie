<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký</title>
    <link rel="stylesheet" href="styles.css">
</head>

<style>

body {
    font-family: Arial, sans-serif;
    background-color: #f2f2f2;
    text-align: center;
}

.container {
    width: 300px;
    margin: 100px auto;
    padding: 20px;
    background-color: white;
    box-shadow: 0px 0px 10px gray;
    border-radius: 10px;
}

h2 {
    margin-bottom: 20px;
}

label {
    display: block;
    margin-top: 10px;
}

input {
    width: 90%;
    padding: 8px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

button {
    width: 100%;
    padding: 10px;
    margin-top: 20px;
    background-color: #28a745;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

button:hover {
    background-color: #218838;
}

p {
    margin-top: 10px;
}

a {
    color: #007bff;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}
</style>

<body>
    <div class="container">
        <h2>Đăng Ký</h2>
        <form action="RegisterServlet" method="post">
            <label for="fullname">Họ và tên:</label>
            <input type="text" id="fullname" name="fullname" required>
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            
            <label for="username">Tên đăng nhập:</label>
            <input type="text" id="username" name="username" required>
            
            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password" required>
            
            <button type="submit">Đăng Ký</button>
        </form>
        <p>Đã có tài khoản? <a href="login.jsp">Đăng nhập</a></p>
    </div>
</body>
</html>


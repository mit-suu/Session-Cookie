<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chào mừng</title>
    <link rel="stylesheet" href="styles.css">
</head>
<style>
    .btn {
    display: inline-block;
    padding: 10px 20px;
    margin-top: 20px;
    background-color: #dc3545;
    color: white;
    text-decoration: none;
    border-radius: 5px;
}

.btn:hover {
    background-color: #c82333;
}

</style>
<body>
    <div class="container">
        <h2>Chào mừng, ${sessionScope.session_login}</h2>
        <p>Bạn đã đăng nhập thành công.</p>
        <a href="logout" class="btn">Đăng xuất</a>
    </div>
</body>
</html>

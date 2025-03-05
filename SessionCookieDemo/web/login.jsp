<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Nhập</title>
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
        <h2>Đăng Nhập</h2>
        
        <%
            //doc cokie trong trinh duyet
            String usernameCookieSaved="";
            String passwordCookieSaved="";
            Cookie [] cookiesListFromBrowser=request.getCookies();
            if(cookiesListFromBrowser!=null){
            for (Cookie cookie : cookiesListFromBrowser) {
                    if(cookie.getName().equals("user_name")){
                    usernameCookieSaved=cookie.getValue();}
                    if(cookie.getName().equals("user_pass")){
                    passwordCookieSaved=cookie.getValue();}
                }
            }
        %>
        
        <c:if test="${requestScope.error}!=null">
            <h1 style="color: red">${requestScope.error}</h1>  
        </c:if>
        <form action="login" method="post">
            <label for="username">Tên đăng nhập:</label>
            <input type="text" id="username" name="username" value ="<%= usernameCookieSaved %>"required>
            
            <label for="password">Mật khẩu:</label>
                <input type="password" id="password" name="password"value ="<%= passwordCookieSaved %>"required>
            
            <label>
                <input type="checkbox" name="remember" id="remember">
                Ghi nhớ đăng nhập
            </label>
            
            <button type="submit">Đăng Nhập</button>
        </form>
        <p>Chưa có tài khoản? <a href="register.jsp">Đăng ký</a></p>
    </div>
</body>
</html>

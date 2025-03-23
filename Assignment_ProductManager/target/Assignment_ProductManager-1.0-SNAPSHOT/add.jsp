<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add New Product</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>Add New Product</h2>
    <form action="ProductController" method="post">
        <input type="hidden" name="action" value="add">
        Code: <input type="text" name="code" required><br>
        Description: <input type="text" name="description" required><br>
        Price: <input type="number" step="0.01" name="price" required><br>
        <input type="submit" value="Add">
    </form>
    <a href="ProductController?action=viewProducts">Back to List</a>
</body>
</html>

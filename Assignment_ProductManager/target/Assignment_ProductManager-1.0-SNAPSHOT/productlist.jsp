<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product List</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>Product List</h2>
    <a href="add.jsp">Add New Product</a> | 
    <a href="AuthController?action=logout">Logout</a>
    <c:if test="${not empty message}">
        <p style="color: red;">${message}</p>
    </c:if>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Code</th>
            <th>Description</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        <c:choose>
            <c:when test="${not empty requestScope.productList}">
                <c:forEach var="product" items="${requestScope.productList}">
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.code}</td>
                        <td>${product.description}</td>
                        <td>${product.price}</td>
                        <td>
                            <a href="ProductController?action=edit&id=${product.id}">Edit</a>
                            <a href="ProductController?action=delete&id=${product.id}" onclick="return confirm('Are you sure?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="5" style="text-align: center; color: red;">No products available</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>
</body>
</html>

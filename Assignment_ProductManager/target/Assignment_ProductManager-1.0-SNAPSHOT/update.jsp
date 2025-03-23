<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Update Product</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>Update Product</h2>

    <c:if test="${empty product}">
        <p style="color: red;">Product not found!</p>
    </c:if>

    <c:if test="${not empty product}">
        <form action="ProductController?action=update" method="post">
            <input type="hidden" name="id" value="${product.id}" />
            Code: <input type="text" name="code" value="${product.code}" /><br/>
            Description: <input type="text" name="description" value="${product.description}" /><br/>
            Price: <input type="number" step="0.01" name="price" value="${product.price}" /><br/>
            <input type="submit" value="Update Product" />
        </form>
    </c:if>
</body>
</html>

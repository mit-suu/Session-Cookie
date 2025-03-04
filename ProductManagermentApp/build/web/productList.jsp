<%-- 
    Document   : productList
    Created on : Mar 4, 2025, 1:07:39 AM
    Author     : mitsu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product List</title>

        <style>

            table{

                border: 1px solid black;
                border-collapse: collapse;
            }

            th,td{

                border: 1px solid black;
                padding: 0.5em;
            }

        </style>

    </head>

    <body>

        <h1>Product List</h1>

        <table>

            <tr>

                <th>Code</th>

                <th>Description</th>

                <th>Price</th>

                <th>Action</th>

                <th>Action</th>

            </tr>

            <c:forEach var="product" items="${products}">

                <tr>

                    <td>${product.code}</td>

                    <td>${product.description}</td>

                    <td>${product.price}</td>

                    <td><a href="ProductServlet?action=delete&id=${product.id}">
                            Delete
                        </a></td>
                    <td><a href="ProductServlet?action=edit&id=${product.id}">
                            Edit
                        </a></td>


                </tr>

            </c:forEach>

        </table>
        <h2><a href="addProduct.jsp">Add New Product</a></h2>
    </body>

</html>
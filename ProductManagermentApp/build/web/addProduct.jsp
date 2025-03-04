<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add New Product</title>
        <meta http-equiv="Content-Type" content="text/html;
              charset=UTF-8">
        <link rel="stylesheet" href="main.css">
        <style>
            body{
                font-family: Arial, Helvetica, sans-serif;
                font-size: 11pt;
                margin-left: 2em;
                margin-right: 2em;
            }
            h1{
                color: teal;
            }
            label{
                float: left;
                width: 7em;
                margin-bottom: .5em;
            }
            input[type="text"]{
                width: 15em;
                margin-left: .5em;
                margin-bottom: .5em;
            }
            br{
                clear: both;
            }
            #submit{
                margin-left: 0.5em;
            }
        </style>
    </head>
    <body>
        <h1>Add New Product</h1>
        <form action="ProductServlet" method="post">
            <input type="hidden" name="action" value="addnew">
            <label>Code(*):</label>
            <input type="text" name="code" > <br/>
            <label>Description(*):</label>
            <input type="text" name="description" > <br/>
            <label>Price(*):</label>
            <input type="text" name="price" > <br/>
            <label>&nbsp;</label>
            <input type="submit" value="Add Product" id="submit"> <br/>
        </form>
        <p style="color:red; font-weight: bold">${errorString}</p>
    </body>
</html>

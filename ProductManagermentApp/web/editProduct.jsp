<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Product</title>
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

        <h1>Edit Product</h1>

        <form action="ProductServlet" method="post">
            <input type="hidden" name="action" value="update" >
            <input type="hidden" name="idP" value="${product.id}" >
            <label>Code(*):</label>
            <input type="text" name="code" value="${product.code}"
                   > <br>
            <label>Description(*):</label>
            <input type="text" name="description"
                   value="${product.description}" required> <br>
            <label>Price(*):</label>
            <input type="text" name="price" value="${product.price}"
                   required> <br>
            <label>&nbsp;</label>
            <input type="submit" value="Update" id="submit"> <br>
        </form>
        <p style="color:red; font-weight: bold">${errorString}</p>
    </body>

</html>
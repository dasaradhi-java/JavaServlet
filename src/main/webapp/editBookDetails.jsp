<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Book</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }

        table {
            width: 100%;
        }

        td {
            padding: 12px;
            text-align: left;
        }

        input[type=text] {
            width: 100%;
            padding: 8px;
            margin: 4px 0;
            box-sizing: border-box;
        }

        input[type=submit], input[type=reset] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <form action="editurl" method="post">
        <input type="hidden" name="id" value="${param.id}">
        <table align="center">
            <tr>
                <td>Book Name</td>
                <td><input type="text" name="bookName" value="${requestScope.bookName}"></td>
            </tr>
            <tr>
                <td>Book Edition</td>
                <td><input type="text" name="bookEdition" value="${requestScope.bookEdition}"></td>
            </tr>
            <tr>
                <td>Book Price</td>
                <td><input type="text" name="bookPrice" value="${requestScope.bookPrice}"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Edit"></td>
                <td><input type="reset" value="Cancel"></td>
            </tr>
        </table>
    </form>
    <a href="index.html">Home</a>
</body>
</html>

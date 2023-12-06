<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            flex-direction: column;
        }

        h2 {
            color: #4CAF50;
            text-align: center;
        }

        a {
            color: #007bff;
            text-decoration: none;
            margin: 5px;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h2>
        <% 
            int count = (Integer) request.getAttribute("count");
            if (count == 1) {
                out.print("Record is Edited Successfully");
            } else {
                out.print("Record is not Edited Successfully");
            }
        %>
    </h2>
    <a href='index.html'>Home</a>
    <a href='BookList'>Book List</a>
</body>
</html>

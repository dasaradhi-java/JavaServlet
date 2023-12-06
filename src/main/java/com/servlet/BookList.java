package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookList")
public class BookList extends HttpServlet {
	public static final String query = "select * from BookTable";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter printWriter = resp.getWriter();
		resp.setContentType("text/html");

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query);) {

			ResultSet rs = ps.executeQuery();
			
			
			printWriter.println("<html>");
            printWriter.println("<head>");
            printWriter.println("<title>Book List</title>");
            printWriter.println("<style>");
            printWriter.println("table { border-collapse: collapse; width: 80%; margin: 20px auto; }");
            printWriter.println("th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }");
            printWriter.println("th { background-color: #4CAF50; color: white; }");
            printWriter.println("tr:hover { background-color: #f5f5f5; }");
            printWriter.println("a { color: #007bff; text-decoration: none; }");
            printWriter.println("a:hover { text-decoration: underline; }");
            printWriter.println("</style>");
            printWriter.println("</head>");
            printWriter.println("<body>");
			
			printWriter.println("<table border ='1' align='center'>");
			printWriter.println("<tr>");
			printWriter.println("<th>id</th>");
			printWriter.println("<th>BookName</th>");
			printWriter.println("<th>BookEdition</th>");
			printWriter.println("<th>Bookprice</th>");
			printWriter.println("<th>Edit</th>");
			printWriter.println("<th>Delete</th>");
			printWriter.println("</tr>");
			while (rs.next()) {
				printWriter.println("<tr>");
				printWriter.println("<td>" + rs.getInt(1) + "</td>");
				printWriter.println("<td>" + rs.getString(2) + "</td>");
				printWriter.println("<td>" + rs.getString(3) + "</td>");
				printWriter.println("<td>" + rs.getFloat(4) + "</td>");
				printWriter.println("<td><a href='EditScreen?id="+rs.getInt(1)+"'>Edit</td>");
				printWriter.println("<td><a href='DeleteScreen?id="+rs.getInt(1)+"'>delete</td>");
				printWriter.println("</tr>");
			}
			printWriter.println("</table>");
		} catch (SQLException e) {
			e.printStackTrace();
			printWriter.println("<h1>" + e.getMessage() + "</h2>");
		}
		printWriter.println("<a href='index.html' style='text-align: center;'>Home</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}

package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final String query = "insert INTO BookTable(BOOKNAME,BOOKEDITION,BOOKPRICE) Values(?,?,?)";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter printWriter = resp.getWriter();
		resp.setContentType("text/html");
		String bookName = req.getParameter("bookName");
		String bookEdition = req.getParameter("bookEdition");
		float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:sqlserver://localhost:1433;databaseName=TestDataBase;user=Sa;password=Sql@123;encrypt=true;trustServerCertificate=true";
		try (Connection con = DriverManager.getConnection(url); PreparedStatement ps = con.prepareStatement(query);) {
			ps.setString(1, bookName);
			ps.setString(2, bookEdition);
			ps.setFloat(3, bookPrice);
			int count = ps.executeUpdate();
			if (count == 1) {
				printWriter.println("record updated successfully");

			} else {
				printWriter.println("recod not updated");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			printWriter.println("<h1>" + e.getMessage() + "</h2>");
		}
		printWriter.println("<br>");
		printWriter.println("<a href='index.html' align='center'>Home</a>");
		printWriter.println("<br>");
		printWriter.println("<a href='BookList'>bookList</a>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}

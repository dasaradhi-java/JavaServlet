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

@WebServlet("/editurl")
public class EditBook extends HttpServlet {

	private static final String query = "update booktable set BOOKNAME=?,BOOKEDITION=?,BOOKPRICE=? where id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    // get PrintWriter
	    PrintWriter pw = res.getWriter();
	    // set content type
	    res.setContentType("text/html");
	    // get the id of record
	    int id = Integer.parseInt(req.getParameter("id"));
	    // get the edit data we want to edit
	    String bookName = req.getParameter("bookName");
	    String bookEdition = req.getParameter("bookEdition");
	    float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
	    // LOAD jdbc driver
	    try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    } catch (ClassNotFoundException cnf) {
	        cnf.printStackTrace();
	    }
	    String url = "jdbc:sqlserver://localhost:1433;databaseName=TestDataBase;user=Sa;password=Sql@123;encrypt=true;trustServerCertificate=true";
	    try (Connection con = DriverManager.getConnection(url);
	         PreparedStatement ps = con.prepareStatement(query);) {

	        ps.setString(1, bookName);
	        ps.setString(2, bookEdition);
	        ps.setFloat(3, bookPrice);
	        ps.setInt(4, id);
	        int count = ps.executeUpdate();

	        pw.println("<html>");
	        pw.println("<head>");
	        pw.println("<title>Edit Result</title>");
	        pw.println("<style>");
	        pw.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; flex-direction: column; }");
	        pw.println("h2 { color: #4CAF50; text-align: center; }");
	        pw.println("a { color: #007bff; text-decoration: none; margin: 5px; }");
	        pw.println("a:hover { text-decoration: underline; }");
	        pw.println("</style>");
	        pw.println("</head>");
	        pw.println("<body>");

	        if (count == 1) {
	            pw.println("<h2>Record is Edited Successfully</h2>");
	        } else {
	            pw.println("<h2>Record is not Edited Successfully</h2>");
	        }

	        pw.println("<a href='index.html'>Home</a>");
	        pw.println("<a href='BookList'>Book List</a>");

	        pw.println("</body>");
	        pw.println("</html>");

	    } catch (SQLException se) {
	        se.printStackTrace();
	        pw.println("<h1>" + se.getMessage() + "</h2>");
	    } catch (Exception e) {
	        e.printStackTrace();
	        pw.println("<h1>" + e.getMessage() + "</h2>");
	    }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}

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
@WebServlet("/EditScreen")
public class EditBookList extends HttpServlet {
	private static final String query = "SELECT BOOKNAME,BOOKEDITION,BOOKPRICE FROM Booktable where id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		int id = Integer.parseInt(req.getParameter("id"));
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		String url = "jdbc:sqlserver://localhost:1433;databaseName=TestDataBase;user=Sa;password=Sql@123;encrypt=true;trustServerCertificate=true";
		try (Connection con = DriverManager.getConnection(url);
				PreparedStatement ps = con.prepareStatement(query);){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			pw.println("<html>");
            pw.println("<head>");
            pw.println("<title>Edit Book</title>");
            pw.println("<style>");
            pw.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
            pw.println("form { background-color: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); width: 400px; text-align: center; }");
            pw.println("table { width: 100%; }");
            pw.println("td { padding: 12px; text-align: left; }");
            pw.println("input[type=text] { width: 100%; padding: 8px; margin: 4px 0; box-sizing: border-box; }");
            pw.println("input[type=submit], input[type=reset] { background-color: #4CAF50; color: white; padding: 10px 20px; margin: 8px 0; border: none; border-radius: 4px; cursor: pointer; }");
            pw.println("a { color: #007bff; text-decoration: none; }");
            pw.println("a:hover { text-decoration: underline; }");
            pw.println("</style>");
            pw.println("</head>");
            pw.println("<body>");
			
			pw.println("<form action='editurl?id="+id+"' method='post'>");
			pw.println("<table align='center'>");
			pw.println("<tr>");
			pw.println("<td>Book Name</td>");
			pw.println("<td><input type='text' name='bookName' value='"+rs.getString(1)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Book Edition</td>");
			pw.println("<td><input type='text' name='bookEdition' value='"+rs.getString(2)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td>Book Price</td>");
			pw.println("<td><input type='text' name='bookPrice' value='"+rs.getFloat(3)+"'></td>");
			pw.println("</tr>");
			pw.println("<tr>");
			pw.println("<td><input type='submit' value='Edit'></td>");
			pw.println("<td><input type='reset' value='cancel'></td>");
			pw.println("</tr>");
			pw.println("</table>");
			pw.println("</form>");
		}catch(SQLException se) {
			se.printStackTrace();
			pw.println("<h1>"+se.getMessage()+"</h2>");
		}catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h2>");
		}
		pw.println("<a href='index.html'>Home</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
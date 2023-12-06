package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditBook extends HttpServlet {

	 protected void doPost(HttpServletRequest req, HttpServletResponse res) 
	            throws ServletException, IOException {
	        PrintWriter pw = res.getWriter();
	        res.setContentType("text/html");

	        // get the id of record
	        int id = Integer.parseInt(req.getParameter("id"));
	        // get the edit data we want to edit
	        String bookName = req.getParameter("bookName");
	        String bookEdition = req.getParameter("bookEdition");
	        float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));

	        try (Connection con = DatabaseConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement("UPDATE Booktable SET BOOKNAME=?, BOOKEDITION=?, BOOKPRICE=? WHERE id=?")) {
	            ps.setString(1, bookName);
	            ps.setString(2, bookEdition);
	            ps.setFloat(3, bookPrice);
	            ps.setInt(4, id);
	            
	            int count = ps.executeUpdate();
	            req.setAttribute("count", count);
	            req.getRequestDispatcher("editBookUpdate.jsp").forward(req, res);
	        } catch (SQLException se) {
	            se.printStackTrace();
	            pw.println("<h1>" + se.getMessage() + "</h2>");
	        } catch (Exception e) {
	            e.printStackTrace();
	            pw.println("<h1>" + e.getMessage() + "</h2>");
	        }
	    }
	}

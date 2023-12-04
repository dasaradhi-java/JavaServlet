package com.registerDetails;

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

import com.servlet.DatabaseConnection;
@WebServlet("/userCreation")
public class RegisterDetails extends HttpServlet {

	private static final String query = "insert into UserManagementDetails (username,password,gender,dob,city,email,mobile,role)values(?,?,?,?,?,?,?,?)";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    PrintWriter pw = resp.getWriter();
	    resp.setContentType("text/html");
	    String username = req.getParameter("username");
	    // Check if the username already exists
	    if (isUsernameExists(username)) {
	        pw.println("<h2 text-align='center'>Username already exists. Please choose a different username.</h2>");
	        return; // Don't proceed further
	    }
	    String password = req.getParameter("password");
	    String gender = req.getParameter("gender");
	    String dob = req.getParameter("dob");
	    String city = req.getParameter("city");
	    String email = req.getParameter("email");
	    String mobile = req.getParameter("mobile");
	    String role = req.getParameter("role");

	    try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query);) {
	        ps.setString(1, username);
	        ps.setString(2, password);
	        ps.setString(3, gender);
	        ps.setString(4, dob);
	        ps.setString(5, city);
	        ps.setString(6, email);
	        ps.setString(7, mobile);
	        ps.setString(8, role);
	        int count = ps.executeUpdate();
	        if (count == 1) {
	            pw.println("<h2 text-align='center'>User Created Successfully</h2>");
	            pw.println("<br>");
	            pw.println("<h2>Your userId is " + username);
	            pw.println("<p> Click Here to<a href='login.html'>  login</a></P>");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	// Method to check if the username already exists
	private boolean isUsernameExists(String username) {
	    String checkQuery = "SELECT * FROM UserManagementDetails WHERE username = ?";
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(checkQuery)) {

	        preparedStatement.setString(1, username);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            // Check if any row is returned
	            return resultSet.next();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Log the exception or handle it appropriately
	        return false;
	    }
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}

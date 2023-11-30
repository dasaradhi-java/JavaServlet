package com.registerDetails;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.DatabaseConnection;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		// Perform authentication (check against the database)
		UserAuthenticationResult authResult = authenticateUser(username, password);
		//session.getAttribute(authResult.getUsername());

		String userRole = authResult.getRole();
		session.setAttribute("username", username);		
		
		if ("admin".equalsIgnoreCase(userRole)) {
			// Redirect to the view orders page for admin
			response.sendRedirect("viewOrders.html");
		} else {
			// Redirect to the order creation page for other users
		
		response.sendRedirect("createOrder.html");
		}
	}

	private UserAuthenticationResult authenticateUser(String userId, String password) {
	    String query = "SELECT role, username FROM UserManagementDetails WHERE username = ? AND password = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, password);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					String role = resultSet.getString("role");
					String username = resultSet.getString("username");
					return new UserAuthenticationResult(role, username);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Log the exception or handle it appropriately
		}
		return null; // Return null if authentication fails
	}
//    @Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		doGet(req, resp);
//	}
}

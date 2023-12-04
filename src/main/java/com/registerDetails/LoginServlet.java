package com.registerDetails;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		Cookie cookie = new Cookie("exampleCookie", "cookieValue");
		// Mark the cookie as HttpOnly
		cookie.setHttpOnly(true);
		// Add the cookie to the response
		response.addCookie(cookie);

		try {
			UserAuthenticationResult authResult = authenticateUser(username, password);

			if (authResult != null) {
				String userRole = authResult.getRole();
				String username1 = authResult.getUsername();
				session.setAttribute("username", username1);
				session.setAttribute("role", userRole);
				response.sendRedirect("viewOrders.html");
			} else {
				out.println("<html><head><title>Login Error</title></head><body>");
				out.println("<h2 style='color: red;'>Invalid username or password. Please try again.</h2>");
				out.println("<p> clike here to try again <a href='login.html'> Login</a></P>");
			}
		} catch (Exception e) {
			// Handle the custom exception and redirect with an error message
			response.sendRedirect("login.html?error=" + e.getMessage());
		}
	}

	private UserAuthenticationResult authenticateUser(String userId, String password)
			throws UserAuthenticationException {
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
			// Log the exception or handle it appropriately
			throw new UserAuthenticationException("UserName or password not found");
		}
		return null;
	}
}

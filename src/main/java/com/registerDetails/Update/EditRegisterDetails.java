package com.registerDetails.Update;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet.DatabaseConnection;

public class EditRegisterDetails extends HttpServlet {
	String query = "select * from UserManagementDetails where id=?";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		int id = Integer.parseInt(req.getParameter("id"));

		try {
			Connection con = DatabaseConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			req.setAttribute("mobile", rs.getString(1));
			req.setAttribute("email", rs.getString(1));
			req.setAttribute("password", rs.getString(1));

		} catch (SQLException e) {

			req.setAttribute("errorMessage", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", e.getMessage());
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("editRegisterDetails.jsp");
		dispatcher.forward(req, resp);

	}

}

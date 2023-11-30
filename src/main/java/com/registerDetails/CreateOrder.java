package com.registerDetails;

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
import javax.servlet.http.HttpSession;

import com.servlet.DatabaseConnection;

@WebServlet("/OrderServlet")
public class CreateOrder extends HttpServlet {
	String query = "insert into orderTable (productName,orderQuantity,username) values(?,?,?)";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		 HttpSession session=req.getSession();  
	        String username=(String)session.getAttribute("username");  
		String productName = req.getParameter("productName");
		String productQuantity = req.getParameter("productQuantity");
		try (Connection connection = DatabaseConnection.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, productName);
			ps.setString(2, productQuantity);
			ps.setString(3, username);
			Integer count = ps.executeUpdate();
			if (count == 1) {
				pw.println("Order Created Successfully");
				pw.println("<a href='viewOrders.html' style='text-align: center;'>View Orders</a>");

			} else {
				pw.println("Order COuld not be placed");
				//pw.println("<a href='viewOrders.html' style='text-align: center;'>Home</a>");

			}
          //  resp.sendRedirect("createOrder.html");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}

package com.registerDetails;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.DatabaseConnection;

@WebServlet("/ViewOrders")
public class ViewOrdersServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");

		List<OrderEntity> orders = getOrdersFromDatabase(username);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>View Orders</title>");
		out.println("<style>");
		out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
		out.println("h2 { color: #333; text-align: center; }");
		out.println("table { border-collapse: collapse; width: 80%; margin: 20px auto; }");
		out.println("th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }");
		out.println("th { background-color: #4CAF50; color: white; }");
		out.println("tr:hover { background-color: #f5f5f5; }");
		out.println("a { color: #007bff; text-decoration: none; }");
		out.println("a:hover { text-decoration: underline; }");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class='card'>");
		out.println("<h2>View Orders</h2>");


		if (!orders.isEmpty()) {		
			
			
			out.println("<table border ='1' align='center'>");
			out.println("<tr>");
			out.println("<th>Order ID</th>");
			out.println("<th>Product Name</th>");
			out.println("<th>Created Date</th>");
			out.println("<th>Delivery Date</th>");
			out.println("<th>Order Quantity</th>");
			out.println("</tr>");			
			

			for (OrderEntity order : orders) {
				
				out.println("<tr>");
				out.println("<td>" +  order.getOrderId()  + "</td>");
				out.println("<td>" + order.getProductName() + "</td>");
				out.println("<td>" + order.getCreateDate() + "</td>");
				out.println("<td>" + order.getDeliveryDate() + "</td>");
				out.println("<td>" + order.getOrderQuantity() + "</td>");
				out.println("</tr>");				
				
		
			}

			//out.println("</tbody>");
			out.println("</table>");
		} else {
			out.println("<p>No orders available.</p>");
		}

		out.println("<p>Click <a href='logout.html'>here</a> to logout.</p>");
		
	}

	private List<OrderEntity> getOrdersFromDatabase(String username) {
		List<OrderEntity> orders = new ArrayList<>();
		String query = "SELECT * FROM OrderTable WHERE username=?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, username);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					OrderEntity order = new OrderEntity();
					order.setOrderId(resultSet.getInt(1));
					order.setProductName(resultSet.getString("productName"));
					order.setCreateDate(resultSet.getDate("createdDate"));
					order.setDeliveryDate(resultSet.getDate("deliveryDate"));
					order.setOrderQuantity(resultSet.getInt("orderQuantity"));

					orders.add(order);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle exceptions appropriately
		}

		return orders;
	}
}

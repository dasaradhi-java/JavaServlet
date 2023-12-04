package com.registerDetails;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.DatabaseConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "ViewOrders", urlPatterns = {"/ViewOrders"})
public class ViewOrdersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        List<OrderEntity> orders;
        if ("admin".equalsIgnoreCase(role)) {
            orders = getAllOrdersFromDatabase();
        } else {
            orders = getOrdersFromDatabase(username);
        }
        request.setAttribute("orders", orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewOrdersJsp.jsp");
        dispatcher.forward(request, response);
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
        }

        return orders;
    }

    private List<OrderEntity> getAllOrdersFromDatabase() {
        List<OrderEntity> orders = new ArrayList<>();
        String query = "SELECT * FROM OrderTable";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

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
        }

        return orders;
    }
}

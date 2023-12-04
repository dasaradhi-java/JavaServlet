<%@ page import="java.util.List"%>
<%@ page import="com.registerDetails.OrderEntity"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<title>View Orders</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
}

.container {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	min-height: 100vh;
}

h2 {
	color: #333;
	text-align: center;
}

.table-container {
	max-height: 800px;
	overflow-y: auto;
	width: 60%; /* Adjust the width as needed */
	margin: 30px auto; /* Center the container horizontally */
}

table {
	border-collapse: collapse;
	width: 100%;
}

thead th {
	position: sticky;
	top: 0;
	background-color: #4CAF50;
	color: white;
}

th, td {
	padding: 12px;
	text-align: center;
	border-bottom: 0.1px solid #ddd;
}

tr:hover {
	background-color: #f5f5f5;
}

a {
	color: #007bff;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class='card'>
		<h2>View Orders</h2>
		<c:if test="${not empty orders}">
			<div class='table-container'>
				<table border='1'>
					<thead>
						<tr>
							<th>Order ID</th>
							<th>Product Name</th>
							<th>Created Date</th>
							<th>Delivery Date</th>
							<th>Order Quantity</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="order" items="${orders}">
							<tr>
								<td>${order.orderId}</td>
								<td>${order.productName}</td>
								<td><fmt:formatDate value="${order.createDate}"
										pattern="yyyy-MM-dd" /></td>
								<td><fmt:formatDate value="${order.deliveryDate}"
										pattern="yyyy-MM-dd" /></td>
								<td>${order.orderQuantity}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
		<c:if test="${empty orders}">
			<p>No orders available.</p>
		</c:if>
		<p>
			Click <a href='logout.html'>here</a> to logout.
		</p>
	</div>
</body>
</html>

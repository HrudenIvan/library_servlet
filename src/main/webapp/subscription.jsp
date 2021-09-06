<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
  border:1px solid black;
}
</style>
<meta charset="UTF-8">
<title>Subscription</title>
</head>
<body>
	${navigation}
	<hr>
	<h1>Subscription of ${user.firstName} ${user.lastName}</h1>
	<table>
		<tr>
			<th>Order Date</th>
			<th>Book title</th>
			<th>Order type</th>
			<th>Open Date</th>
			<th>close Date</th>
			<th>Order status</th>
			<th>Penalty</th>
			<th>Action</th>
		</tr>
		<c:forEach var="bookOrder" items="${bookOrders}">
			<tr>
				<td><c:out value="${bookOrder.orderDate}"/></td>
				<td><c:out value="${bookOrder.bookTitle}"/></td>
				<td><c:out value="${bookOrder.orderType}"/></td>
				<td><c:out value="${bookOrder.openDate}"/></td>
				<td><c:out value="${bookOrder.closeDate}"/></td>
				<td><c:out value="${bookOrder.orderStatus.status}"/></td>
				<td><c:out value="${bookOrder.penalty}"/></td>
				<td><a href="main?action=prepareBookOrderUpdate&uId=${bookOrder.userId}&bId=${bookOrder.bookId}">Update</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
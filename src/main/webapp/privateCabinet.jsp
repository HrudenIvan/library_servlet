<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${currentUser.firstName} ${currentUser.lastName}</title>
</head>
<body>
	${navigation}
	<hr>
	<h1>Private Cabinet</h1>
	Login: ${currentUser.login}<br>
	First name: ${currentUser.firstName}<br>
	Last name: ${currentUser.lastName}<br>
	Total penalty: ${currentUser.penalty}<br>
	Blocked: ${currentUser.isBlocked}<br><br>

	<h3>Books in use</h3>
	<table>
		<tr>
			<td>Order date</td>
			<td>Book title</td>
			<td>Order type</td>
			<td>Order status</td>
			<td>Open date</td>
			<td>Close date</td>
			<td>Penalty</td>
		</tr>
		<c:forEach var="bookOrder" items="${bookOrders}">
			<tr>
				<td><c:out value="${bookOrder.orderDate}"/></td>
				<td><c:out value="${bookOrder.bookTitle}"/></td>
				<td><c:out value="${bookOrder.orderType}"/></td>
				<td><c:out value="${bookOrder.orderStatus.status}"/></td>
				<td><c:out value="${bookOrder.openDate}"/></td>
				<td><c:out value="${bookOrder.closeDate}"/></td>
				<td><c:out value="${bookOrder.penalty}"/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
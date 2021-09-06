<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">    
           #left { width:50%;height:800px;overflow:auto;float:left; }
           #right { width:50%;height:800px;overflow:auto; } 
</style>
<title>Librarian page</title>
</head>
<body>
	${navigation}
	<hr>
	<h1>Librarian page</h1>
	Login: ${currentUser.login}<br>
	First name: ${currentUser.firstName}<br>
	Last name: ${currentUser.lastName}<br>
	Blocked: ${currentUser.isBlocked}<br><br>
	
	<div id="left">
		<h3>New books orders</h3>
		<table>
			<tr>
				<th>Order date</th>
				<th>Book title</th>
				<th>Order type</th>
				<th>Order status</th>
				<th>Action</th>
			</tr>
			<c:forEach var="bookOrder" items="${bookOrders}">
				<tr>
					<td><c:out value="${bookOrder.orderDate}"/></td>
					<td><c:out value="${bookOrder.bookTitle}"/></td>
					<td><c:out value="${bookOrder.orderType}"/></td>
					<td><c:out value="${bookOrder.orderStatus.status}"/></td>
					<td><a href="main?action=prepareBookOrderUpdate&uId=${bookOrder.userId}&bId=${bookOrder.bookId}">Process</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="right">
	 	<h3>Users list with open orders</h3>
	 	<table>
	 		<tr>
	 			<th>Login</th>
	 			<th>First name</th>
	 			<th>Last name</th>
	 			<th>Subscription</th>
	 		</tr>
	 		<c:forEach var="user" items="${users}">
	 			<tr>
	 				<td><c:out value="${user.login}"/></td>
	 				<td><c:out value="${user.firstName}"/></td>
	 				<td><c:out value="${user.lastName}"/></td>
	 				<td><a href="main?action=subscription&uId=${user.id}">Show subscription</a></td>
	 			</tr>
	 		</c:forEach>
	 	</table>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update book order</title>
</head>
<body>
	${navigation}
	<hr>
	<h1>Update book order</h1>
	<form action="main?action=updateBookOrder" method="post">
		<input type="hidden" name="uId" value="${bookOrder.userId}">
		<input type="hidden" name="bId" value="${bookOrder.bookId}">
		Order date:<input type="datetime-local" value="${bookOrder.orderDate}" readonly><br>
		Book title:<input type="text" value="${bookOrder.bookTitle}" readonly><br>
		Order type:<input type="text" value="${bookOrder.orderType}" readonly><br>
		Open date:<input type="date" name="openDate" value="${bookOrder.openDate}" readonly><br>
		Close date:<input type="date" name="closeDate" value="${bookOrder.closeDate}"><br>
		Penalty:<input type="text" value="${bookOrder.penalty}" readonly><br>
		Order status: <select name="osId">
			<option value="${bookOrder.orderStatus.id}" selected>${bookOrder.orderStatus.status}</option>
			<c:forEach var="orderStatus" items="${orderStatuses}">
				<option value="${orderStatus.id}">${orderStatus.status}</option>
			</c:forEach>
		</select><br><br>
		<input type="submit" value="Update">
	</form>

</body>
</html>
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
		Order date:<input type="datetime-local" name="orderDate" value="${bookOrder.orderDate}" readonly><br>
		Book title:<input type="text" name="title" value="${bookOrder.bookTitle}" readonly><br>
		Order type:<input type="text" name="orderType" value="${bookOrder.orderType}" readonly><br>
		Open date:<input type="date" name="openDate" value="${bookOrder.openDate}" readonly><br>
		Close date:<input type="date" name="closeDate" value="${bookOrder.closeDate}">
		<input type="hidden" name="oldCloseDate" value="${bookOrder.closeDate}">
		<label style="color:red">${errors.closeDate}</label><br>
		Penalty:<input type="text" name="penalty" value="${bookOrder.penalty}" readonly><br>
		Order status: <select name="osId">
			<c:set var="osId" value="${bookOrder.orderStatus.id}"/>
			<c:forEach var="orderStatus" items="${orderStatuses}">
				<c:set var="curOsId" value="${orderStatus.id}"/>
				<option value="${orderStatus.id}"
					<c:if test="${osId == curOsId}">
						selected
					</c:if>
				>${orderStatus.status}</option>
			</c:forEach>
		</select>
		<label style="color:red">${errors.orderStatus}</label>
		<input type="hidden" name="oldOsId" value="${bookOrder.orderStatus.id}"><br><br>
		<input type="submit" value="Update">
	</form>

</body>
</html>
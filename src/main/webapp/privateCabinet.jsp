<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="privateCabinet"/>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table, th, td {
  border:1px solid black;
}
</style>
<meta charset="UTF-8">
<title>${currentUser.firstName} ${currentUser.lastName}</title>
</head>
<body>
	${navigation}
	<hr>
	<h1><c:if test="${isBlocked}"><label style="color:red"><fmt:message key="privateCabinet.blocked"/></label></c:if></h1>
	
	<h1><fmt:message key="privateCabinet.header"/></h1>
	
	<fmt:message key="privateCabinet.login"/> ${currentUser.login}<br>
	<fmt:message key="privateCabinet.firstName"/> ${currentUser.firstName}<br>
	<fmt:message key="privateCabinet.lastName"/> ${currentUser.lastName}<br>
	<fmt:message key="privateCabinet.totalPenalty"/><fmt:formatNumber value="${currentUser.penalty}" type="currency"/> <br><br>

	<h3><fmt:message key="privateCabinet.booksInUse"/></h3>
	<table>
		<tr>
			<th><fmt:message key="privateCabinet.table.orderDate"/></th>
			<th><fmt:message key="privateCabinet.table.bookTitle"/></th>
			<th><fmt:message key="privateCabinet.table.orderType"/></th>
			<th><fmt:message key="privateCabinet.table.orderStatus"/></th>
			<th><fmt:message key="privateCabinet.table.openDate"/></th>
			<th><fmt:message key="privateCabinet.table.closeDate"/></th>
			<th><fmt:message key="privateCabinet.table.penalty"/></th>
		</tr>
		<c:forEach var="bookOrder" items="${bookOrders}">
			<tr>
				<td><c:out value="${bookOrder.orderDate}"/></td>
				<td><c:out value="${bookOrder.bookTitle}"/></td>
				<td><c:out value="${bookOrder.orderType}"/></td>
				<td><c:out value="${bookOrder.orderStatus.status}"/></td>
				<td><c:out value="${bookOrder.openDate}"/></td>
				<td><c:out value="${bookOrder.closeDate}"/></td>
				<td><fmt:formatNumber value="${bookOrder.penalty}" type="currency"/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
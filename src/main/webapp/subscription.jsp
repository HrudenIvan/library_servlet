<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="subscription"/>
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
  border:1px solid black;
}
</style>
<meta charset="UTF-8">
<title><fmt:message key="title"/></title>
</head>
<body>
	${navigation}
	<hr>
	<h1><fmt:message key="header"/> ${user.firstName} ${user.lastName}</h1>
	<table>
		<tr>
			<th><fmt:message key="orderDate"/></th>
			<th><fmt:message key="bookTitle"/></th>
			<th><fmt:message key="orderType"/></th>
			<th><fmt:message key="openDate"/></th>
			<th><fmt:message key="closeDate"/></th>
			<th><fmt:message key="orderStatus"/></th>
			<th><fmt:message key="penalty"/></th>
			<th><fmt:message key="update"/></th>
		</tr>
		<c:forEach var="bookOrder" items="${bookOrders}">
			<tr>
				<td><c:out value="${bookOrder.orderDate}"/></td>
				<td><c:out value="${bookOrder.bookTitle}"/></td>
				<td><c:out value="${bookOrder.orderType}"/></td>
				<td><c:out value="${bookOrder.openDate}"/></td>
				<td><c:out value="${bookOrder.closeDate}"/></td>
				<td><c:out value="${bookOrder.orderStatus.status}"/></td>
				<td><fmt:formatNumber value="${bookOrder.penalty}" type="currency"/></td>
				<td><a href="main?action=prepareBookOrderUpdate&uId=${bookOrder.userId}&bId=${bookOrder.bookId}"><fmt:message key="button"/></a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
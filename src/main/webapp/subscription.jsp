<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="subscription" />
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title><fmt:message key="title" /></title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="main?action=default"><fmt:message
					key="nav.lib"/></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link" href="main?action=default"><fmt:message
					key="nav.home"/></a></a> <a
						class="nav-link" href="main?action=logout"><fmt:message
					key="nav.logout"/></a>
				</div>
			</div>
		</div>
		<div class="d-flex">
			<div class="navbar-nav">
				<a class="nav-link"
					href="main?action=changeLocale&language=ru&country=RU">RU</a> <a
					class="nav-link"
					href="main?action=changeLocale&language=en&country=US">Eng</a>
			</div>
		</div>
	</nav>
	<br>
	<br>
	<div style="margin: 20px;">
		<h1>
			<fmt:message key="header" />
			${user.firstName} ${user.lastName}
		</h1>
		<br> <br>
		<table class="table table-striped">
			<tr>
				<th><fmt:message key="orderDate" /></th>
				<th><fmt:message key="bookTitle" /></th>
				<th><fmt:message key="orderType" /></th>
				<th><fmt:message key="openDate" /></th>
				<th><fmt:message key="closeDate" /></th>
				<th><fmt:message key="orderStatus" /></th>
				<th><fmt:message key="penalty" /></th>
				<th><fmt:message key="update" /></th>
			</tr>
			<c:forEach var="bookOrder" items="${bookOrders}">
				<tr>
					<td><ctg:format value="${bookOrder.orderDate}"/></td>
					<td><c:out value="${bookOrder.bookTitle}" /></td>
					<td><c:out value="${bookOrder.orderType}" /></td>
					<td><ctg:format value="${bookOrder.openDate}"/></td>
					<td><ctg:format value="${bookOrder.closeDate}"/></td>
					<td><c:out value="${bookOrder.orderStatus.status}" /></td>
					<td><fmt:formatNumber value="${bookOrder.penalty}"
							type="currency" /></td>
					<td><a class="btn btn-outline-secondary"
						href="main?action=prepareBookOrderUpdate&uId=${bookOrder.userId}&bId=${bookOrder.bookId}"><fmt:message
								key="button" /></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="privateCabinet" />
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
</style>
<meta charset="UTF-8">
<title>${currentUser.firstName}${currentUser.lastName}</title>
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
					key="nav.home"/></a> <a
						class="nav-link active" href=main?action=prepareCabinet><fmt:message
					key="nav.privateCabinet"/></a> <a class="nav-link" href="main?action=logout"><fmt:message
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
			<c:if test="${isBlocked}">
				<label style="color: red"><fmt:message
						key="privateCabinet.blocked" /></label>
			</c:if>
		</h1>

		<h1>
			<fmt:message key="privateCabinet.header" />
		</h1>
		<br><br>
		<table>
			<tr>
				<th><fmt:message key="privateCabinet.login" /></th>
				<td>${currentUser.login}</td>
			</tr>
			<tr>
				<th><fmt:message key="privateCabinet.firstName" /></th>
				<td>${currentUser.firstName}</td>
			</tr>
			<tr>
				<th><fmt:message key="privateCabinet.lastName" /></th>
				<td>${currentUser.lastName}</td>
			</tr>
			<tr>
				<th><fmt:message key="privateCabinet.totalPenalty" /></th>
				<td><fmt:formatNumber value="${currentUser.penalty}"
						type="currency" /></td>
			</tr>
		</table>
		<br> <br>

		<h3>
			<fmt:message key="privateCabinet.booksInUse" />
		</h3>
		<table class="table table-striped">
			<tr>
				<th><fmt:message key="privateCabinet.table.orderDate" /></th>
				<th><fmt:message key="privateCabinet.table.bookTitle" /></th>
				<th><fmt:message key="privateCabinet.table.orderType" /></th>
				<th><fmt:message key="privateCabinet.table.orderStatus" /></th>
				<th><fmt:message key="privateCabinet.table.openDate" /></th>
				<th><fmt:message key="privateCabinet.table.closeDate" /></th>
				<th><fmt:message key="privateCabinet.table.penalty" /></th>
			</tr>
			<c:forEach var="bookOrder" items="${bookOrders}">
				<tr>
					<td><ctg:format value="${bookOrder.orderDate}"/></td>
					<td><c:out value="${bookOrder.bookTitle}" /></td>
					<td><c:out value="${bookOrder.orderType}" /></td>
					<td><c:out value="${bookOrder.orderStatus.status}" /></td>
					<td><ctg:format value="${bookOrder.openDate}"/></td>
					<td><ctg:format value="${bookOrder.closeDate}"/></td>
					<td><fmt:formatNumber value="${bookOrder.penalty}"
							type="currency" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
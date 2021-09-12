<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="librarian" />
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
<style type="text/css">
#left {
	width: 49%;
	overflow: auto;
	float: left;
}

#right {
	width: 49%;
	overflow: auto;
	float: right;
}
</style>
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
					<a class="nav-link active" href="main?action=default"><fmt:message
					key="nav.home"/></a> <a class="nav-link" href="main?action=logout"><fmt:message
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
						key="librarian.blocked" /></label>
			</c:if>
		</h1>
		<h1>
			<fmt:message key="librarian.header" />
		</h1>
		<br>
		<table>
			<tr>
				<th><fmt:message key="librarian.login" /></th>
				<td>${currentUser.login}</td>
			</tr>
			<tr>
				<th><fmt:message key="librarian.firstName" /></th>
				<td>${currentUser.firstName}</td>
			</tr>
			<tr>
				<th><fmt:message key="librarian.lastName" /></th>
				<td>${currentUser.lastName}</td>
			</tr>
		</table>
		<br> <br>

		<div id="left">
			<h3>
				<fmt:message key="librarian.newBooksOrders" />
			</h3>
			<table class="table table-striped">
				<tr>
					<th><fmt:message key="librarian.orderDate" /></th>
					<th><fmt:message key="librarian.title" /></th>
					<th><fmt:message key="librarian.orderType" /></th>
					<th><fmt:message key="librarian.orderStatus" /></th>
					<th><fmt:message key="librarian.process" /></th>
				</tr>
				<c:forEach var="bookOrder" items="${bookOrders}">
					<tr>
						<td><c:out value="${bookOrder.orderDate}" /></td>
						<td><c:out value="${bookOrder.bookTitle}" /></td>
						<td><c:out value="${bookOrder.orderType}" /></td>
						<td><c:out value="${bookOrder.orderStatus.status}" /></td>
						<td><a class="btn btn-outline-secondary"
							href="main?action=prepareBookOrderUpdate&uId=${bookOrder.userId}&bId=${bookOrder.bookId}"><fmt:message
									key="librarian.process" /></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div id="right">
			<h3>
				<fmt:message key="librarian.userslist" />
			</h3>
			<table class="table table-striped">
				<tr>
					<th><fmt:message key="librarian.login" /></th>
					<th><fmt:message key="librarian.firstName" /></th>
					<th><fmt:message key="librarian.lastName" /></th>
					<th><fmt:message key="librarian.subscription" /></th>
				</tr>
				<c:forEach var="user" items="${users}">
					<tr>
						<td><c:out value="${user.login}" /></td>
						<td><c:out value="${user.firstName}" /></td>
						<td><c:out value="${user.lastName}" /></td>
						<td><a class="btn btn-outline-secondary"
							href="main?action=subscription&uId=${user.id}"><fmt:message
									key="librarian.show" /></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
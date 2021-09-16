<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="updateBookOrder" />
<!DOCTYPE html>
<html lang="${locale.language}">
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
					key="nav.home"/></a> <a
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
		</h1>
		<br>
		<br>
		<form action="main?action=updateBookOrder" method="post">
			<input type="hidden" name="uId" value="${bookOrder.userId}">
			<input type="hidden" name="bId" value="${bookOrder.bookId}">
			<input type="hidden" name="oldCloseDate" value="${bookOrder.closeDate}">
			<input type="hidden" name="oldOsId"	value="${bookOrder.orderStatus.id}">
			<table>
				<tr>
					<td><fmt:message key="form.orderDate" /></td>
					<td><input class="form-control" type="datetime-local" name="orderDate"
						value="${bookOrder.orderDate}" readonly></td>
				</tr>
				<tr>
					<td><fmt:message key="form.title" /></td>
					<td><input class="form-control" type="text" name="title"
						value="${bookOrder.bookTitle}" readonly></td>
				</tr>
				<tr>
					<td><fmt:message key="form.orderType" /></td>
					<td><input class="form-control" type="text" name="orderType"
						value="${bookOrder.orderType}" readonly></td>
				</tr>
				<tr>
					<td><fmt:message key="form.openDate" /></td>
					<td><input class="form-control" type="date" name="openDate"
						value="${bookOrder.openDate}" readonly></td>
				</tr>
				<tr>
					<td><fmt:message key="form.closeDate" /></td>
					<td><input class="form-control" type="date" name="closeDate"
						value="${bookOrder.closeDate}"></td><td><label
						style="color: red">${errors.closeDate}</label></td>
				</tr>
				<tr>
					<td><fmt:message key="form.penalty" /></td>
					<td><input class="form-control" type="text" name="penalty"
						value="${bookOrder.penalty}" readonly></td>
				</tr>
				<tr>
					<td><fmt:message key="form.orderStatus" /></td>
					<td><select class="form-select" name="osId">
							<c:set var="osId" value="${bookOrder.orderStatus.id}" />
							<c:forEach var="orderStatus" items="${orderStatuses}">
								<c:set var="curOsId" value="${orderStatus.id}" />
								<option value="${orderStatus.id}"
									<c:if test="${osId == curOsId}">
						selected
					</c:if>>${orderStatus.status}</option>
							</c:forEach>
					</select></td><td> <label style="color: red">${errors.orderStatus}</label></td>
				</tr>
			</table>
			<br>
			<br> <input class="btn btn-outline-secondary" type="submit"
				value="<fmt:message key="form.button"/>">
		</form>
	</div>
</body>
</html>
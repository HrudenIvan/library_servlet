<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="orderBook" />
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
			<fmt:message key="header" />
		</h1>
		<form action="main?action=addBookOrder" method="post">
			<input type="hidden" name="bId" value="${book.id}"> <input
				type="hidden" name="uId" value="${currentUserId}">
			<table>
				<tr>
					<td><fmt:message key="title" /></td>
					<td><input class="form-control" type="text"
						value="${book.title}" readonly></td>
				</tr>
				<tr>
					<td><fmt:message key="author" /></td>
					<td><input class="form-control" type="text"
						value="${book.author.firstName} ${book.author.lastName}" readonly></td>
				</tr>
				<tr>
					<td><fmt:message key="orderType" /></td>
					<td><select class="form-select" name="otId">
							<c:forEach var="orderType" items="${orderTypes}">
								<option value="${orderType.id}">${orderType.type}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><fmt:message key="releaseDate" /></td>
					<td><input class="form-control" type="text"
						value="${book.releaseDate}" readonly></td>
				</tr>
			</table>
			<br>
			<br> <input class="btn btn-outline-secondary" type="submit"
				value="<fmt:message key="button"/>">
		</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="books"/>
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
<style type="text/css">
	.bookEdit {
		display: ${bookEditVis}
	}
	.bookOrder {
		display: ${bookOrderVis}
	}
</style>
<meta charset="UTF-8">
<title><fmt:message key="books.title"/></title>
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
					<c:choose>
						<c:when test="${currentUserType eq 'guest'}">
							<a class="nav-link" href="login.jsp"><fmt:message
					key="nav.login"/></a>
							<a class="nav-link active" href="main?action=default"><fmt:message
					key="nav.home"/></a>
							<a class="nav-link" href="register.jsp"><fmt:message
					key="nav.register"/></a>
						</c:when>
						<c:when test="${currentUserType eq 'user'}">
							<a class="nav-link active" href="main?action=default"><fmt:message
					key="nav.home"/></a>
							<a class="nav-link" href=main?action=prepareCabinet><fmt:message
					key="nav.privateCabient"/></a>
							<a class="nav-link" href="main?action=logout"><fmt:message
					key="nav.logout"/></a>
						</c:when>
						<c:when test="${currentUserType eq 'admin'}">
							<a class="nav-link" href="main?action=default"><fmt:message
					key="nav.home"/></a>
							<a class="nav-link" href="main?action=getAllUsers"><fmt:message
					key="nav.upadteUser"/></a>
							<a class="nav-link active" href="main?action=getAllBooks"><fmt:message
					key="nav.updateBook"/></a>
							<a class="nav-link" href="main?action=addBookLink"><fmt:message
					key="nav.addBook"/></a>
							<a class="nav-link" href="main?action=getAllAuthors"><fmt:message
					key="nav.updateAuthor"/></a>
							<a class="nav-link" href="main?action=addAuthorLink"><fmt:message
					key="nav.addAuthor"/></a>
							<a class="nav-link" href="main?action=getAllPublishers"><fmt:message
					key="nav.updatePublisher"/></a>
							<a class="nav-link" href="main?action=addPublisherLink"><fmt:message
					key="nav.addPublisher"/></a>
							<a class="nav-link" href="main?action=logout"><fmt:message
					key="nav.logout"/></a>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
		<div class="d-flex">
			 <div class="navbar-nav">
				<a class="nav-link" href="main?action=changeLocale&language=ru&country=RU">RU</a>
				<a class="nav-link" href="main?action=changeLocale&language=en&country=US">Eng</a>
		  	</div>
		</div>
	</nav>
	<br>	
	<br>
	<div style="margin: 20px;">
		<h1>
			<fmt:message key="books.header" />
		</h1>
		<br><br>
			<form action="main" method="get">
				<input type="hidden" name="action" value="getAllBooks">
				<fmt:message key="books.form.sort.sortBy"/>
				<select name="sort">
					<c:forEach var="sortByItem" items="${sortBy}">
						<option value="${sortByItem.value}"
							<c:set var="cn" value="${sortByItem.value}"/>
							<c:if test="${cn eq sort}">
							selected
						</c:if>>${sortByItem.title}</option>
					</c:forEach>
				</select>
				<fmt:message key="books.form.sort.orderBy"/>
				<select name="order">
					<c:forEach var="orderByItem" items="${orderBy}">
						<option value="${orderByItem.value}"
							<c:set var="cn" value="${orderByItem.value}"/>
							<c:if test="${cn eq order}">
							selected
						</c:if>>${orderByItem.title}</option>
					</c:forEach>
				</select>
				<input class="btn btn-outline-secondary" type="submit" value="<fmt:message key="books.form.sort.button"/>"><br>
				<fmt:message key="books.form.find.findeByTitle"/>
				<input type="text" name="title" value="${title}">
				<fmt:message key="books.form.main.aLastName"/>
				<input type="text" name="aLastname" value="${aLastname}">
				<fmt:message key="books.form.main.aFirstName"/>
				<input type="text" name="aFirstname" value="${aFirstname}">
				<input class="btn btn-outline-secondary" type="submit" value="<fmt:message key="books.form.find.button"/>">
			</form>
		<br><br>
		<table class="table table-striped">
			<tr>
				<th><fmt:message key="books.form.main.title" /></th>
				<th><fmt:message key="books.form.main.available" /></th>
				<th><fmt:message key="books.form.main.aLastName" /></th>
				<th><fmt:message key="books.form.main.aFirstName" /></th>
				<th><fmt:message key="books.form.main.publisher" /></th>
				<th><fmt:message key="books.form.main.releaseDate" /></th>
				<th class="bookEdit"><fmt:message key="books.form.main.edit" /></th>
				<th class="bookOrder"><fmt:message key="books.form.main.order" /></th>
			</tr>
			<c:forEach var="book" items="${books}">
				<tr>
					<td><c:out value="${book.title}" /></td>
					<td><c:out value="${book.available}" /></td>
					<td><c:out value="${book.author.lastName}" /></td>
					<td><c:out value="${book.author.firstName}" /></td>
					<td><c:out value="${book.publisher.name}" /></td>
					<td><c:out value="${book.releaseDate}" /></td>
					<td class="bookEdit"><a class="btn btn-outline-secondary"
						href="main?action=prepareBook&bId=${book.id}"><fmt:message
								key="books.form.main.edit" /></a></td>
					<td class="bookOrder"><a class="btn btn-outline-secondary"
						href="main?action=prepareBookOrder&bId=${book.id}"><fmt:message
								key="books.form.main.order" /></a></td>
				</tr>
			</c:forEach>
		</table>
		<p>
		${paginationNav}
		</p>
	</div>
</body>
</html>
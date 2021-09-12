<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="updateUser"/>
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
<title><fmt:message key="title"/></title>
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
					<a class="nav-link" href="main?action=default"><fmt:message key="nav.home"/></a>
					<a class="nav-link active" href="main?action=getAllUsers"><fmt:message	key="nav.updateUser"/></a>
					<a class="nav-link" href="main?action=getAllBooks"><fmt:message	key="nav.updateBook"/></a>
					<a class="nav-link" href="main?action=addBookLink"><fmt:message	key="nav.addBook"/></a>
					<a class="nav-link" href="main?action=getAllAuthors"><fmt:message key="nav.updateAuthor"/></a>
					<a class="nav-link" href="main?action=addAuthorLink"><fmt:message key="nav.addAuthor"/></a>
					<a class="nav-link" href="main?action=getAllPublishers"><fmt:message key="nav.updatePublisher"/></a>
					<a class="nav-link" href="main?action=addPublisherLink"><fmt:message key="nav.addPublisher"/></a>
					<a class="nav-link" href="main?action=logout"><fmt:message key="nav.logout"/></a>
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
		<form action="main?action=updateUser" method="post">
			<input type="hidden" name="uId" value="${user.id}">
			<table>
				<tr>
					<td><fmt:message key="login" /></td>
					<td><input class="form-control" type="text" name="login"
						value="${user.login}"></td>
					<td><label style="color: red">${errors.login}</label></td>
				</tr>
				<tr>
					<td><fmt:message key="password" /></td>
					<td><input class="form-control" type="password"
						name="password"></td>
					<td><label style="color: red">${errors.password}</label></td>
				</tr>
				<tr>
					<td><fmt:message key="password1" /></td>
					<td><input class="form-control" type="password"
						name="password1"></td>
				</tr>
				<tr>
					<td><fmt:message key="firstName" /></td>
					<td><input class="form-control" type="text" name="firstName"
						value="${user.firstName}"></td>
					<td><label style="color: red">${errors.firstname}</label></td>
				</tr>
				<tr>
					<td><fmt:message key="lastName" /></td>
					<td><input class="form-control" type="text" name="lastName"
						value="${user.lastName}"></td>
					<td><label style="color: red">${errors.lastname}</label></td>
				</tr>
				<tr>
					<td><fmt:message key="userType" /></td>
					<td><select class="form-select" name="tId">
							<c:set var="curUtId" value="${user.userType.id}" />
							<c:forEach var="ut" items="${userTypes}">
								<option value="${ut.id}" <c:set var="utId" value="${ut.id}"/>
									<c:if test="${curUtId eq utId}">
						selected
					</c:if>>${ut.type}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><fmt:message key="penalty" /></td>
					<td><input class="form-control" type="text" name="penalty"
						value="${user.penalty}"></td>
					<td><label style="color: red">${errors.penalty}</label></td>
				</tr>
				<tr>
					<td><fmt:message key="isBlocked" /></td>
					<td><select class="form-select" name="isBlocked">
							<option value="${user.isBlocked}" selected>${user.isBlocked}</option>
							<option value="false">false</option>
							<option value="true">true</option>
					</select></td>
				</tr>
			</table>
			<br>
			<br> <input class="btn btn-outline-secondary" type="submit"
				value="<fmt:message key="button"/>">
		</form>
	</div>
</body>
</html>
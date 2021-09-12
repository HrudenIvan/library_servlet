<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="register"/>
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
<title><fmt:message key="register.title"/></title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="main?action=default"><fmt:message
					key="nav.lib" /></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link" href="login.jsp"><fmt:message
							key="nav.login" /></a>
					<a class="nav-link" href="main?action=default"><fmt:message
							key="nav.home" /></a>
					<a class="nav-link active" href="register.jsp"><fmt:message
							key="nav.register" /></a>
				</div>
			</div>
		</div>
</nav>
<br>	
	<br>
	<div style="margin:20px;">
<h1><fmt:message key="register.header"/></h1><br>
	<form action="main?action=addUser" method="POST">
	<table>
		<tr><td><fmt:message key="register.form.login"/></td><td><input type="text" name="login" value="${user.login}">
		<label style="color:red">${errors.login}</label></td></tr>
		<tr><td><fmt:message key="register.form.password"/></td><td><input type="password" name="password">
		<label style="color:red">${errors.password}</label></td></tr>
		<tr><td><fmt:message key="register.form.password1"/></td><td><input type="password" name="password1"></td></tr>
		<tr><td><fmt:message key="register.form.firstName"/></td><td><input type="text" name="firstName" value="${user.firstName}">
		<label style="color:red">${errors.firstname}</label></td></tr>
		<tr><td><fmt:message key="register.form.lastName"/></td><td><input type="text" name="lastName" value="${user.lastName}">
		<label style="color:red">${errors.lastname}</label></td></tr>
		</table><br>
		<button type="submit" class="btn btn-outline-secondary"><fmt:message key="register.form.button"/></button>
	</form>
	</div>
</body>
</html>
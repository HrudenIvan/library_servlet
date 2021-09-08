<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body>
${navigation}
<hr>
<h1>Please, register</h1>
	<form action="main?action=addUser" method="POST">
		Login: <input type="text" name="login" value="${user.login}">
		<label style="color:red">${errors.login}</label><br>
		Password: <input type="password" name="password">
		<label style="color:red">${errors.password}</label><br>
		Password: <input type="password" name="password1"><br>
		First name: <input type="text" name="firstName" value="${user.firstName}">
		<label style="color:red">${errors.firstname}</label><br>
		Last name: <input type="text" name="lastName" value="${user.lastName}">
		<label style="color:red">${errors.lastname}</label><br>
		<br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>
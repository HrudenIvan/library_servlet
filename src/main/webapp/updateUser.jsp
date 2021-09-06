<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
</style>
<meta charset="UTF-8">
<title>Update user</title>
</head>
<body>
	${navigation}
	<hr>
	<h1>Update user</h1>
	<form action="main?action=updateUser" method="post">
		<input type="hidden" name="uId" value="${user.id}">
		Login: <input type="text" name="login" value="${user.login}"><br>
		Password: <input type="text" name="password"><br>
		Password: <input type="text" name="password1"><br>
		First name: <input type="text" name="firstName"	value="${user.firstName}"><br>
		Last name: <input type="text" name="lastName" value="${user.lastName}"><br>
		User type:
		<select name="tId">
			<option value="${user.userType.id}" selected>${user.userType.type}</option>
			<c:forEach var="ut" items="${userTypes}">
				<option value="${ut.id}">${ut.type}</option>
			</c:forEach>
		</select><br>
		Penalty:<input type="text" name="penalty" value="${user.penalty}"><br>
		Blocked: <select name="isBlocked">
			<option value="${user.isBlocked}" selected>${user.isBlocked}</option>
			<option value="false">false</option>
			<option value="true">true</option>
		</select><br>
		<input type="submit" value="Update">
	</form>
</body>
</html>
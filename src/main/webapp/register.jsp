<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		Login: <input type="text" name="login"><br>
		Password: <input type="password" name="password"><br>
		Password: <input type="password" name="password1"><br>
		First name: <input type="text" name="firstName"><br>
		Last name: <input type="text" name="lastName"> <br>
		${errorRegistrationMessage}<br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>
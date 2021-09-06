<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Author</title>
</head>
<body>
	${navigation}
	<hr>
	<h1>Update Author</h1>
	<form action="main?action=updateAuthor" method="post">
		<input type="hidden" name="aId" value="${author.id}">
		First name: <input type="text" name="firstName" value="${author.firstName}"><br>
		Last name: <input type="text" name="lastName" value="${author.lastName}"><br><br>
		<input type="submit" value="Update">
	</form>
</body>
</html>
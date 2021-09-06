<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add author</title>
</head>
<body>
	${navigation}
	<hr>
	<h1>Add author</h1>
	<form action="main?action=addAuthor" method="post">
		First name: <input type="text" name="firstName"><br>
		Last name: <input type="text" name="lastName"><br><br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>
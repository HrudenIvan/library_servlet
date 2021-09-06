<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add publisher</title>
</head>
<body>
	${navigation}
	<hr>
	<h1>Add publisher</h1>
	<form action="main?action=addPublisher" method="post">
		Name: <input type="text" name="name"><br><br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>
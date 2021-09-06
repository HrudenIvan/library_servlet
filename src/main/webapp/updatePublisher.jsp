<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update publisher</title>
</head>
<body>
	${navigation}
	<hr>
	<h1>Update publisher</h1>
	<form action="main?action=updatePublisher" method="post">
		<input type="hidden" name="pId" value="${publisher.id}">
		Name:<input type="text" name="name" value="${publisher.name}"><br><br>
		<input type="submit" value="Submit">
	</form>
</body>
</html>
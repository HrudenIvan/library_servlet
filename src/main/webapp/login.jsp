<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
input, select {
  width: 300px;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

div {
  width:375px;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
${navigation}
<hr>
	<h1>Please, login</h1>
	<div>
	<form action="main" method="post">
		<input type="hidden" name="action" value="login">
		<table>
			<tr>
				<td>Login:</td>
				<td><input type="text" name="login" ></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password"></td>
			</tr>
		</table>
		${errorLoginMessage}
		<input type="submit" value="submit">
	</form>
	</div>
</body>
</html>
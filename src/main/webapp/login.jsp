<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="login"/>

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
	width: 375px;
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
<meta charset="UTF-8">
<title><fmt:message key="login.title"/></title>
</head>
<body>
	${navigation}
	<hr>
	<h1><fmt:message key="login.header"/></h1>
	<div>
		<form action="main?action=login" method="post">
			<table>
				<tr>
					<td><fmt:message key="login.form.login"/></td>
					<td><input type="text" name="login" value="${login}"></td>
				</tr>
				<tr>
					<td><fmt:message key="login.form.password"/></td>
					<td><input type="password" name="password"></td>
				</tr>
			</table>
			<label style="color: red">${errorLoginMessage}</label><br> <input
				type="submit" value="<fmt:message key="login.form.button"/>">
		</form>
	</div>
</body>
</html>
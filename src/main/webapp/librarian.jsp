<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="librarian"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">    
	#left { width:50%;height:800px;overflow:auto;float:left; }
    #right { width:50%;height:800px;overflow:auto; } 
    table, th, td {
  		border:1px solid black;
  	}
}
</style>
<title>${currentUser.firstName} ${currentUser.lastName}</title>
</head>
<body>
	${navigation}
	<hr>
	<a href="main?action=changeLocale&language=ru&country=RU">RU</a>
	<a href="main?action=changeLocale&language=en&country=US">Eng</a>
	<h1><c:if test="${isBlocked}"><label style="color:red"><fmt:message key="librarian.blocked"/></label></c:if></h1>
	<h1><fmt:message key="librarian.header"/></h1>
	<fmt:message key="librarian.login"/>Login: ${currentUser.login}<br>
	<fmt:message key="librarian.firstName"/> ${currentUser.firstName}<br>
	<fmt:message key="librarian.lastName"/> ${currentUser.lastName}<br><br>
	
	<div id="left">
		<h3><fmt:message key="librarian.newBooksOrders"/></h3>
		<table>
			<tr>
				<th><fmt:message key="librarian.orderDate"/></th>
				<th><fmt:message key="librarian.title"/></th>
				<th><fmt:message key="librarian.orderType"/></th>
				<th><fmt:message key="librarian.orderStatus"/></th>
				<th><fmt:message key="librarian.process"/></th>
			</tr>
			<c:forEach var="bookOrder" items="${bookOrders}">
				<tr>
					<td><c:out value="${bookOrder.orderDate}"/></td>
					<td><c:out value="${bookOrder.bookTitle}"/></td>
					<td><c:out value="${bookOrder.orderType}"/></td>
					<td><c:out value="${bookOrder.orderStatus.status}"/></td>
					<td><a href="main?action=prepareBookOrderUpdate&uId=${bookOrder.userId}&bId=${bookOrder.bookId}"><fmt:message key="librarian.process"/></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="right">
	 	<h3><fmt:message key="librarian.userslist"/></h3>
	 	<table>
	 		<tr>
	 			<th><fmt:message key="librarian.login"/></th>
	 			<th><fmt:message key="librarian.firstName"/></th>
	 			<th><fmt:message key="librarian.lastName"/></th>
	 			<th><fmt:message key="librarian.subscription"/></th>
	 		</tr>
	 		<c:forEach var="user" items="${users}">
	 			<tr>
	 				<td><c:out value="${user.login}"/></td>
	 				<td><c:out value="${user.firstName}"/></td>
	 				<td><c:out value="${user.lastName}"/></td>
	 				<td><a href="main?action=subscription&uId=${user.id}"><fmt:message key="librarian.show"/></a></td>
	 			</tr>
	 		</c:forEach>
	 	</table>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>User Management Application</title>
</head>

<body>
	<header>
		<div align="center">
			<h1>User Management App</h1>
		</div>
		<div>
			<a href="list">Users</a>
		</div>
	</header>
	<br>


	<div>
		<h3 align="center">
			<b>List of Users</b>
		</h3>
		<hr>
		<div align="left">
			<a href="<%=request.getContextPath()%>/new">Add New User</a>
		</div>
		<br>
		<table border="1" cellpadding="10" align="center" bordercolor="red">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Country</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${userList}">

					<tr>
						<td><c:out value="${user.id}" /></td>
						<td><c:out value="${user.name}" /></td>
						<td><c:out value="${user.email}" /></td>
						<td><c:out value="${user.country}" /></td>
						<td><a href="<%=request.getContextPath()%>/edit?id=<c:out value='${user.id}' />">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp; <a
							href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>

</body>

</html>

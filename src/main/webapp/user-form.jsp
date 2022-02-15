<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>User Management Application</title>
<body>

	<header>
		<div>User Management App</div>
		<div>
			<a href="<%=request.getContextPath()%>/list">Users</a>
		</div>
	</header>
	<br>
	<c:if test="${user != null}">
		<form action="update" method="post">
	</c:if>
	<c:if test="${user == null}">
		<form action="insert" method="post">
	</c:if>

	<caption>
		<h2>
			<c:if test="${user != null}"> Edit User
            </c:if>
			<c:if test="${user == null}"> Add New User
            </c:if>
		</h2>
	</caption>

	<c:if test="${user != null}">
		<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
	</c:if>

	<fieldset>
		<label>User Name</label>
		<input type="text" value="<c:out value='${user.name}' />"name="name" required="required">
	</fieldset>

	<fieldset>
		<label>User Email</label>
		<input type="text" value="<c:out value='${user.email}' />"name="email">
	</fieldset>

	<fieldset>
		<label>User Country</label>
		<input type="text" value="<c:out value='${user.country}' />" name="country">
	</fieldset>

	<button type="submit">Save</button>
	</form>
</body>

</html>

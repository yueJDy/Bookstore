<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<title>Thêm tài khoản</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<base href="${pageContext.servletContext.contextPath}/">
	<link rel = "stylesheet" href="files/css/accounts/login.css">
</head>
<body>
	<%@ include file = "../../page/header.jsp" %>
	
	<div class = "container">
		<div class = "pagelogin">
			<h1>THÊM TÀI KHOẢN</h1>
			<p>
				${message}
			</p>
			<form:form action = "admin/user/insert.htm" modelAttribute = "user" method = "post">
				<p>
					<label>Email: </label>
					<form:input path="email" type = "email"/>
					${email_fail}
				</p>
				<p>
					<label>Tên hiển thị: </label>
					<form:input path="name" type = "text"/>
					${name_fail}
				</p>
				<p>
					<label>Loại tài khoản: </label>
					<form:radiobutton path="isAdmin" value = "true" label = "Quản trị viên"/>
					<form:radiobutton path="isAdmin" value = "false" label = "Thành viên"/>
				</p>
				<p>
					${isAdmin_fail}
				</p>
				<p>
					<input type = "submit" class = "btn" value = "Thêm"/>
				</p>
			</form:form>
			
			
		</div>
	</div>

</body>
</html>
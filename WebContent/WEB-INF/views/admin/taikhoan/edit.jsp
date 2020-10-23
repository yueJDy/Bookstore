<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<title>Chỉnh sửa tài khoản</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<script src='https://www.google.com/recaptcha/api.js?hl=vi'></script>
	<base href="${pageContext.servletContext.contextPath}/">
	<link rel = "stylesheet" href="files/css/accounts/login.css">
	<style>
		#label1{
			display:inline"
		}
	</style>
</head>
<body>
	<%@ include file = "../../page/header.jsp" %>
	
	<div class = "container">
		<div class = "pagelogin">
			<h1>CHỈNH SỬA THÔNG TIN TÀI KHOẢN</h1>
			<p>
				${message}
			</p>
			<form:form action = "admin/user/edit/${user.email}.htm" modelAttribute = "user" method = "post">
				<p>
					<label>Email: ${user.email}</label>
					
				</p>
				<p>
					<label>Tên hiển thị: ${user.name}</label>
				</p>
				<p>
					<label>Loại tài khoản: </label>
					<form:radiobutton path="isAdmin" value = "true" label = "Quản trị viên" />
					<form:radiobutton path="isAdmin" value = "false" label = "Thành viên" />
				</p>
				<div class="g-recaptcha" data-sitekey="6Lf_sdkZAAAAABI29FKzToJ0CasW0jgey-xxvcD6"></div>
				<p>
					${check_fail}
				</p>
				<p>
					<input type = "submit" class = "btn" value = "Cập nhật"/>
				</p>
			</form:form>
			
			
		</div>
	</div>


</body>
</html>
<%@ page pageEncoding="utf-8"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<title>Đăng ký </title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel = "stylesheet" href="files/css/accounts/login.css">
</head>
<body>
	<%@ include file = "../page/header.jsp" %>
	
	<div class = "container">
		<div class = "pagelogin">
			<h1>Đăng ký</h1>
			
			<form:form action = "dang-ky.htm" modelAttribute = "user" method = "post">
				<p>
					<label>Email</label>
					<!-- <input data-val="true" data-val-email="Sai định dạng Email" data-val-required="*" id="email" name="Email" type="email" value="" > -->
					<form:input path="email" type = "email"/>
					<form:errors path = "email"/>
					<span class="field-validation-valid" data-valmsg-for="Email" data-valmsg-replace="true"></span>
				</p>
				<p>
					<label for = "password">Mật khẩu</label>
					<form:input type = "password" path = "password"/>
					<form:errors path = "password"/>
				</p>
				<p>
					<label for = "name">Tên hiển thị</label>
					<form:input name="name" type="text" value="" path = "name"/>
					<form:errors path = "name"/>
				</p>
				<p>
					<input type = "submit" class = "btn" value = "Đăng ký"/>
				</p>
				<p>
				Bạn đã có tài khoản? Hãy 
				<a href = "dang-nhap.htm" class = "btn">Đăng nhập</a>
				</p>
			</form:form>
		</div>
	</div>

	
</body>
</html>
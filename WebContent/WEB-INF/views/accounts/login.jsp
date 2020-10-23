<%@ page pageEncoding="utf-8"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Đăng nhập </title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src='https://www.google.com/recaptcha/api.js?hl=vi'></script>
    <link rel = "stylesheet" href="files/css/accounts/login.css">
    <base href = "${pageContext.servletContext.contextPath}/">
</head>
<body>
	<%@ include file = "../page/header.jsp" %>
	
	
	<div class = "container">
		<div class = "pagelogin">
			<h1>Đăng nhập</h1>
			<c:if test = "${log_fail != null}">
				<p>
					${log_fail}
				</p>
			</c:if>
			<c:if test = "${message_login != null}">
				<p>
					${message_login}
				</p>
			</c:if>
			
			<form:form action = "dang-nhap.htm" modelAttribute = "user" method = "post">
				<p>
					<label for = "email">Email</label>
					<!-- <input data-val="true" data-val-email="Sai định dạng Email" data-val-required="*" id="email" name="Email" type="email" value="" > -->
					<form:input path="email" type = "email"/>
					<form:errors path = "email"/>
				</p>
				<p>
					<label for = "password">Mật khẩu</label>
					<form:input type = "password" path = "password"/>
					<form:errors path = "password"/>
				</p>
				<div class="g-recaptcha" data-sitekey="6Lf_sdkZAAAAABI29FKzToJ0CasW0jgey-xxvcD6"></div>
				<p>
					${check_fail}
				</p>
				<p>
					<input type = "submit" class = "btn" value = "Đăng nhập"/>
				</p>
				<p>
					Chưa có tài khoản? Hãy 
					<a href = "dang-ky.htm" class = "btn">Đăng ký</a>
				</p>
			</form:form>
			
			<fieldset class = "formforget">
				<legend>Quên mật khẩu?</legend>
				<form action = "quen-mat-khau.htm">
					<p>
						<label>Email</label>
						<input type = "email" name  = "email_forget">
						${message}
					</p>
					
					<p>
						<!-- a href = "quen-mat-khau.htm" class = "btn">Tìm lại mật khẩu</a> -->
						<input type = "submit" class = "btn" value = "Tìm lại mật khẩu">
					</p>
				</form>
			</fieldset>
		</div>

		
	</div>


</body>
</html>
<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Xác thực tài khoản</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    
    <base href = "${pageContext.servletContext.contextPath}/">
</head>
<body>
	<%@ include file = "../page/header.jsp" %>
	
	<link rel = "stylesheet" href="files/css/accounts/login.css">
	<div class = "container">
		<div class = "pagelogin">
			<h1>XÁC THỰC TÀI KHOẢN</h1>
			<form action = "user/xac-thuc-tai-khoan.htm" method = "post">
				<p>
					<label>Nhập mã xác thực:</label>
					<input type = "text" name = "verify">
					${verify_fail}
				</p>
				<p>
					<input type = "submit" class = "btn" value = "Xác nhận">
				</p>
			</form>
		</div>
	</div>

</body>
</html>
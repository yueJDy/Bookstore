<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<title>Mua ngay</title>
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
	<%@ include file = "../page/header.jsp" %>
	
	<div class = "container">
		<div class = "pagelogin">
			<h1>Nhập thông tin</h1>
			<form:form action = "gio-hang/thong-tin.htm" modelAttribute = "donhang">
				<p>
					<label>Họ và tên: </label>
					<form:input path="hoten"/>
					${hoten_fail}
				</p>
				<p>
					<label>Số điện thoại:</label>
					<form:input path="sdt"/>
					${sdt_fail}
				</p>
				<p>
					<label>Địa chỉ nhận hàng:</label>
					<form:input path="diachi"/>
					${diachi_fail}
				</p>
				<p>
					<label>Ghi chú:</label>
					<form:textarea path="ghichu" rows= "3" />
					${ghichu_fail}
				</p>
				<p>
					<input type = "submit" class = "btn" value = "Tiếp tục"/>
				</p>
			</form:form>
			
			
		</div>
	</div>
	

</body>
</html>
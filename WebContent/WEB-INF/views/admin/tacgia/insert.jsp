<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<title>Thêm tác giả mới</title>
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
			<h1>THÊM TÁC GIẢ</h1>
			<p>
				${message}
			</p>
			<form:form action = "admin/tacgia/insert.htm" modelAttribute = "tacgia" method = "post">
				<p>
					<label>Tên tác giả: </label>
					<form:input path="tenTG"/>
					${ten_fail}
				</p>
				<p>
					<label>Giới thiệu:</label>
					<form:textarea path="gioiThieu" rows = "5" style = "width:400px"/>
					${gt_fail}
				</p>
				<p>
					<input type = "submit" class = "btn" value = "Thêm"/>
				</p>
			</form:form>
			
			
		</div>
	</div>


</body>
</html>
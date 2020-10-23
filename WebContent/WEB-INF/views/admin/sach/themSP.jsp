<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<title>Thêm sản phẩm</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<script src='https://www.google.com/recaptcha/api.js?hl=vi'></script>
	<base href="${pageContext.servletContext.contextPath}/">
	<link rel = "stylesheet" href="files/css/accounts/login.css">
</head>
<body>
	<%@ include file = "../../page/header.jsp" %>
	
	<div class = "container">
		<div class = "pagelogin">
			<h1>THÊM SẢN PHẨM</h1>
			<form:form action = "admin/sach/addSL/${book.maSach}.htm" modelAttribute = "book" method = "post">
				<p>
					<label>
						Tên sách: <strong style="text-align: left; text-transform: capitalize;">${book.tenSach}</strong>
					</label>
				</p>
				<p>
					<label>Số lượng:</label>
					<input name = "soluong" type = "number" min = 0 value ="${soluong}">
				</p>
				<div class="g-recaptcha" data-sitekey="6Lf_sdkZAAAAABI29FKzToJ0CasW0jgey-xxvcD6"></div>
				<p>
					${check_fail}
				</p>
				<p>
					<input type = "submit" class = "btn" value = "Thêm"/>
				</p>
			</form:form>
			
			
		</div>
	</div>

</body>
</html>
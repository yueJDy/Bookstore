<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<title>Thêm sách mới</title>
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
			<h1>THÊM SÁCH MỚI</h1>
			
			<form:form action = "admin/sach/insert-thong-tin.htm" modelAttribute = "book" method = "post" >
				<p>
					<label>Tên sách: </label>
					<form:input path="tenSach"/>
					${ten_fail}
				</p>
				<p>
					<label>Số lượng sản phẩm:</label>
					<form:input path="SLcon" type = "number" min="1" style = "width: 200px;height: 21px"/>
					${SLcon_fail}
				</p>
				<p>
					<label>Số trang:</label>
					<form:input path="soTrang" type = "number" min = "1" style = "width: 200px;height: 21px"/>
					${ST_fail}
				</p>
				<p>
					<label>Ngày xuất bản:</label>
					<input name="ngay" type = "date" />
					${date_fail}
				</p>
				<p>
					<label>Giới thiệu sách:</label>
					<form:textarea path="gioithieu" rows = "5" style = "width:400px"/>
					${gioithieu_fail}
				</p>
				<p>
					<label>Giá:</label>
					<form:input path="gia" type = "number" style = "width: 200px;height: 21px"/>
					${gia_fail}
				</p>
				<p>
					<label>Loại sách:</label>
					<form:select path="loaisach.maLoai"
							items = "${LS}" itemValue = "maLoai" itemLabel = "tenLoai"/>
				</p>
				<p>
					<label>Tác giả:</label>
					<form:select path="tacgia.maTG" 
							items= "${TG}" itemValue = "maTG" itemLabel = "tenTG"/>
				</p>
				<p>
					<label>Nhà xuất bản:</label>
					<form:select path="nhaXB.maNXB"
							items = "${NXB}" itemValue = "maNXB" itemLabel = "tenNXB"/>
				</p>
				<p>
					<input type = "submit" class = "btn" value = "Tiếp tục"/>
				</p>
			</form:form>
			
			
		</div>
	</div>


</body>
</html>
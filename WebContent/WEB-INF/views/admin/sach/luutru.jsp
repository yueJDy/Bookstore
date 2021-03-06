<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Quản lý sách</title>
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
<link rel="stylesheet" href="files/css/accounts/login.css">

</head>
<body>
	<%@ include file="../../page/header.jsp"%>

	<div class="container">
		<div class="pagelogin">
			<div style="width: 100%; height: 61px">
				<h1 style="float: left">DANH SÁCH</h1>
				<div style = "float:right; margin-top:18px">
					<a href = "admin/chuyenmuc/10/quan-ly-sach.htm" class = "btn" >Sách</a>
				</div>
				
			</div>

			<table>
				<tr>
					<th>Tiêu đề</th>
					<th>Số lượng SP</th>
					<th>Giá</th>
					<th>Loại sách</th>
					<th></th>
				</tr>
				<c:forEach var="b" items="${books}" varStatus="status">
					<tr>
						<td style="text-align: left; text-transform: capitalize;">
							<a
								href="sach.htm?sach=${b.maSach}">${b.tenSach}
							</a>
						</td>
						<td>${b.SLcon}
						<td>${b.gia}</td>
						<td>${b.loaisach.tenLoai}</td>
						<td>
							<a href="admin/sach/${b.maSach}.htm?restore" class = "btn">Khôi phục</a>
						</td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>
</body>
</html>
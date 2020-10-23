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
					<a href="admin/sach/insert.htm" class="btn" >+Thêm</a>
					<a href = "admin/sach/luu-tru.htm" class = "btn" >Lưu trữ</a>
				</div>
				
			</div>
			
			<c:if test="${error_book == true}">
				<div class="alert alert-danger alert-dismissible">
				    <button type="button" class="close" data-dismiss="alert">&times;</button>
				    <strong>Lỗi!</strong> Sách đã xoá hoặc không tồn tại.
				</div>
			</c:if>
			
			<c:if test="${message != null }">
				<div class="alert alert-danger alert-dismissible">
				    <button type="button" class="close" data-dismiss="alert">&times;</button>
				    <strong>Lỗi!</strong> ${message}
				</div>
			</c:if>

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
							<a href="admin/sach/${b.maSach}.htm?addSL" class = "btn">Thêm SP</a>
							<a href="admin/sach/${b.maSach}.htm?edit" class = "btn">Sửa</a>
							
							<!-- Button to Open the Modal -->
							<button type="button" class="btn" data-toggle="modal"
								data-target="#myModal${status.index + 1}">Xoá</button> <!-- The Modal -->
							<div class="modal fade" id="myModal${status.index + 1}">
								<div class="modal-dialog">
									<div class="modal-content">

										<!-- Modal Header -->
										<div class="modal-header">
											<h5 class="modal-title">Thông báo</h5>
											<button type="button" class="close" data-dismiss="modal">&times;</button>
										</div>

										<!-- Modal body -->
										<div class="modal-body">
											<strong>Xoá ${b.tenSach}?</strong>
											<form action = "admin/sach/${b.maSach}.htm?delete" method = "post">
												<p>
													<label style="display:inline-block;">Nhập lại mật khẩu: </label>
													<input name="password" type ="password"/>
													
												</p>
												<p>
													<input type = "submit" class = "btn" value = "Xoá"/>
												</p>
											</form>
										</div>

										<!-- Modal footer -->
										<div class="modal-footer">
											<button type="button" class="btn btn-danger"
												data-dismiss="modal">Close</button>
										</div>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>
</body>
</html>
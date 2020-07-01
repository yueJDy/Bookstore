<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Quản lý Tác giả</title>
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
			<div style = "width:100%; height:61px">
				<h1 style = "float:left">TÁC GIẢ </h1>
				<div style = "float:right; margin-top:18px">
					<a href = "admin/tacgia/insert.htm" class = "btn" >+Thêm</a>
					<a href = "admin/tacgia/luu-tru.htm" class = "btn" >Lưu trữ</a>
				</div>
			</div>
			
			<table>
                <tr>
                    <th>STT</th>
                    <th>Tên tác giả</th>
                    <th></th>
                </tr>
                <c:forEach var = "t" items = "${tacgia}" varStatus="status">
	                <tr>
	                	<td>${status.index + 1}</td>
	                	<td style = "text-align:left; text-transform: capitalize;">
	                		${t.tenTG}
	                	</td>
	                	<td>
	                		<a href="admin/tacgia/${t.maTG}.htm?edit" class = "btn">Sửa</a>
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
											<strong>Xoá tác giả?</strong>
											Nhấn vào <a href="admin/tacgia/${t.maTG}.htm?delete">đây</a> để xoá!
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
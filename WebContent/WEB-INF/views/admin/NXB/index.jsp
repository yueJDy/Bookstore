<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<title>Quản lý sách</title>
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
			<div style = "width:100%; height:100px">
				<h1>NHÀ XUẤT BẢN </h1>
				<c:if test="${error == true}">
					<div class="alert alert-danger alert-dismissible">
					    <button type="button" class="close" data-dismiss="alert">&times;</button>
					    <strong>Lỗi!</strong> Nội dung đã xoá hoặc không tồn tại.
					</div>
				</c:if>
				<c:if test="${message != null }">
					<div class="alert alert-danger alert-dismissible">
					    <button type="button" class="close" data-dismiss="alert">&times;</button>
					    <strong>Lỗi!</strong> ${message}
					</div>
				</c:if>
				
				<div style = "float:right; margin-top:18px">
					<a href = "admin/nxb/luu-tru.htm" class = "btn" >Lưu trữ</a>
				</div>
			</div>
			
			<p></p>
			<p></p>
			<div>
				<form:form action = "admin/nxb/insert.htm" modelAttribute = "nxb" method = "post">
					<p>
						<label>Tên nhà xuất bản: </label>
						<form:input path="tenNXB"/>
						${ten_fail}
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
			
			
			
			<table>
                <tr>
                    <th>STT</th>
                    <th>Tên Nhà xuất bản</th>
                    <th></th>
                </tr>
                <c:forEach var = "n" items = "${NXB}" varStatus="status">
	                <tr>
	                	<td>${status.index + 1}</td>
	                	<td style = "text-align:left; text-transform: capitalize;">
	                		${n.tenNXB}
	                	</td>
	                	<td>
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
											<strong>Xoá ${n.tenNXB}?</strong>
											<form action = "admin/nxb/${n.maNXB}.htm?delete" method = "post">
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
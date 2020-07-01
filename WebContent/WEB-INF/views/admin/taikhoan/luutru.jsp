<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Quản lý tài khoản</title>
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
	<style>
		.table_1{
			width: auto;
		}
	</style>
</head>
<body>
	<%@ include file = "../../page/header.jsp" %>
	
	<div class = "container">
		<div class = "pagelogin">
			<div style = "width:100%; height:61px">
				<h1 style = "float:left">DANH SÁCH TÀI KHOẢN </h1>
				<div style = "float:right; margin-top:18px">
					<a href = "admin/chuyenmuc/11/quan-ly-thanh-vien.htm" class = "btn" >Tài khoản</a>
				</div>
			</div>
			
			<table class = "table_1">
                <tr>
                    <th>STT</th>
                    <th>Email</th>
                    <th>Tên</th>
                    <th>Loại tài khoản</th>
                    <th></th>
                </tr>
                <c:forEach var = "u" items = "${users}" varStatus="status">
	                <tr>
	                	<td>${status.index + 1}</td>
	                	<td style = "text-align:left">
	                		${u.email}
	                	</td>
	                	<td>${u.name}</td>
	                	<td>
	                		<c:choose >
	                			<c:when test="${u.isAdmin}">
	                				Quản trị viên
	                			</c:when>
	                			<c:otherwise>Thành viên</c:otherwise>
	                		</c:choose>
	                	</td>
	                	<td>
	                		<a href="admin/user/${u.email}.htm?restore" class = "btn">Khôi phục</a>
						</td>
	                	
	                </tr>
                </c:forEach>
            </table>
		</div>
	</div>

</body>
</html>
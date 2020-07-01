<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Quản lý nhà xuất bản</title>
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
				<h1 style = "float:left">NHÀ XUẤT BẢN </h1>
				<div style = "float:right; margin-top:18px">
					<a href = "admin/chuyenmuc/12/quan-ly-NXB.htm" class = "btn">Nhà xuất bản</a>
				</div>
			</div>
			
			<table>
                <tr>
                    <th>STT</th>
                    <th>Tên nhà xuất bản</th>
                    <th></th>
                </tr>
                <c:forEach var = "n" items = "${nxb}" varStatus="status">
	                <tr>
	                	<td>${status.index + 1}</td>
	                	<td style = "text-align:left; text-transform: capitalize;">
	                		${n.tenNXB}
	                	</td>
	                	<td>
	                		<a href="admin/nxb/${n.maNXB}.htm?restore" class = "btn">Khôi phục</a>
	                		
						</td>
	                	
	                </tr>
                </c:forEach>
            </table>
		</div>
	</div>

</body>
</html>
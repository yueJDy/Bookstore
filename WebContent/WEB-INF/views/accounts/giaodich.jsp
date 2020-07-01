<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Giao dịch </title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel = "stylesheet" href="files/css/accounts/login.css">
    <base href = "${pageContext.servletContext.contextPath}/">
</head>
<body>
	<%@ include file = "../page/header.jsp" %>
	
	<link rel = "stylesheet" href="files/css/accounts/login.css">
	<div class = "container">
		<div class = "pagelogin">
			<h1>LỊCH SỬ GIAO DỊCH</h1>
			
			<table>
                <tr>
                    <th>STT</th>
                    <th>Mã đơn hàng</th>
                    <th>Người mua</th>
                    <th>Ngày mua</th>
                    <th>Trạng thái</th>
                </tr>
                <c:forEach var = "dh" items = "${DH}" varStatus="status">
	                <tr>
	                	<td>${status.index + 1}</td>
	                	<td>
	                		<a href = "gio-hang/donhang/${dh.maDonhang}.htm">${dh.maDonhang}</a> 
	                	</td>
	                	<td>${dh.hoten}</td>
	                	<td>${dh.ngay}</td>
	                	<td>
		                	<c:choose>
		                		<c:when test="${dh.trangthai == 1}">Đã nhận đơn</c:when>
		                		<c:when test="${dh.trangthai == 2}">Đang vận chuyển</c:when>
		                		<c:when test="${dh.trangthai == 3}">Giao hàng thành công</c:when>
		                	</c:choose>
	                	</td>
	                </tr>
                </c:forEach>
            </table>
		</div>
	</div>

</body>
</html>
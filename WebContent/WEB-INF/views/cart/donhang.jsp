<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Thêm vào giỏ hàng</title>
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
	<%@ include file = "../page/header.jsp" %>
	
	<div class = "container">
		<div class = "pagelogin">
			<h1>ĐƠN HÀNG</h1>
			<h6><strong>1. Thông tin khách hàng:</strong></h6>
			<p>
				<label>Họ và tên: ${donhang.hoten}</label>
			</p>
			<p>
				<label>Số điện thoại: ${donhang.sdt}</label>
			</p>
			<p>
				<label>Địa chỉ nhận hàng: ${donhang.diachi}</label>
			</p>
			<c:if test="${donhang.ghichu != null}">
				<p>
					<label>Ghi chú: ${donhang.ghichu}</label>
				</p>
			</c:if>
			<p>
				Trạng thái đơn hàng:
				<c:choose>
					<c:when test="${donhang.trangthai == 1}">Đã nhận đơn</c:when>
               		<c:when test="${donhang.trangthai == 2}">Đang vận chuyển</c:when>
               		<c:when test="${donhang.trangthai == 3}">Giao hàng thành công</c:when>
				</c:choose>
			</p>
			<h6><strong>2. Thông tin sản phẩm:</strong></h6>
    		<table>
                <tr>
                    <th>STT</th>
                    <th>Tiêu đề</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th>Tổng cộng</th>
                </tr>
                <c:forEach var = "c" items = "${CTDH}" varStatus="status">
	                <tr>
	                	<td>${status.index + 1}</td>
	                	<td style = "text-align:left; text-transform: capitalize;">
	                		<a href = "sach.htm?sach=${c.book.maSach}">${c.book.tenSach}</a> 
	                	</td>
	                	<td>${c.book.gia}đ</td>
	                	<td>
	                		${c.soluong}
						</td>
	                	<td>${c.soluong * c.book.gia}</td>
	                	
	                </tr>
                </c:forEach>
                <tfoot>
                	<tr>
                		
                		<th colspan = "4" style = "border-top: 1px solid #adabab">Tổng cộng:</th>
                		<td>${sum}đ</td>
                	</tr>
                </tfoot>
            </table>
		    	
			
		</div>
	</div>
	
</body>
</html>
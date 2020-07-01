<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<title>Đơn hàng</title>
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
			<h1>THÔNG TIN ĐƠN HÀNG</h1>
			
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
			<h6><strong>2. Thông tin sản phẩm:</strong></h6>
			<table >
                <tr>
                    <th>STT</th>
                    <th>Tiêu đề</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th>Tổng cộng</th>
                    
                </tr>
                <c:forEach var = "p" items = "${sessionScope.user.getCart()}" varStatus="status">
	                <tr>
	                	<td>${status.index + 1}</td>
	                	<td style = "text-align:left; text-transform: capitalize;">
	                		${p.book.tenSach}
	                	</td>
	                	<td>${p.book.gia}đ</td>
	                	<td>
	                		${p.soluong}
						</td>
	                	<td>${p.soluong * p.book.gia}</td>
	                	
	                </tr>
                </c:forEach>
                <tfoot>
                	<tr>
                		
                		<th colspan = "4" style = "border-top: 1px solid #adabab">Tổng cộng:</th>
                		<td>${sum}đ</td>
                	</tr>
                </tfoot>
            </table>
            
            <h6><strong>3.Thời gian giao hàng dự kiến</strong></h6>
            <p> 48 giờ kể từ lúc đơn hàng được tiếp nhận.</p>
            
            <h6><strong>4. Hình thức thanh toán</strong></h6>
            <p>Thanh toán tiền mặt khi nhận hàng</p>
			
			<p>
				<a href = "gio-hang/dat-hang.htm" class = "btn" >Tiếp tục</a>
			</p>
			
		</div>
	</div>

</body>
</html>
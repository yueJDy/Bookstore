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
			<h1>GIỎ HÀNG</h1>
			<c:choose>
				<c:when test="${sessionScope.user != null && sizecart == 0}">
					<div style = "margin:auto; width :300px ">
			    		<p>Không có sản phẩm nào trong giỏ hàng của bạn.</p>
		    		</div>
		    		<div style = "margin:auto; width :135px ">
			    		<a href = "index.htm" class = "btn">Tiếp tục mua sắm</a>
		    		</div>
		    	</c:when>
		    	<c:when test="${sizecart > 0}">
		    		<c:if test = "${message != null}">
		    		<p>
		    			<label>${message}</label>
		    		</p>
		    		</c:if>
		    		<table>
		                <tr>
		                    <th>STT</th>
		                    <th>Tiêu đề</th>
		                    <th>Giá</th>
		                    <th>Số lượng</th>
		                    <th>Tổng cộng</th>
		                    <th></th>
		                </tr>
		                <c:forEach var = "p" items = "${sessionScope.user.getCart()}" varStatus="status">
			                <tr>
			                	<td>${status.index + 1}</td>
			                	<td style = "text-align:left; text-transform: capitalize;">
			                		<a href = "sach.htm?sach=${p.book.maSach}">${p.book.tenSach}</a> 
			                	</td>
			                	<td>${p.book.gia}đ</td>
			                	<td>
			                		${p.soluong}
								</td>
			                	<td>${p.soluong * p.book.gia}</td>
			                	<td>
				                	<a href="gio-hang/${status.index}.htm?edit" >Sửa</a>  |  
				                	<a href="gio-hang/${status.index}.htm?delete">Xoá</a>
				                	
			                	</td>
			                </tr>
		                </c:forEach>
		                <tfoot>
		                	<tr>
		                		
		                		<th colspan = "4" style = "border-top: 1px solid #adabab">Tổng cộng:</th>
		                		<td>${sum}đ</td>
		                		<td></td>
		                	</tr>
		                </tfoot>
		            </table>
		            <div style ="float:right; width:200px">
		            	<a href = "gio-hang/thanh-toan.htm" class = "btn">Đặt hàng</a>
		            </div>
		    	</c:when>
		    	<c:otherwise>
		    		
		    		<div style = "margin:auto; width :135px ">
			    		<a href = "index.htm" class = "btn">Tiếp tục mua sắm</a>
		    		</div>	
		    	</c:otherwise>
			</c:choose>
			
		</div>
	</div>

</body>
</html>
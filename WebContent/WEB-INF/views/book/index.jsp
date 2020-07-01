<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>${title}</title>
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
<link rel="stylesheet" href="files/css/book/book.css">
<style>
#cart_buy {
	width: 137px;
	cursor: pointer;
	background-color: #033801;
}

.btn {
	margin-top: 2px;
	height: 30px;
}

.btn:hover {
	color: white;
}

.div_1 {
	display: inline-block;
}

.div_2 {
	float: right;
	padding-left: 15px;
}

.titlebook {
	display: inline-block;
}

.addofadmin {
	float: right;
}
</style>

</style>
</head>
<body>
	<%@ include file="../page/header.jsp"%>

	<div class="container">
		<div class="bookdetailwrap">
			<div class="bookdetail clearfix">
				<a class="image image0" href="sach.htm?sach=${book.maSach}"> <img
					src="files/images/book/${book.photo}" alt="${book.tenSach}" /> <span
					class="overlay"></span>
				</a>
				<div class="info">
					<div>
						<div class="titlebook">
							<h1>
								<a href="sach.htm?sach=${book.maSach}"> ${book.tenSach} </a>
							</h1>
						</div>
					</div>


					<div class="intro clearfix">
						<div class="attributes">
							<ul>
								<li class="dataattr"><span>Tác giả: </span> <a
									href="tac-gia/${book.tacgia.maTG}.htm">${book.tacgia.tenTG}</a></li>
								<li class="dataattr"><span>Nhà xuất bản: </span> <a
									href="nha-xuat-ban/${book.nhaXB.maNXB}.htm">${book.nhaXB.tenNXB}</a></li>
							</ul>

							<ul>
								<li>Số trang: ${book.soTrang}</li>
								<li>Kích thước: ${book.width}cm x ${book.height}cm</li>
								<li>Ngày phát hành: <fmt:formatDate value="${book.ngayXB}"
										pattern="dd-MM-yyyy" />
								</li>
							</ul>


						</div>

						<div class="action">

							<div class="price">
								<p>
									Giá: <span>${book.gia}đ</span> (Đã có VAT)
								</p>
							</div>

							<div class="quantitytext">Số lượng:</div>
							<c:choose>
								<c:when test="${book.SLcon == 0}">
									<div class="div_1">
										<form action="gio-hang/them-vao-gio-hang/${book.maSach}.htm"
											method=post>
											<input type="number" value="1" class="quantity" min="1" 
													max="${book.SLcon}" name="soluong" style="color: red;">
											<div class="div_2">
												<div>
													<input type="submit" value="Thêm vào giỏ hàng" class="btn"
														id="cart_buy" disabled />
												</div>

											</div>
										</form>

									</div>
									<div style="width: 200px">
										<p>Sản phẩm đã hết hàng</p>
									</div>
								</c:when>
								<c:otherwise>
									<div class="div_1">
										<form action="sach/them-vao-gio-hang/${book.maSach}.htm"
											method=post>
											<input type="number" value="1" class="quantity" min="0"
												max="${book.SLcon}" name="soluong" style="color: red;">
											<div class="div_2">
												<div>
													<input type="submit" value="Thêm vào giỏ hàng" class="btn"
														id="cart_buy" />
												</div>

											</div>
										</form>

									</div>
									<div style="width: 200px">Còn ${book.SLcon} sản phẩm
										${soluong_fail}</div>
								</c:otherwise>
							</c:choose>

						</div>
					</div>
				</div>

			</div>

			<div class="bookdetailblockcontent">
				<h1>Giới thiệu sách</h1>
				<article>
					<p>${book.gioithieu}</p>

				</article>
			</div>

			<div class="bookdetailblock bookdetailblockrelated"
				id="bookrelatecategory">
				<h1>Có thể bạn quan tâm</h1>
				<article>



					<div class="bookslider">
						<ul class="clearfix listbook3">
							<c:forEach var = "b" items = "${books}">
								<li class="book3 bookimage0">
									<a title="${b.tenSach}" class="image"
										href="sach.htm?sach=${b.maSach}">
										<img src="files/images/book/${b.photo}" alt="${b.tenSach}" />
									</a>
								</li>
							</c:forEach>
						</ul>

					</div>

					
			</div>
		</div>
</body>
</html>
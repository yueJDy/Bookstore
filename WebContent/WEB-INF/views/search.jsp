<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Nhã Nam </title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <base href = "${pageContext.servletContext.contextPath}/">
    <style>
    	#title{
    		height: 70px;
    		
    	}
    	#text{
    		font-size: 13px;
    	}
   		a:not([href]):hover {
    		color: white;
    	}
    </style>
</head>
<body>
    <%@ include file = "page/header.jsp" %>
	
	<link rel = "stylesheet" href="files/css/home/home.css">
	<div class="container">
		<div class = "wrapper">
			
	
			<h1 class="pagetitle">
				<a > Kết quả tìm kiếm: ${search} </a>
			</h1>
	
			<div class="listbook">
				<div class="wrapper">
					<c:forEach var="b" items="${book1}">
						<div class="book">
							<a href="sach.htm?sach=${b.maSach}" title="${b.tenSach}">
								<div class="card mycart" style="width: 210px; height: 360px">
									<img src="files/images/book/${b.photo}" alt="${b.tenSach}" />
									<div class="card-body">
										<div id="title">
											<h4 class="card-title">${b.tenSach}</h4>
										</div>
										<p class="card-text" id = "text">${b.gia}đ</p>
									</div>
								</div>
							</a>
	
						</div>
					</c:forEach>
					<c:forEach var="b2" items="${book2}">
						<div class="book">
							<a href="sach.htm?sach=${b2.maSach}" title="${b2.tenSach}">
								<div class="card mycart" style="width: 210px; height: 360px">
									<img src="files/images/book/${b2.photo}" alt="${b2.tenSach}" />
									<div class="card-body">
										<div id="title">
											<h4 class="card-title">${b2.tenSach}</h4>
										</div>
										<p class="card-text" id = "text">${b2.gia}đ</p>
									</div>
								</div>
							</a>
	
						</div>
					</c:forEach>
				</div>
			</div>
			
			<c:if test = "${tacgia != null}">
				<h1 class="pagetitle">
					<a href="tac-gia/${tacgia.maTG}.htm"> Tác giả: ${tacgia.tenTG} </a>
				</h1>
		
				<div class="listbook">
					<div class="wrapper">
						<c:forEach var="b3" items="${book3}">
							<div class="book">
								<a href="sach.htm?sach=${b3.maSach}" title="${b3.tenSach}">
									<div class="card mycart" style="width: 210px; height: 360px">
										<img src="files/images/book/${b3.photo}" alt="${b3.tenSach}" />
										<div class="card-body">
											<div id="title">
												<h4 class="card-title">${b3.tenSach}</h4>
											</div>
											<p class="card-text" id = "text">${b3.gia}đ</p>
										</div>
									</div>
								</a>
		
							</div>
						</c:forEach>
					</div>
				</div>
			</c:if>
			
		</div>
	</div>

</body>
</html>
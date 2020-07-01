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
    </style>
</head>
<body>
    <%@ include file = "../page/header.jsp" %>
	
	<link rel = "stylesheet" href="files/css/home/home.css">
	<div class="container">
		<div class = "wrapper">
			<div class="slide">
				<div id="my-slide" class="carousel slide" data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#my-slide" data-slide-to="0" class="active"></li>
						<li data-target="#my-slide" data-slide-to="1"></li>
						<li data-target="#my-slide" data-slide-to="2"></li>
						<li data-target="#my-slide" data-slide-to="3"></li>
					</ol>
					<div class="carousel-inner">
						<div class="carousel-item active" data-interval="5000">
							<a href="#"> <img src="files/images/I6QXE6G2.jpg" width="100%"
								height="300" alt="Chương trình tháng 4">
							</a>
	
						</div>
						<div class="carousel-item" data-interval="5000">
							<a href="#"> <img src="files/images/6HHNAJ7E.jpg" width="100%"
								height="300" alt="Bộ cuốn sách đầu đời">
							</a>
	
						</div>
						<div class="carousel-item" data-interval="5000">
							<a href="#"> <img src="files/images/OJ4M48Q3.jpg" width="100%"
								height="300" alt="Bộ Ehon nồng ấm của Yoko Imoto (4 quyển)">
							</a>
						</div>
						<div class="carousel-item" data-interval="5000">
							<a href="#"> <img src="files/images/3XSUDF44.jpg" width="100%"
								height="300" alt="Bộ Ehon nồng ấm của Yoko Imoto (4 quyển)">
							</a>
	
						</div>
					</div>
					<a class="carousel-control-prev" href="#my-slide" role="button"
						data-slide="prev"> <span class="carousel-control-prev-icon"
						aria-hidden="true"></span> <span class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#my-slide" role="button"
						data-slide="next"> <span class="carousel-control-next-icon"
						aria-hidden="true"></span> <span class="sr-only">Next</span>
					</a>
				</div>
			</div>
	
	
			<h1 class="pagetitle">
				<a href="chuyenmuc/1/van-hoc-viet-nam.htm"> Văn học Việt Nam </a>
			</h1>
	
			<div class="listbook">
				<div class="wrapper">
					<c:forEach var="b" items="${VHVN}">
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
				</div>
			</div>
		</div>
	</div>

</body>
</html>
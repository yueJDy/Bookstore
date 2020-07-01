<%@ page pageEncoding="utf-8"%>
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
    	.gt{
    		background-color:white;
    		padding:10px; 
    	}
    </style>
</head>
<body>
    <%@ include file = "../page/header.jsp" %>
	
	<link rel = "stylesheet" href="files/css/home/home.css">
	<div class="container">
		<div class = "wrapper">
			
			<h1 class="pagetitle">
				<a href="tac-gia/${tacgia.maTG}.htm">${tacgia.tenTG}</a>
			</h1>
			
			<c:if test="${gioithieu}">
				<div class = "gt">
					${tacgia.gioiThieu}
				</div>
			</c:if>
	
			<div class="listbook">
				<div class="wrapper">
					<c:forEach var="b" items="${books}">
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
<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Giới thiệu</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <base href = "${pageContext.servletContext.contextPath}/">
    <link rel = "stylesheet" href = "files/css/page/page.css">
    <style>
    	.page{
    		background-image: url("files/images/header/headerbg.png");
    		margin: 10px auto;
    		padding: 30px;
    		font-size: 13px;
    		weidth: 940px;
    	}
    </style>
</head>
<body>
	<%@ include file = "../page/header.jsp" %>
	<div class = "container">
		<div class = "wrapper">
			<div >
				<a href="admin/chuyenmuc/10/quan-ly-sach.htm" class = "btn">Sách</a>
				<a href="admin/chuyenmuc/11/quan-ly-thanh-vien.htm" class = "btn">
                    Thành viên
                </a>
                <a href="admin/chuyenmuc/12/quan-ly-NXB.htm" class = "btn">
	                Nhà xuất bản
	            </a>
	            <a href="admin/chuyenmuc/13/quan-ly-tac-gia.htm" class = "btn">
	                Tác giả
	            </a>
			</div>
			
			
		</div>
	</div>
	
</body>
</html>
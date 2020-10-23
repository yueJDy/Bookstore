<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Bookstore </title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel = "stylesheet" href="files/css/accounts/login.css">
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
	<%@ include file = "page/header.jsp" %>
	<div class = "container">
		<div class = "pagelogin">
			<div style = "margin:auto; width :330px ">
	    		<h5>Nội dung đã xoá hoặc không tồn tại.</h5>
    		</div>
			<div style = "margin:auto; width :135px ">
	    		<a href = "index.htm" class = "btn">Trở lại trang chủ</a>
    		</div>
		</div>
	</div>

</body>
</html>
<%@ page pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jstl/core_rt" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet" href = "files/css/header/header.css">
</head>
<body>
	<div class="header">
    	<div class="topbar hidden-mobile">
   			<div class="wrapper">
       			<div class="clearfix">
       				<ul class = "topprofile">
       					<c:choose>
               				<c:when test="${sessionScope.user != NULL}">
               					<li>
		                  			<a href="gio-hang/lich-su-giao-dich.htm">${sessionScope.user.username}</a>
		                      		
		                 		</li>
		                  		<li>
		                      		<a href="dang-xuat.htm">
		                      			Đăng xuất
		                      		</a>
		                  		</li>
               				</c:when>
               				<c:otherwise>
               					<li>
		                  			<a href="dang-ky.htm">Đăng ký</a>
		                      		
		                 		</li>
		                  		<li>
		                      		<a href="dang-nhap.htm">
		                      			Đăng nhập
		                      		</a>
		                  		</li>
               				</c:otherwise>
               			</c:choose>
                  		
		            
		        	</ul>
		        </div>
		    </div>
		</div>

		<div class="wrapper wrap">
		    <h1 class="logo">
                   <a href="index.htm">
                   Nha Nam
                   </a>
		    </h1>
		    
	    	<div class = "cart">
	    		<a href = "gio-hang.htm">Giỏ hàng</a>
	    	</div>
	    
	    	<div class="search">
	        	<form action="tim-kiem.htm" method="post">
		            <input type="text" name="q" placeholder="Tìm kiếm sách..." class="text"/>
		            <input type="submit" value="Tìm kiếm" class="submit" />
		        </form>
		        <p>
					${search_fail}
				</p>
	    	</div>
		</div>
	</div>
	
	<div id="mainnav">
	    <div class="wrapper">
	        <ul class="menu clearfix">
	        	<li>
	                <a >
	                    Danh mục sách
	                </a>
                	<ul class="submenu">
                        <li>
                            <a href="chuyenmuc/1/van-hoc-viet-nam.htm">
                                Văn học Việt Nam
                            </a>
                        </li>
                        <li>
                            <a href="chuyenmuc/2/van-hoc-nuoc-ngoai.htm">
                                Văn học Nước Ngoài
                            </a>
                        </li>
                        <li>
                            <a href="chuyenmuc/3/thieu-nhi.htm">
                                Thiếu nhi
                            </a>
                        </li>
                        <li>
                            <a href="chuyenmuc/4/khoa-hoc-tu-nhien-nhan-van.htm">
                                Khoa học tự nhiên - Nhân văn
                            </a>
                        </li>

	                </ul>
		
		        </li>
		
	            <c:choose>
	            	<c:when test="${sessionScope.user.isAdmin}">
		            	<li>
			                <a>
                                Quản lý
                            </a>
	                        <ul class="submenu">
		                        <li>
		                            <a href="admin/chuyenmuc/11/quan-ly-thanh-vien.htm">
		                                Tài khoản
		                            </a>
		                        </li>
		                        <li>
		                            <a href="admin/chuyenmuc/10/quan-ly-sach.htm">
		                                Sách
		                            </a>
		                        </li>
		                        <li>
		                            <a href="admin/chuyenmuc/12/quan-ly-NXB.htm">
		                                Nhà xuất bản
		                            </a>
		                        </li>
		                        <li>
		                            <a href="admin/chuyenmuc/13/quan-ly-tac-gia.htm">
		                                Tác giả
		                            </a>
		                        </li>
			                </ul>
			            </li>
			            
			    	</c:when>
			    	<c:otherwise>
			    		<li>
			                <a href="chuyenmuc/7/sach-moi.htm">Sách mới</a>
			            </li>
			    	</c:otherwise>
	            </c:choose>		
	
	            
	        </ul>
	    </div>
	</div>
</body>
</html>
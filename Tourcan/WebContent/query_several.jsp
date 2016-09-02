<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h2>Search Photo</h2>
		<form name="queryPhoto">
			<div>
				<label for="photo_id">照片編號:</label><input name="photo_id"
					id="photo_id"> <span id="photoId"></span>
			</div>
			<div>
				<label for="att_id">景點編號:</label><input name="att_id"
					id="att_id"> <span id="attId"></span>
			</div>
			<div>
				<label for="hotel_id">飯店編號:</label><input name="hotel_id"
					id="hotel_id"> <span id="hotelId"></span>
			</div>
			<div>
				<input type="button" id="getImgs" value="Search By ID">
			</div>
			<div>
				<img id="photo_file" alt="" src="">
			</div>
			<div>
				<span id="result"></span>
			</div>
		</form>
	</center>
	<script type="text/javascript">
		$(function(){
			
			
			$("#getImgs").click(function()
			{
				$.getJSON("PhotoServlet",{"photo_id":$("#photo_id").val(),
										  "att_id":$("#att_id").val(),
										  "hotel_id":$("#hotel_id").val(),
										  "method":"getServal"})
					.done(function(data)
					{
						
					});
			});
			
			
		});
	</script>
</body>
</html>
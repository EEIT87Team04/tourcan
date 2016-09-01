<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Photo Query</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
</head>
<body>
	<center>
		<h2>Search Photo</h2>
		<form name="queryPhoto">
			<div>
				<label for="getOne">Photo ID:</label><input name="photo_id"
					id="photo_id"> <span id="photoId"></span>
			</div>
			<div>
				<input type="button" id="getOne" value="Search By PhotoID">
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
			
			
			$("#getOne").click(function()
			{
				$("#photo_file").removeAttr("src");
				$("#photoId").empty();
				$("#result").empty();
				$.getJSON("PhotoServlet",{"photo_id":$("#photo_id").val(),
										  "method":"getOne"})
					.done(function(data)
					{
						$.each(data,function(resName,resVal){
							if(resName=="photo_file"){
							$("#"+resName).attr("src","data:image/jpg;base64," + resVal);}
							
							$("#"+resName).text(resVal);
						});
					});
			});
			
			
		});
	</script>
</body>
</html>
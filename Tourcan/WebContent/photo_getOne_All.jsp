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
				<label for="photo_id">Photo ID:</label><input name="photo_id"
					id="photo_id"> <span id="photoId"></span>
			</div>
			<div>
				<input type="button" id="getImg" value="Search By PhotoID">
			</div>
			<div>
				<img id="photo_file" alt="" src="">
			</div>
			<div>
				<span id="result"></span>
			</div>
		</form>
		<hr>
		<h2>List Every Images</h2>
		<form name="getAll">
			<div>
				<input type="button" id="getAll" value="Search For All">
			</div>
			<div>
				<span id="result"></span>
			</div>
			<div>
				<table id="photoList" border="1">
					<tr>
						<th>Photo ID</th>
						<th>Photo</th>
					</tr>
				</table>
			</div>
		</form>
	</center>
	
	
	
	
	<script type="text/javascript">
		$(function(){
			
			$("#getImg").click(function(){
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
					})
					.fail(function(){
						console.log("Failed");
					});
			});

			
			$("#getAll").one('click',function(){
				$.getJSON("PhotoServlet",{"method":"getAll"})
				.done(function(data){
					console.log(data);
					$.each(data,function(id,src){
						if(src!="查無資料"){
						var tdID = $("<td></td>").append(id);
						var img = $("<img></img>").attr("src","data:image/jpg;base64,"+src).attr("height","200px").attr("width","200px");
						var tdImg = $("<td></td>").append(img);
						var row = $("<tr></tr>");
						row.append(tdID)
							.append(tdImg);
						$("#photoList").append(row);
						}else{
							$("#"+id).text(src);
						}
					});
					
				})
				.fail(function(){
					console.log("Failed");
				});
			});
			
			
		});
	</script>
</body>
</html>
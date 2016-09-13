<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Photo Delete</title>
</head>
<body>

	<center>
		<h2>Delete By ID</h2>
		<form name="deleteById">
			<div>
				<label for="photo_id">Photo ID:</label><input name="photo_id"
					id="photo_id"><span id="photoId"></span>
			</div>
			<div>
				<input type="button" id="deleteById" value="deleteById">
			</div>
		</form>
		<br> <br>
		<hr>
		<br> <br>
		<h2>Delete By Attraction ID</h2>
		<form name="deleteByAtt">
			<div>
				<label for="att_id">Attraction ID:</label><input name="att_id"
					id="att_id"><span id="attId"></span>
			</div>
			<div>
				<input type="button" id="deleteByAtt" value="deleteByAtt">
			</div>
		</form>
	</center>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#deleteById").click(function(){
				$("#photoId").empty();
				$.ajax(
					  {"url":"PhotoServlet?" + $.param({"photo_id":$("#photo_id").val(),
													   "method" : "deleteById"}) ,
					  "type":"delete"})
					  .done(function(data){
						  $("#photo_id").val("");
						  $.each(data,function(result,msg){
							  $("#photoId").text(msg);
						  });
					  })
					  .fail(function(err){
						  console.log("Failed");
					  });
			});
				
			$("#deleteByAtt").click(function(){
				$("#attId").empty();
				$.ajax(
					  {"url":"PhotoServlet?" + $.param({"att_id":$("#att_id").val(),
													   "method" : "deleteByAtt"}) ,
					  "type":"delete"})
					  .done(function(data){
						  $("#att_id").val("");
						  $.each(data,function(result,msg){
							  $("#attId").text(msg);
						  });
					  })
					  .fail(function(err){
						  console.log("Failed");
					  });
				
				});
			
				

		});
	</script>

</body>
</html>
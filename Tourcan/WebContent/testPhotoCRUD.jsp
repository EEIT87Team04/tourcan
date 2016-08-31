<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Photo CRUD</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
</head>
<body>
	<center>
<!-- 		<form action="PhotoServlet" method="post" name="insertPhoto" enctype="multipart/form-data"> -->
		<form name="insertPhoto">
			<h2>Photo CRUD</h2>
			<div>
				<label for="photo"></label><input type="file" name="photo"
					id="photo">
			</div>
			<div>
				<span style="color: RED" id="result"></span>
			</div>
			<div>
<!-- 				<input type="submit" id="sendPhoto" value="send"> -->
				<input type="button" id="sendPhoto" value="send">
			</div>
		</form>
	</center>

	<script type="text/javascript">
		$(function() {
			var form = $(document.insertPhoto).serializeArray(), json = {};
			for(var i=0;i<form.length;i++)
			json[form[i].name] = form[i].value;
			$("#sendPhoto").click(function() {
				$.ajax({
					"type":"post",
					"url":"PhotoServlet",
					"data":JSON.stringify(json),
				    "cache":false,
				    "processData":false,
				    "contentType":false
// 					"contentType":"multipart/form-data"
				}).done(function(data) {
					$.each(data, function(pos, msg) {
						$("#" + pos).text(msg);
					});
				});

			});
		});
	</script>
</body>
</html>
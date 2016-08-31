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
		<form name="insertPhoto" enctype="multipart/form-data">
			<h2>Photo CRUD</h2>
			<div>
			<label for="photo"></label><input type="file" name="photo" id="photo">
			</div>
			<div>
			<input type="button" id="sendPhoto" value="send">
			</div>
		</form>
	</center>

	<script type="text/javascript">
		$(function() {
			$("#sendPhoto").click(function() {
				$.post("")
			});

		});
	</script>
</body>
</html>
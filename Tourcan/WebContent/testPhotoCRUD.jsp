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
		<form action="PhotoServlet" method="post" name="insertPhoto" enctype="multipart/form-data">
			<h2>Photo CRUD</h2>
			<div>
				<label for="att_id">Attraction ID:</label><input type="text" name="att_id" id="att_id">
			</div>
			<div>
				<label for="hotel_id">Hotel ID:</label><input type="text" name="hotel_id" id="hotel_id">
			</div>
			<div>
				<label for="photo">Photo:</label><input type="file" name="photo"
					id="photo">
			</div>
			<div>
				<span style="color: RED" id="result"></span>
			</div>
			<div>
				<input type="submit" id="sendPhoto" value="send">
			</div>
		</form>
	</center>

	<script type="text/javascript">
		$(function() {
			
		});
	</script>
</body>
</html>
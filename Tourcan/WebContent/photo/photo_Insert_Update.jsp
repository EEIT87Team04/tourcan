<%@page import="javax.persistence.metamodel.SetAttribute"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@page import="javax.servlet.http.HttpSession"%> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<form action="${contextPath}/att/PhotoServlet" method="post" name="insertPhoto"
			enctype="multipart/form-data">

			<h2>Photo Insert</h2>
			<div>
				<label for="att_id">Attraction ID:</label><input type="text"
					name="att_id" id="att_id" value="${param.att_id}">
				<div style="color: RED">${attid}</div>
			</div>
			<!--<div> -->
			<!--<label for="hotel_id">Hotel ID:</label><input type="text" name="hotel_id" id="hotel_id"> -->
			<!--</div> -->
			<div>
				<label for="photo">Photo:</label><input type="file" name="imgs"
					id="photo">
				<div style="color: RED">${photo}</div>
			</div>
			<div>
				<span style="color: RED" id="result"></span>
			</div>
			<div>
				<input type="hidden" name="uri" id="uri"
					value="${pageContext.request.requestURI}"> <input
					type="hidden" name="action" id="action" value="post">
			</div>
			<div>
				<input type="submit" id="sendPhoto" value="send">
				<div style="color: RED">${result}</div>
			</div>
		</form>
		<br>
		<hr>
		<br>

	</center>

	<script type="text/javascript">
		$(function() {
		});
	</script>
</body>
</html>
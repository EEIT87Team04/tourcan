<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>${hotelVO.hotel_name}</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="container" style="padding: 10px">
		<div class="row">
			<div class="col-md-6 col-md-offset-4">
				<h1>${hotelVO.hotel_name}</h1>
			</div>
		</div>
		<div style="border: solid 1px" class="col-md-10 col-md-offset-1">
			<div class="row">
				<div style="border: solid 1px" class="col-md-4 col-md-offset-1">
					照片:照片放這</div>
				<div style="border: solid 1px" class="col-md-7">
					<div style="border: solid 1px" class="row">
						<div class="col-md-7">${hotelVO.hotel_name}</div>
						<div class="col-md-5">${hotelVO.regionVO.region_name}</div>
						<div class="col-md-12">${hotelVO.hotel_addr}</div>
						<div class="col-md-7">${hotelVO.hotel_price}</div>
						<div class="col-md-5">${hotelVO.hotel_phone}</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>住宿資料維護</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>No.</th>
				<th>名稱</th>
				<th>地址</th>
				<th>電話</th>
				<th>星等</th>
				<th>價位</th>
				<th>網址</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
		<tfoot>
		</tfoot>
	</table>


	<script src="js/jquery-3.1.0.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	<script type="text/javascript">
		$(function() {

			$.when($.ajax({
				url : "HotelServlet",
				method : "GET",
				contentType : "application/json; charset=UTF-8",
				dataType : "json"
			}), $.ajax({
				url : "RegionServlet",
				method : "GET",
				contentType : "application/json; charset=UTF-8",
				dataType : "json"
			})).done(function(d1, d2) {
				console.log(d1);
				console.log(d2);
				$.each(d1[0], function() {
					var $tr = $("<tr>")
					var $td1 = $("<td>", {
						text : this.hotel_id
					});
					var $td2 = $("<td>").text(this.hotel_name);
					var $td3 = $("<td>").text(this.hotel_addr);
					var $td4 = $("<td>").text(this.hotel_phone);
					var $td5 = $("<td>").text(this.hotel_class);
					var $td6 = $("<td>").text(this.hotel_price);
					var $td7 = $("<td>").text(this.hotel_url);
					$tr.append([ $td1, $td2, $td3, $td4, $td5, $td6, $td7 ]);
					$("table tbody").append($tr);
				});
			}).fail(function() {

			});
		});
	</script>
</body>
</html>
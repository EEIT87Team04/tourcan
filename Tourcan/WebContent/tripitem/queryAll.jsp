<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<style type="text/css">

table, th, td {
   border: 1px solid black;
}
h3{
color:red;
}
h2{
text-decoration: underline;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>查詢全部行程明細</title>
</head>
<body>
<h2 >Search All</h2>
	<div >
		<label for="tripitemAll">LIST ALL</label> 
		<button type="button" id="search">search all</button>
	</div>
	<div id="aa">
		<table id="tb1">
		<thead>
			<tr>
				<th>tripitem_id</th>				
				<th>trip_id</th>				
				<th>tripitem_serial</th>				
				<th>att_id</th>				
				<th>hotel_id</th>				
				<th>tripitem_staytime</th>				
				<th>tripitem_begin</th>				
				<th>tripitem_end</th>
				<th>tripitem_traffic</th>
				<th>tripitem_memo</th>
			</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>

<!--  js/jquery-3.1.0.min.js 注意路徑-->
	<script src="../js/jquery-3.1.0.min.js"></script>  
	<script type="text/javascript">

$(function(){
	$("#search").click(function() {
				$('#tb1>tbody').empty();
		$.getJSON(("TripitemServlet"),{"method":"getAll"},function(data){
			var myBody = $('#tb1>tbody');
			$.each(data,function(idx,tripitem){
				var cell1 = $("<td></td>").text(tripitem.tripitem_id);
				var cell2 = $("<td></td>").text(tripitem.trip_id);
				var cell3 = $("<td></td>").text(tripitem.tripitem_serial);
				var cell4 = $("<td></td>").text(tripitem.att_id);
				var cell5 = $("<td></td>").text(tripitem.hotel_id);
				var cell6 = $("<td></td>").text(tripitem.tripitem_staytime);
				var cell7 = $("<td></td>").text(tripitem.tripitem_begin);
				var cell8 = $("<td></td>").text(tripitem.tripitem_end);
				var cell9 = $("<td></td>").text(tripitem.tripitem_traffic);
				var cell10 = $("<td></td>").text(tripitem.tripitem_memo);
				var row = $("<tr></tr>").append([cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10]);
				myBody.append(row);				
			})
		})
	})
})
</script>
</body>
</html>
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
<title>查詢全部行程</title>
</head>
<body>
<h2 >Search byName or Search All</h2>
	<div >
		<label for="tripName">TripNAME</label> 
		<input type="text" class="form-control" id="trip_name" name="trip_name" placeholder="請輸入行程名稱" >
		<button type="button" id="search1">search</button>
	</div>
	<div >
		<label for="tripAll">LIST ALL</label> 
		<button type="button" id="search2">search all</button>
	</div>
	<div id="aa">
		<table id="tb1">
		<thead>
			<tr>
				<th>trip_id</th>				
				<th>trip_name</th>
				<th>trip_ctime</th>
				<th>trip_price</th>
				<th>mem_id</th>
			</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>

<!--  js/jquery-3.1.0.min.js 注意路徑-->
	<script src="../js/jquery-3.1.0.min.js"></script>  
	<script type="text/javascript">

$(function(){
	$("#search1").click(function() {
			$('#tb1>tbody').empty();
			var tripName = $("#trip_name").val(); 
			$.get(("TripServlet"),{"tripName":tripName,"method":"getByName"},function(data){
				$("#errName").remove();
					var myBody = $('#tb1>tbody');
// 					console.log("data:"+data);
					console.log("data.result:"+data.result);
				if(data.result==undefined){
				$.each(data,function(idx,trip){
// 					console.log("trip:"+trip);
// 					console.log("data.result:"+data.result);
				var cell1 = $("<td></td>").text(trip.trip_id);
				var cell2 = $("<td></td>").text(trip.trip_name);
				var cell3 = $("<td></td>").text(trip.trip_ctime);
				var cell4 = $("<td></td>").text(trip.trip_price);
				var cell5 = $("<td></td>").text(trip.mem_id);
				var row = $("<tr></tr>").append([cell1,cell2,cell3,cell4,cell5]);
				myBody.append(row);
				})
				}else{
	 				var errSpan= document.createElement("span");
	 				var errMsg = document.createTextNode(data.result);
					errSpan.appendChild(errMsg);
					errSpan.setAttribute("style","color:red");
					errSpan.setAttribute("id","errName");
					$("#errName").remove();
					$("#search1").after(errSpan);
				}
			})
		});
	
	$("#search2").click(function() {
				$('#tb1>tbody').empty();
		$.getJSON(("TripServlet"),{"method":"getAll"},function(data){
			var myBody = $('#tb1>tbody');
			$.each(data,function(idx,trip){
				var cell1 = $("<td></td>").text(trip.trip_id);
				var cell2 = $("<td></td>").text(trip.trip_name);
				var cell3 = $("<td></td>").text(trip.trip_ctime);
				var cell4 = $("<td></td>").text(trip.trip_price);
				var cell5 = $("<td></td>").text(trip.mem_id);
				var row = $("<tr></tr>").append([cell1,cell2,cell3,cell4,cell5]);
				myBody.append(row);				
				
			})
		})
	})
})
</script>
</body>
</html>
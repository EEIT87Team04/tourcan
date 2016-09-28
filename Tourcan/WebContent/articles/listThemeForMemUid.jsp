<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我發表的討論</title>
</head>
<body>
<div class="container">
	<h2>我發表的討論</h2>
	<input type="hidden" id="mem_uid" name="mem_uid" value="rdSAmrdF3rUIAUL4A50YOqElJUR2" >
	<table class="table table-striped" id="tab1">
			<thead>
				<tr class="warning">
					<th>編號</th>				
					<th>主題名稱</th>
					<th>發表者</th>
					<th>建立時間</th>
				</tr>
			</thead>
			<tbody>		
			</tbody>
		</table>
<script src="${contextPath}/js/jquery-3.1.0.min.js"></script>  
	<script type="text/javascript">
		var myBody=$('#tab1>tbody')
		var memuid=$('#mem_uid').val();
		$(function(){
			$.getJSON(("${contextPath}/articles/ThemeServlet"),{"mem_uid":memuid,"method":"findByMemuid"},function(data){
				$.each(data,function(idx, tripvalue){
						console.log(idx);
						console.log(data);
					var cell1 = $('<td/>').text(tripvalue.theme_id);
					var cell2 = $("<td/>").text(tripvalue.theme_topic);
					var cell3 = $("<td/>").text(tripvalue.mem_uid);
					var cell4 = $('<td/>').text(tripvalue.theme_time)
					var row = $('<tr/>').append([cell1,cell2,cell3,cell4]);
					myBody.append(row);
					
				})
			
			
		})
		})
	
	</script>
</div>
</body>
</html>
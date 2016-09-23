<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<title>會員行程列表</title>
</head>
<body>
	<div class="container">
		<span style="font-size: 200%;font-weight:bold">我 的 行 程</span>	
			<div class='row'>
				<input type="hidden" id="mem_uid" name="mem_uid" value="h9nbaY43OGRODXAGp2XMMhskW9r1" >
				<input type="button" class="btn btn-info btn-lg  " id="serach" value="查詢">
				<input type="button" class="btn btn-defaulf btn-lg pull-right" id="cancel" value="取消查詢">
			</div>
		<br>
		<table class="table table-striped" id="tab1">
			<thead>
				<tr class="warning">
					<th class="col-md-5">主題名稱</th>
					<th class="col-md-4">總運算</th>
					<th class="col-md-3">創建時間</th>
<!-- 					<th class='pull right'><input type="button" class="btn btn-danger" value="刪除"></th> -->
					<th > 刪除</th>
				</tr>
			</thead>
			<tbody>		
			</tbody>
		</table>
		
	</div>
<script src="../js/jquery-3.1.0.min.js"></script>  
	<script type="text/javascript">
	$(function(){
		$('#tab1').hide();
		var buID;
		$('#serach').click(function(){			
			$('#tab1').show();
				var myBody = $('#tab1>tbody');
				myBody.empty();
				var memuid=$("#mem_uid").val();
// 			console.log(memuid);
			$.getJSON(("TripServlet"),{"mem_uid":memuid,"method":"findByMemuid"},function(data){
				$.each(data,function(idx, tripvalue){
// 					console.log(idx);
					buID=idx;
					var input3= $("<input/>").attr("type","hidden").attr('name','method').val("findByID");
					var input2= $("<input/>").attr("type","hidden").attr('name','tripId').val(tripvalue.trip_id);
					var input1= $("<input/>").attr("class","btn btn-default btn-lg").attr('type','submit').val(tripvalue.trip_name);
					var form1 = $('<form/>').attr("method","get").attr("action","TripServlet").append([input1,input2,input3]);
					var cell1 = $("<td/>").append([form1]);
					var cell2 = $("<td/>").text(tripvalue.trip_price);
					var cell3 = $("<td/>").text(tripvalue.trip_ctime);
					var button41= $("<button/>").attr("class","btn btn-danger d22 ").attr("id","bu"+""+idx).val(tripvalue.trip_id).text("刪除");
					var cell4 = $("<td/>").append([button41]);
					var row = $('<tr/>').attr("id","tr"+""+idx).val(tripvalue.trip_id).append([cell1,cell2,cell3,cell4]);
					myBody.append(row);
					
				})
				trhover();

				$('.d22').off("click").on("click",function(){					
					console.log($(this).val())
					$('#tab1').show();
				});
			})
		})	
		click1();
	})
	
	function trhover(){
		$("tr").not(':first').hover(
				function () {
				    $(this).css("background","#FFBD9D");},
			    function () {
					$(this).css("background","");});
	}
	
	
	function click1(){
		$('#cancel').click(function(){
			$('#tab1').hide();
			console.log("123123")
		})	
	}
// 	function click2(){
// 		$('table > tbody').delegate("tr","click",function(){
// 			alert($(this).val())
// 		})	
// 	}
	
	</script>
</body>
</html>
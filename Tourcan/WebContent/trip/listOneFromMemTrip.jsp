<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html >
<jsp:include page="/frontPageHeader.jsp"/>
			<div class='row'>
		<span style="font-size: 200%;font-weight:bold">我 的 行 程</span>	
				<input type="hidden" id="mem_uid" name="mem_uid" value="${vo.mem_uid}" >
<!-- 				<input type="button" class="btn btn-info btn-lg  " id="serach" value="查詢"> -->
<!-- 				<input type="button" class="btn btn-defaulf btn-lg pull-right" id="cancel" value="取消查詢"> -->
				<input type="button" class="btn btn-info btn-lg pull-right" id="insetNew" value="新增行程" onclick="javascript:location.href='<%=request.getContextPath()%>/trip/tripView.jsp'">
			</div>
		<br>
		<table class="table table-striped" id="tab1">
			<thead>
				<tr class="warning">
					<th class="col-md-5">行程名稱</th>
					<th class="col-md-4">總預算</th>
					<th class="col-md-3">創建時間</th>
					<th >刪除</th>
				</tr>
			</thead>
			<tbody>		
			</tbody>
		</table>
		
<script src="${contextPath}/js/jquery-3.1.0.min.js"></script>  
	<script type="text/javascript">
	
	function searchCheck(){
		$('#tab1').show();
		var myBody = $('#tab1>tbody');
		myBody.empty();
		var memuid=$("#mem_uid").val();
//		console.log(memuid);
	$.getJSON(("${contextPath}/trip/TripServlet"),{"mem_uid":memuid,"method":"findByMemuid"},function(data){
		$.each(data,function(idx, tripvalue){
//				console.log(idx);
			var input3= $("<input/>").attr("type","hidden").attr('name','method').val("findByID");
			var input2= $("<input/>").attr("type","hidden").attr('name','tripId').val(tripvalue.trip_id);
			var input1= $("<input/>").attr("class","btn btn-default btn-lg").attr('type','submit').val(tripvalue.trip_name);
			var form1 = $('<form/>').attr("method","get").attr("action","../tripitem/TripitemServlet").append([input1,input2,input3,hiddenId]);
			var cell1 = $("<td/>").append([form1]);
			var cell2 = $("<td/>").text(tripvalue.trip_price);
			var cell3 = $("<td/>").text(tripvalue.trip_ctime);
			var hiddenId=$("<input/>").attr("type","hidden").attr("name","method").attr("value","findByID");
			var button41= $("<button/>").attr("class","btn btn-danger d22").attr("id","bu"+""+idx).val(tripvalue.trip_id).text("刪除");
			var cell4 = $("<td/>").append([button41]);
			var row = $('<tr/>').attr("id","tr"+""+idx).val(tripvalue.trip_id).append([cell1,cell2,cell3,cell4]);
			myBody.append(row);
			
		})
		trhover();

		$('.d22').click(function(){
			var id1=$(this).val()
//				console.log($(this).parent().parent())
			var ab=$(this).parent().parent()
			$.ajax({
					type:"delete",
					url:"TripServlet?"+$.param({"tripId":id1}),
					dataType:"json",
					success:function(){
//							alert("success")
						ab.remove();
					}

			})
			
		});
	})
	}
	
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
		})	
	}
	
	
	
	
	$(function(){
		searchCheck();

		$('#serach').click(function(){
			searchCheck();
		})	
		click1();
	})
// 	function click2(){
// 		$('table > tbody').delegate("tr","click",function(){
// 			alert($(this).val())
// 		})	
// 	}
	
	</script>
<jsp:include page="/frontPageFooter.jsp"/>
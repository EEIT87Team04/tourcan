<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>show all Theme</title>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<style type="text/css">
.tbody{
border: 3px double;
}

</style>

</head>
<body>
<!-- <button >123</button> -->
<div class="container">
	<div id="aa">
		<table id="tb1" class="table table-bordered">
			<thead>
				<tr>
					<th>id</th>				
					<th>topic</th>
					<th>memUID</th>
					<th>catalog</th>
					<th>修改</th>
					<th>刪除</th>			
					
				</tr>
			  </thead>
			<tbody></tbody>
		</table>
	</div>
</div>
<div id="d1">
<!-- <button id="bbb">213123</button> -->
</div>
<script type="text/javascript">
$(function(){
// 				var updatab= document.createElement("button");
// 				var upMsg = document.createTextNode("updatab");
				var upMsg = "updata";
				var deMsg = "delete";
// 				updatab.setAttribute("value","");
// 				updatab.appendChild(upMsg);
// 				$("#d1").after(updatab)
				
	$.getJSON(("ThemeServlet"),function(data){
		var myBody = $('#tb1>tbody');
		$.each(data,function(idx,theme1){
			
				var updatab=$("<button></button>").append(upMsg);
				var deleteb=$("<button></button>").append(deMsg);
				updatab.prop("value",theme1.theme_id);
				updatab.prop("id","u"+theme1.theme_id);
				deleteb.prop("value",theme1.theme_id);
				var cell1 = $("<td></td>").text(theme1.theme_id);
				var cell2 = $("<td></td>").text(theme1.theme_topic);
				var cell3 = $("<td></td>").text(theme1.mem_uid);
				var cell4 = $("<td></td>").text(theme1.theme_catalog);
				var cell5 = $("<td></td>").append(updatab);				
				var cell6 = $("<td></td>").append(deleteb);				
				var row = $("<tr></tr>").append([cell1,cell2,cell3,cell4,cell5,cell6]);
				myBody.append(row);
				
			
		});
		
		console.log($('td:eq(2)').val())
	$("tbody >button").click(function(){
// 		var theme_id= ($("#u"+theme1.theme_id).val())
// 			json = {};
// 				json["theme_id"]=+theme_id;
// 			$.ajax({
// 				"type":"PUT",
// 				"url":"ThemeServlet?theme_id="+theme_id,
// 				"dataType":"json",
// 				"data":JSON.stringify(json),	
// // 				"success":function(data){
		
// 				"complete":function(){location.href ="queryOneTheme_update.jsp"}//跳转页面
// // 				})
// 			   })
			})
	});
	
	
	
	
	
});

</script>


</body>
</html>
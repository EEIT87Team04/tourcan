<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">

/*  table, th  { 
    border: 5px ; 
 } */ 
 /* td { 
 	border:2px solid black; 
 }  */
.error {
   color: #ff0000;
   font-size: 12px;
   margin-top: 5px;
   margin-bottom: 0;
}
 
.inputTxtError {
   border: 1px solid #ff0000;
   color: #0e0e0e;
}
</style>
<title>query theme by list</title> 
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
  <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <link href="//cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
  <script src="//cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
</head>
<body>
<div class="container">
	<div>
		<input type="button" value="New Theme" id="but1" /> 
		<input type="button" value=" New Response" id="but2" />
	</div>

<!-- 	<div> -->
<!-- 		<label for="themeID">ThemeID</label> <input type="text" -->
<!-- 			class="form-control" id="themeID" name="themeID" -->
<!-- 			 placeholder="id"> -->
<!-- 	</div> -->

	<div>
			<label for="respTopic">Search RespTopice</label> <input type="text"
			class="form-control" id="resp_topic" name="resptopic"
			placeholder="respTopic">
	</div>
<!-- 	<div> -->
<!-- 		<label for="respAll">LIST ALL Theme</label> -->
<!-- 	</div> -->
<!-- 		<div>	 -->
<!-- 			<button type="button" id="search3" class="btn btn-default">search all</button> -->
<!-- 		</div> -->
	<div id="d1"></div>
	
	<div class="row">
			<div class="col-md-6">
				<button type="button" class="btn btn-success form-control"
				id="btnIdCheck">TopicCheck</button>
			</div>
			<div class="col-md-6">
				<button type="button" class="btn btn-danger form-control"
				id="btnReset">Reset</button>
			</div>	
	</div>
	<hr>
	<div id="aa">
		<table id="tb1" class="table table-bordered">
		<thead>
			<tr>
				<th>id</th>				
				<th>topic</th>
				<th>memUID</th>
				<th>themeID</th>
				
			</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
</div>	

	
	
	<script type="text/javascript">
$(function(){
	$("#btnIdCheck").click(function() {
		resetErrors();
		var resp_topic = $("#resp_topic").val();
		$('#tb1>tbody').empty();
		$("#d1").empty();
// 			if(attno2==null||attno2.trim().length==0){
// 				var errSpan= document.createElement("span");
// 				var errMsg = document.createTextNode("plz enter number");
// 				errSpan.appendChild(errMsg);
// 				errSpan.setAttribute("style","color:red");
// 				errSpan.setAttribute("id",'errid')
// 				$("#errid").remove();
// 				$("#search1").after(errSpan);
// 			}else{
		$.getJSON(("RespServlet"),{"resp_topic" : resp_topic},function(data){
// 			$("#errid").remove();
			var myBody = $('#tb1>tbody');
			$.each(data,function(respName,respValue){
				if(respName=="resp_topic"){
						console.log(respName);
				    if(respValue=="respTopic search error" || respValue=="無此Resp"){
					    alert("查無編號!");
					    var msg = '<label class="error" for="respTopic">'+respValue+'</label>';
						$('input[name="resptopic"]').addClass('inputTxtError').after(msg);
					}
		        
				}else{
// 				var j1= JSON.stringify(theme1);
// // 				console.log(j1);
//  					if(j1.indexOf("error")>-1){
// 						var p1=$("<h3></h3>").text(theme1);
// // 				$("#h3").setAttribute("style","color:red");
// 						$("#d1").append(p1);
 					
						var cell1 = $("<td></td>").text(respValue.resp_id);
 						var cell2 = $("<td></td>").text(respValue.resp_topic);
 						var cell3 = $("<td></td>").text(respValue.mem_uid);
 						var cell4 = $("<td></td>").text(respValue.theme_id);						
 						var row = $("<tr></tr>").append([cell1,cell2,cell3,cell4]);
 						myBody.append(row);
}
				})
		})
// 	}
	})
	
	
// 	$("#search3").click(function() {
// 				$('#tb1>tbody').empty();
// 		$.getJSON(("ThemeServlet"),function(data){
// 			var myBody = $('#tb1>tbody');
// 			$.each(data,function(idx,resp1){
// 					var cell1 = $("<td></td>").text(resp1.resp_id);
// 					var cell2 = $("<td></td>").text(resp1.resp_topic);
// 					var cell3 = $("<td></td>").text(resp1.mem_uid);
// 					var cell4 = $("<td></td>").text(resp1.theme_id);					
// 					var row = $("<tr></tr>").append([cell1,cell2,cell3,cell4]);
// 					myBody.append(row);
				
// 			})
// 		})
// 	});
	function resetErrors() {
	    $('form input, form select').removeClass('inputTxtError');
	    $('label.error').remove();
	}
	});
	</script>
</body>
</html>
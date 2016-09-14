<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>query One Response and Update</title>
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
<style type="text/css">


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


</head>
<body>
<div class="container">
	<div>
		<div>
<!-- 			<input type="button" value="New Theme" id="but1" />  -->
<!-- 			<input type="button" value=" New Response" id="but2" /> -->
		</div>
	</div>
	<br>		
	<div>
		<h3>Search for respID</h3>
		<div>
			<div>
				<form name="idCheckesp" id="idCheckesp">
					<div>
						<div >
							<label for="respId">Search 編號Id</label> <input type="text"
							class="form-control" id="resp_id" name="respid"
							placeholder="respid" >
						</div>
					</div>
					<div class="col-md-6">
						<button type="button" class="btn btn-success form-control"
							id="btnIdCheck">IdCheck</button>
					</div>
					<div class="col-md-6">
						<button type="button" class="btn btn-warning form-control"
							id="btnEdit">edit</button>
					</div>
				</form>
			</div>
		<div>
			<form name="updateResp" class="form-horizontal">
				<div>
					<h2>Update Response </h2>
				</div>
			<div class="form-group">
				<label  class="control-label col-sm-2" for="themeId"> 編號Id</label> 
				<div class="col-sm-6"> 
				<input type="text" class="form-control" id="theme_id" name="theme_id" placeholder="themeid" >
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="memId">會員UID</label>
				<div class="col-sm-6"> 
					<input type="text" class="form-control"  id="mem_uid" name="mem_uid" placeholder="會員Id" min=-1>
				</div>
			</div>
			<div class="form-group">			
				<label class="control-label col-sm-2" for="respTopic">respTopic</label>
				<div class="col-sm-6">	
					<input type="text" class="form-control" id="resp_topic" name="resp_topic" placeholder="respTopic">
				</div>	
			</div>
			<div>
				<div id="resp_article">
				
				</div>
			</div>
			<div>
				<textarea name="resp_article" id="theme_article2" class="form-control"></textarea>
			</div>
			<div>
				<button type="button" class="btn btn-danger form-control"
						id="btnUpdate">Update</button>
			</div>
			</form>
			</div>
		</div>
	</div>
</div>
<!-- 			<input type="button" id="b1" value="undo">  -->
<!-- 	<script src="../js/jquery-3.1.0.min.js"></script> -->
	<script type="text/javascript">
$(function(){
		
	$("#btnIdCheck").click(function() {
		resetErrors();
		var resp_id = $("#resp_id").val();
		
		$.getJSON(("RespServlet"), {"resp_id" : resp_id}, function(data) {
//				console.log(data);
			$.each(data, function(respName, respValue) {
			    	$("#"+respName).val(respValue);
			    	

					if(respName=="resp_article"){
// 						console.log(themeValue);
					$('#resp_article').summernote('code', respValue);		
					$('#theme_article2').summernote('code', respValue);		
					 var makrup = $('#resp_article').summernote('code');
					 
					 $("#resp_article").summernote('destroy');
					}
// 					if(themeName=="theme_id"){
// 						console.log(themeValue);
// 			 			$("#theme_id").prop("readonly", true);
// 					}
					
					if(respName=="respId"){
// 						console.log(themeName);
					    if(respValue=="編號只能為整數" || respValue=="無此編號"){
						    alert("查無編號!");
						    var msg = '<label class="error" for="respId">'+themeValue+'</label>';
							$('input[name="respid"]').addClass('inputTxtError').after(msg);
						}
			        
					}
			});
	   });				
    });
	$("#btnEdit").click(function(){
// 		var theme_topic = $("#theme_topic").val();
// 		console.log((theme_topic))	
		 $('#theme_article2').summernote({			 
			 height:200
			 });
		 $("#resp_article").remove();
	})
	
	
		$("#btnUpdate").click(function() {
				resetErrors();				
				var resp_id = $("#resp_id").val();				
				var form = $(document.updateResp).serializeArray();
				var nameValue = $(document.updateResp).serialize();
				json = {};
				json["resp_id"]=+resp_id;
				for (var i = 0; i < form.length; i++){
// 					$("#theme_id").prop("readonly", true);
					json[form[i].name] = form[i].value;				

				}
				console.log(JSON.stringify(json));
				
				$.ajax({
					"type":"PUT",
					"url":"RespServlet?resp_id="+resp_id,
					"dataType":"json",
					"data":JSON.stringify(json),					
					"success":function(data){
					$.each(data,function(respinx,respValue){						
							
					if(respValue=="修改成功"){
								alert("修改成功!");
							}							
							if(respValue=="修改失敗"){
								alert("修改失敗!");
							}
							
							var msg = '<label class="error" for="'+respinx+'">'+respValue+'</label>';
							$("input[name=" + respinx + "], select[name=" + respinx + "], textarea[name=" + respValue + "]").addClass('inputTxtError').after(msg);																				
						});
// 							console.log(JSON.stringify(json));
                        }					
				})		
			 
				.done(function(data)
			    {
	 				console.log("200.");
	 				
	 				$.each(data,function(themeName,themeValue){
	 				if(themeValue=="修改成功"){
// 	 				document.idCheckResp.reset();
// 	 				document.attUpdate.reset();
// 	 				$("#att_id").prop("readonly", false);
	 				}
	 				});
			    })
				.fail(function(xhr) 
				{
    			   console.log("ERR.");
				});
			});	
	
	
	
	function resetErrors() {
	    $('form input, form select').removeClass('inputTxtError');
	    $('label.error').remove();
	}	
	
	})
	 
// 	 $('#summernote').summernote({
// 		 height:200
// 		 });
// 	 $("#b1").click(function(){
		
// 	$('#summernote').summernote('undo');
		 
// 	 })
// 	$('#summernote').summernote('redo');
// 	$('#summernote').summernote('reset');

		
		
	</script>

</body>
</html>
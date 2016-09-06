<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>query One Theme and Update</title>
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
			<input type="button" value="New Theme" id="but1" /> 
			<input type="button" value=" New Response" id="but2" />
		</div>
	</div>
	<br>		
	<div>
		<div>
			<h3>Search for themeID</h3>
			<div>
				<form name="idCheckTheme" id="idCheckTheme">
					<div>
						<div >
							<label for="themeId">Search 編號Id</label> <input type="text"
							class="form-control" id="theme_id" name="themeid"
							placeholder="themeid" >
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
			<form name="updateTheme" class="form-horizontal">
				<div>
					<h2>Update Theme </h2>
				</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="memId">會員UID</label>
				<div class="col-sm-6"> 
					<input class="form-control" type="text" id="mem_uid" name="mem_uid" placeholder="會員Id" min=-1>
				</div>
			</div>
			<div class="form-group">			
				<label class="control-label col-sm-2" for="themeTopic">themeTopic</label>
				<div class="col-sm-6">	
					<input type="text" class="form-control" id="theme_topic" name="theme_topic" placeholder="themeTopic">
				</div>	
			</div>
			<div class="form-group">		
				<label class="control-label col-sm-2" for="themeCatalog">catalog</label> 
				<div class="col-sm-6">	
					<input type="number" class="form-control" id="theme_catalog" name="theme_catalog" placeholder="catalog" min=0>
				</div>
			</div>								
			<div>
				<div id="theme_article">
				
				</div>
			</div>
			<div>
				<textarea name="theme_article" id="theme_article2" class="form-control"></textarea>
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
		var theme_id = $("#theme_id").val();
		
		$.getJSON(("ThemeServlet"), {"theme_id" : theme_id}, function(data) {
//				console.log(data);
			$.each(data, function(themeName, themeValue) {
			    	$("#"+themeName).val(themeValue);
			    	

					if(themeName=="theme_article"){
// 						console.log(themeValue);
					$('#theme_article').summernote('code', themeValue);		
					$('#theme_article2').summernote('code', themeValue);		
					 var makrup = $('#theme_article').summernote('code');
					 
					 $("#theme_article").summernote('destroy');
					}
// 					if(themeName=="theme_id"){
// 						console.log(themeValue);
// 			 			$("#theme_id").prop("readonly", true);
// 					}
					
					if(themeName=="themeId"){
// 						console.log(themeName);
					    if(themeValue=="編號只能為整數" || themeValue=="無此編號"){
						    alert("查無編號!");
						    var msg = '<label class="error" for="themeId">'+themeValue+'</label>';
							$('input[name="themeid"]').addClass('inputTxtError').after(msg);
						}
			        
					}
			});
	   });				
    });
	$("#btnEdit").click(function(){
		var theme_topic = $("#theme_topic").val();
		console.log((theme_topic))	
		 $('#theme_article2').summernote({			 
			 height:200
			 });
		 $("#theme_article").remove();
	})
	
	
		$("#btnUpdate").click(function() {
// 				resetErrors();				
				var theme_id = $("#theme_id").val();				
				var form = $(document.updateTheme).serializeArray();
				var nameValue = $(document.updateTheme).serialize();
				json = {};
				json["theme_id"]=+theme_id;
				for (var i = 0; i < form.length; i++){
// 					$("#theme_id").prop("readonly", true);
					json[form[i].name] = form[i].value;				

				}
				console.log(JSON.stringify(json));
				
				$.ajax({
					"type":"PUT",
					"url":"ThemeServlet?theme_id="+theme_id,
					"dataType":"json",
					"data":JSON.stringify(json),					
					"success":function(data){
					$.each(data,function(themeinx,themeValue){						
							
					if(themeValue=="修改成功"){
								alert("修改成功!");
							}							
							if(themeValue=="修改失敗"){
								alert("修改失敗!");
							}
							
							var msg = '<label class="error" for="'+themeinx+'">'+themeValue+'</label>';
							$("input[name=" + themeinx + "], select[name=" + themeinx + "], textarea[name=" + themeValue + "]").addClass('inputTxtError').after(msg);																				
						});
// 							console.log(JSON.stringify(json));
                        }					
				})		
			 
				.done(function(data)
			    {
	 				console.log("200.");
	 				
	 				$.each(data,function(themeName,themeValue){
	 				if(themeValue=="修改成功"){
	 				document.idCheckTheme.reset();
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
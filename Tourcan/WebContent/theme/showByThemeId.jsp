<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show Theme</title>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
	<style type="text/css">
#mapPreview {
	height: 292px;
}

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
	<div>
		<div>
			<input type="button" value="New Theme" id="but1" /> 
			<input type="button" value=" New Response" id="but2" />
		</div>
	</div>
	<br>		
	<div>
		<div>
			<div>
				<div>
					<label for="themeId">Search 編號Id</label> <input type="text"
					class="form-control" id="theme_id" name="themeid"
					placeholder="themeid" >
				</div>
			</div>
			<div>
					<button type="button" class="btn btn-success form-control"
						id="btnIdCheck">IdCheck</button>
			</div>
			<div>
					<button type="button" class="btn btn-danger form-control"
						id="btnReset">Reset</button>
			</div>
		<div>
			<div>
				<label for="memId">會員Id</label> <input type="text"
							id="mem_id" name="memid"
							placeholder="會員Id" min=-1>
				<label for="themeTopic">themeTopic</label><input type="text" 
					id="theme_topic" name="themetopic" 
					placeholder="themeTopic">
				
				<label for="themeCatalog">catalog</label> <input type="number"
					id="theme_catalog" name="themecatalog"
					placeholder="catalog" min=0>
			</div>								
		</div>
			<div>
				<div id="theme_article">
				
				</div>
		</div>
	</div>
</div>
<!-- 			<input type="button" id="b1" value="undo">  -->

	<script type="text/javascript">
	
	$("#btnIdCheck").click(function() {		
		var theme_id = $("#theme_id").val();
		var theme_topic = $("#theme_topic").val();
		console.log((theme_topic))	
		
		$.getJSON(("ThemeServlet"), {"theme_id" : theme_id}, function(data) {
//				console.log(data);
			$.each(data, function(themeName, themeValue) {
			    	$("#"+themeName).val(themeValue);
			    	

					if(themeName=="theme_article"){
// 						console.log(themeValue);
					$('#theme_article').summernote('code', themeValue);		
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
		
// 		$.getJSON(("ThemeServlet"), {"theme_topic" : theme_topic}, function(data) {
// //				console.log(data);
// 			$.each(data, function(themeName, themeValue) {
// 			    	$("#"+themeName).val(themeValue);
			    	

// 					if(themeName=="theme_article"){
// // 						console.log(themeValue);
// 					$('#theme_article').summernote('code', themeValue);		
// 					 var makrup = $('#theme_article').summernote('code');
// 					 $("#theme_article").summernote('destroy');
// 					}
// // 					if(themeName=="theme_id"){
// // 						console.log(themeValue);
// // 			 			$("#theme_id").prop("readonly", true);
// // 					}
					
// 					if(themeName=="themeTopic"){
// 						console.log(themeName);
// 					    if(themeValue=="themeTopice search error" || themeValue=="themeTopice error"){
// // 						    alert("查無編號!");
// 						    var msg = '<label class="error" for="themeTopic">'+themeValue+'</label>';
// 							$('input[name="themetopic"]').addClass('inputTxtError').after(msg);
// 						}
			        
// 					}
// 			});
// 	   });
		
    });
	
	
	 
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
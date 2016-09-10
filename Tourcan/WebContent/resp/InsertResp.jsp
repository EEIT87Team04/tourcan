<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Resp</title>
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<!--     <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>    -->
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
</head>
<body>
<div class="container">
	<div>
		<div>
			<input type="button" value="New Theme" id="but1" /> 
<!-- 			<input type="button" value=" New Response" id="but2" /> -->
		</div>
	</div>	
	<div>	
<!-- 		<form id="postForm" action="ThemeServlet" method="post"> -->
		<form name="addResp">
			<div class="row">
				<div  class="col-sm-6 col-sm-offset-3">
					<h2>Create New Response</h2>
				</div>
			</div>
			<div>
				<div class="form-inline">
				
					<div class="form-group">
						<label for="respTopic"  >respTopic</label>
						
						<input type="text" id="resp_topic" name="resp_topic"  class="form-control"	placeholder="respTopic">
						
					</div>
					<div class="form-group" >
						<label for="memId" >會員UId</label> <input type="text"
							id="mem_uid" name="mem_uid" class="form-control"
							placeholder="會員Id" >
					</div>
					<div class="form-group" >
						<label for="themeID" >themeId</label> <input type="number"
							id="theme_id" name="theme_id" class="form-control"
							placeholder="theme_id" min=1>
					</div>
					
				
				</div>
			</div>
			<div class="from-grop">
				<label for="">RespArticle</label>
				<div id="d2"></div>
				<textarea name="resp_article" id="resp_article" class="form-control"></textarea>
<!-- 				<div id="theme_article" ></div> -->
			
			
		<!-- 	<button type="submit" class="btn btn-primary">Save all</button> -->
		<!-- 	<button type="button" class="btn btn-primary" id="canel">Cancel</button> -->
			</div>
			  
<!-- 			<button id="save" class="btn btn-primary"  type="submit">Save </button> -->
			<div></div>
			<div class="col-md-3 col-sm-offset-8" >
				<button type="button" class="btn btn-success form-control" id="btnInsert">Save</button>
			</div>
<!-- 			<div class="col-md-3"> -->
<!-- 				<button id="cancel" class="btn btn-danger form-control"  type="button">Cancel</button> -->
<!-- 			</div> -->
<!-- 		</form> -->
		<div>
			<div id="result"></div>
		</div>
		</form>
<!-- 			<button id="show" class="btn" onclick="show()" type="button">show</button> -->
<!-- 			<button id="edit" class="btn btn-primary" onclick="edit()" type="button">Edit 1</button> -->
	</div>	
</div>



<!-- 	<script src="../js/jquery-3.1.0.min.js"></script> -->
<!-- 	<script src="https://cdn.quilljs.com/1.0.0-rc.4/quill.js"></script> -->

<!--   <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script> -->
<!--     <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script>  -->
  
	<script type="text/javascript">
	
// 	$('#summernote').summernote({
// 		  height: 300,                 // set editor height
// 		  width:  800,
// 		  minHeight: 100,             // set minimum height of editor
// 		  maxHeight: 1000,             // set maximum height of editor
// 		  focus: true                  // set focus to editable area after initializing summernote
// 		});
// 		 focus: true
		 $('#resp_article').summernote({
			 height:200
			 });
	 $(function() {
	
		 $("#btnInsert").click(function() {
				var errMsgSpan = $('form[name="addResp"] span');
				errMsgSpan.remove();
				var form = $(document.addResp).serializeArray(), json = {};
				for (var i = 0; i < form.length; i++) {
					
					if (form[i].value.length > 0) {
						json[form[i].name] = form[i].value;
					}
				}
					console.log(json);
				$.post("RespServlet", JSON.stringify(json)).done(function(data) {
					$.each(data, function(errAtt, errMsg) {
						console.log(errMsg);
						if (errMsg == "success") {
							document.addResp.reset();
							errMsgSpan.remove();
							
						}
						var errSpan = document.createElement("span");
						var errText = document.createTextNode(errMsg);
						var errId = 'err' + errAtt;
						errSpan.appendChild(errText);
						errSpan.setAttribute("style", "color:red");
						errSpan.setAttribute("id", errId);
						$('#' + errId).remove();
						$('#' + errAtt).after(errSpan);
					});
					console.log("200.");
					var makrup = $('#resp_article').summernote('code');
// 					$('#theme_article').summernote('destroy');
// 					$( "#theme_article" ).remove();
				}).fail(function(xhr) {
					console.log("ERR.");
				});
			});
	 
	 });
	 
	 
// 		var edit = function() {
// 			  $('#theme_article').summernote({focus: true});
// 			};

// 		var show = function() {
// 		  var makrup = $('#resp_article').summernote('code');
// 		  console.log($('#resp_article').summernote('code'));
// 		  $('#resp_article').summernote('destroy');
// 		};
	
	
	
	
	</script>
	
	
	
	
</body>
</html>
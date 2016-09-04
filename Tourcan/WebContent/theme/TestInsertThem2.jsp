<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Theme</title>
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<!--     <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>    -->
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
</head>
<body>
	<div>
		<div>
			<input type="button" value="New Theme" id="but1" /> 
			<input type="button" value=" New Response" id="but2" />
		</div>
	</div>	
	<div>	
<!-- 		<form id="postForm" action="ThemeServlet" method="post"> -->
			<form name="addTheme">
			<div>
				<div>
					<h2>Create New Theme</h2>
				</div>
			</div>
			<div>
				<div>
					<div>
						<label for="themeTopic">themeTopic</label><input type="text" 
						id="theme_topic" name="theme_topic" 
						placeholder="themeTopic">
					</div>
				</div>
						
				<div>
					<div>
						<label for="memId">會員Id</label> <input type="text"
							id="mem_id" name="mem_id"
							placeholder="會員Id" min=-1>
					
						<label for="themeCatalog">catalog</label> <input type="number"
							id="theme_catalog" name="theme_catalog"
							placeholder="catalog" min=0>
						</div>
				</div>
			</div>
			<div class="from-grop">
				<label for="">ThemeArticle</label>
				<div id="d2"></div>
				<textarea name="theme_article" id="theme_article" class="form-control"></textarea>
<!-- 				<div id="theme_article" ></div> -->
			
			
		<!-- 	<button type="submit" class="btn btn-primary">Save all</button> -->
		<!-- 	<button type="button" class="btn btn-primary" id="canel">Cancel</button> -->
			</div>
			  
<!-- 			<button id="save" class="btn btn-primary"  type="submit">Save </button> -->
			<button type="button" class="btn btn-success form-control" id="btnInsert">Save</button>
			<button id="cancel" class="btn btn-danger form-control"  type="button">Cancel</button>
<!-- 		</form> -->
		<div>
			<div id="result"></div>
		</div>
		</form>
			<button id="show" class="btn" onclick="show()" type="button">show</button>
			<button id="edit" class="btn btn-primary" onclick="edit()" type="button">Edit 1</button>
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
		 $('#theme_article').summernote({
			 height:200
			 });
	 $(function() {
	
		 $("#btnInsert").click(function() {
				var errMsgSpan = $('form[name="addTheme"] span');
				errMsgSpan.remove();
				var form = $(document.addTheme).serializeArray(), json = {};
				for (var i = 0; i < form.length; i++) {
					
					if (form[i].value.length > 0) {
						json[form[i].name] = form[i].value;
					}
				}
				$.post("ThemeServlet", JSON.stringify(json)).done(function(data) {
					console.log(data);
					$.each(data, function(errAtt, errMsg) {
						console.log(errMsg);
						if (errMsg == "success") {
							document.addTheme.reset();
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
// 					var makrup = $('#theme_article').summernote('code');
					$('#theme_article').summernote('destroy');
					$( "#theme_article" ).remove();
				}).fail(function(xhr) {
					console.log("ERR.");
				});
			});
	 
	 });
	 
	 
		var edit = function() {
			  $('#theme_article').summernote({focus: true});
			};

		var show = function() {
		  var makrup = $('.theme_article').summernote('code');
		  console.log($('.theme_article').summernote('code'));
		  $('.theme_article').summernote('destroy');
		};
	
	
	
	
	</script>
	
	
	
	
</body>
</html>
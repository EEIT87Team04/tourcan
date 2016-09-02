<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRUD Theme</title>
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<!--     <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>    -->
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
</head>
<body>
	<H2>Theme View</H2>
	<div>
		<input type="button" value="New Theme" id="but1" /> 
		<input type="button" value=" New Response" id="but2" />
	</div>


	<div id="d1"></div>
	<hr>
	<hr>
	<div>
		<label for="themeID">ThemeID</label> <input type="text"
			class="form-control" id="themeID" name="themeID"
			 placeholder="id">
		<button type="button" id="search1">search</button>
	</div>
	<div>
		<label for="themeTopice">ThemeTopice</label> <input type="text"
			class="form-control" id="themeTopice" name="themeTopice"
			placeholder="themeTopice">
		<button type="button" id="search2">search</button>
	</div>
	<div>
		<label for="themeAll">LIST ALL Theme</label>
		<button type="button" id="search3">search all</button>
	</div>
	<hr>
	<br>
	<form action="">
	<div class="click2edit"></div>
	
<!-- 	<div id="summernote"><p>Hello Summernote</p></div> -->
	<hr>
	  
</form>
	<button id="edit" class="btn btn-primary" onclick="edit()" type="button">Edit</button>
	<button id="save" class="btn btn-primary" onclick="save()" type="button">Save </button>
	




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
	
	var edit = function() {
		  $('.click2edit').summernote({focus: true});
		};

		var save = function() {
		  var makrup = $('.click2edit').summernote('code');
		  console.log(typeof($('.click2edit').summernote('code')));
		  $('.click2edit').summernote('destroy');
		};
	
	
	
	
	</script>
	
	
	
	
</body>
</html>
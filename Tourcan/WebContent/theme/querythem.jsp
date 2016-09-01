<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://cdn.quilljs.com/1.0.0-rc.4/quill.snow.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRUD Theme</title>
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
	<div id="d2">
	
	</div>
	<hr>
	<hr>
	<div id="toolbar">
 	 <button class="ql-bold">Bold</button>
  	<button class="ql-italic">Italic</button>
	</div>
	
	<div id="editor">
  		<p>Hello World!</p>
  		<p>Some initial <strong>bold</strong> text</p>
  		<p><br></p>
	</div>





	<script src="../js/jquery-3.1.0.min.js"></script>
	<script src="https://cdn.quilljs.com/1.0.0-rc.4/quill.js"></script>
	<script type="text/javascript">
	
	  var editor = new Quill('#editor', {
	    modules: { toolbar: '#toolbar' },
	    theme: 'snow'
	  });
		
// 		quill.addModule('toolbar', Image );
		
		var delta = editor.getContents();
		console.log(typeof(delta))
		$("#d2").html(delta);
		
	</script>
	
	
	
	
</body>
</html>
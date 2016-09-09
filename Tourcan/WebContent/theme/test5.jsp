<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://cdn.quilljs.com/1.0.0-rc.4/quill.snow.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRUD Theme</title>
<style type="text/css">
.quill-wrap {
  max-width:900px;
  width:100%;
  margin-left:auto;
  margin-right:auto;
  margin-top:20px;
}
/* .info { */
/*   text-align:center; */
/*   margin-top:20px; */
/* } */
.quill-wrap .ql-active {
  border: 1px solid #ccc !important;
  border-radius:4px;
}
.quill-wrap button, .quill-wrap .ql-picker {
  margin-right:2px
}




</style>

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
	<div class="quill-wrap">
<!-- Create the toolbar container -->
<div id="toolbar">
      <span class="ql-formats">
        <select class="ql-header">
          <option value="1">Heading</option>
          <option value="2">Subheading</option>
          <option selected>Normal</option>
        </select>
<!--         <select class="ql-font"> -->
<!--           <option selected>Sans Serif</option> -->
<!--           <option value="serif">Serif</option> -->
<!--           <option value="monospace">Monospace</option> -->
<!--         </select> -->
      </span>
      <span class="ql-formats">
        <button class="ql-bold"></button>
        <button class="ql-italic"></button>
        <button class="ql-underline"></button>
        <button class="ql-strike"></button>
      </span>
      <span class="ql-formats">
        <select class="ql-color"></select>
        <select class="ql-background"></select>
      </span>
      <span class="ql-formats">
        <button class="ql-list" value="ordered"></button>
        <button class="ql-list" value="bullet"></button>
        <select class="ql-align">
          <option selected></option>
          <option value="center"></option>
          <option value="right"></option>
          <option value="justify"></option>
        </select>
      </span>
      <span class="ql-formats">
<!--         <button class="ql-blockquote"></button> -->
        <button class="ql-link"></button>
        <button class="ql-image"></button>
<!--         <button class="ql-code-block"></button> -->
<!--         <button class="ql-video"></button> -->
      </span>
      <span class="ql-formats">
        <button class="ql-clean"></button>
      </span>
</div>
	
	<div id="editor">
  		<p>Hello World!</p>
  		<p>Some initial</p> 
  		<p>1<br>1</p>
	</div>
<button id="save" class="btn btn-primary" onclick="save()" type="button">Save 2</button>
<input type="button" id="b1"  value="but1">



	<script src="../js/jquery-3.1.0.min.js"></script>
	<script src="https://cdn.quilljs.com/1.0.0-rc.4/quill.js"></script>
	<script type="text/javascript">
	
	  var quill = new Quill('#editor', {
		    modules: { toolbar: '#toolbar' },
		    theme: 'snow'
		  });
		
		
		var delta = quill.getContents();
// 		console.log(delta);
// 		$("#d2").html(delta);
// 			$.each(editor,function(indx,s1){
// 				console.log(s1);
// 				$("#d2").text(s1);
// 				 console.log(  $("#d2").text(s1) );
				
// 			 })
		
// 		quill.on('text-change', function(delta, oldDelta, source) { 
// // 		console.log(delta); 
// 		var text1 = quill.getText(); 
// 			$("#b1").click(function(){
// 				$("#d2").html(text1);
// // 				$("#d2").text(text1);
			
				
// 			})
// // 		console.log(text); 
// 		});
// 		quill.on('selection-change', function(range) {
// // 		        console.log('selection-change', range)
// 		      });
			
		quill.on('editor-change', function(eventName ,arguments) {
			$("#b1").click(function(){
			console.log( arguments)
				$("#d2").html(arguments);
				
			})
			});
		
		
		
		
		
		
	</script>
	
	
	
	
</body>
</html>
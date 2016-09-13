<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>發表新主題</title>
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
  <script src="../lang/summernote-zh-TW.js"></script>
  
</head>
<body>
<div class="container">
	<div>
		<div>
<!-- 			<input type="button" value="New Theme" id="but1" />  -->
<!-- 			<input type="button" value=" New Response" id="but2" /> -->
		</div>
	</div>	
	<div>	
<!-- 		<form id="postForm" action="ThemeServlet" method="post"> -->
		<form name="addTheme">
			<div class="row">
				<div  class="col-sm-6 col-sm-offset-3">
					<h2>發表新主題</h2>
				</div>
			</div>
			<div>
				<div class="form-inline">
				
					<div class="form-group">
						<label for="themeTopic"  >主題名稱</label>						
						<input type="text" id="theme_topic" name="theme_topic"  class="form-control"	placeholder="themeTopic">
						
					</div>
					<div class="form-group" >
						<label for="memId" >會員UID</label> <input type="text"
							id="mem_uid" name="mem_uid" class="form-control"
							placeholder="會員Id" >
					</div>
<!-- 					<div class="form-group"> -->
<!-- 						<label for="themeCatalog">catalog</label> <input type="number" -->
<!-- 							id="theme_catalog" name="theme_catalog" class="form-control" -->
<!-- 							placeholder="catalog" min=1> -->
<!-- <!-- 							<input type="hidden" name="action" value="qq"> -->
<!-- 						</div> -->
				
				</div>
			</div>
			<div class="from-grop">
				<label for="">內容:</label>
				<div id="d2"></div>
				<textarea name="theme_article" id="theme_article" class="form-control"></textarea>

			</div>
			  
<!-- 			<button id="save" class="btn btn-primary"  type="submit">Save </button> -->
			<div class="form-inline">
				<div class="col-md-3">
					<button type="button" class="btn btn-default form-control" id="backall" onclick="javascript:location.href='<%=request.getContextPath()%>/theme/listAllTheme.jsp'">回到所有主題</button>
				</div>
				<div class="col-md-3 col-sm-offset-6" >
					<button type="button" class="btn btn-success form-control" id="btnInsert">確認新增</button>
				</div>
			</div>
<!-- 			<div class="col-md-3"> -->
<!-- 				<button id="cancel" class="btn btn-danger form-control"  type="button">Cancel</button> -->
<!-- 			</div> -->
<!-- 		</form> -->
		<div>
			<div id="result"></div>
		</div>
		</form>

	</div>	
</div>

  
	<script type="text/javascript">

	//----------summernote---------------
		 $('#theme_article').summernote({
			 height:200,
			 lang: 'zh-TW', // default: 'en-US'
			 placeholder:"在此編輯內容"
			 });
	//----------End---------------------
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
				$.post("ThemeServlet" ,JSON.stringify(json)).done(function(data) {
					console.log(data);
					$.each(data, function(errAtt, errMsg) {
						console.log(errMsg);
						if (errMsg == "success") {
							document.addTheme.reset();
							errMsgSpan.remove();
							alert("新增成功 即將跳轉畫面");
							location.href="listAllTheme.jsp";
							
						}
// 						console.log("errMsg"+errMsg)
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
					var makrup = $('#theme_article').summernote('code');
// 					$('#theme_article').summernote('destroy');
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
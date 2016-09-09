<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>delete theme</title>
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
	<div>
		<div>
			<div>
				<label for="themeId">Search 編號Id</label> <input type="text"
				class="form-control" id="theme_id" name="themeid"
				placeholder="themeid" >
			</div>						
		</div>
		<div>
			<input type="button" id="btnDelete" value="delete">		
		</div>
		<div id="success"></div>
		
	</div>
	<script src="../js/jquery-3.1.0.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#btnDelete").click(function() {
				resetErrors();
				var theme_id = $("#theme_id").val();
				$.ajax({
					type : "delete",
					url:"ThemeServlet?" + $.param({"theme_id" : theme_id}),
					dataType : "json",
					success : function(data) {
						console.log(data.themeInt);
						console.log(data.themeId);
						console.log(data.themeSu);
						$.each(data, function(themeName, themeValue) {
							var v2;
// 		 						console.log(themeValue);
							if(data.themeInt=="編號只能為整數"&&data.themeId=="2無此編號"&&data.themeSu=="success"){
// 							    if(themeValue=="編號只能為整數"||themeValue=="無此編號"||themeValue=="success" ){
// 									console.log(themeValue)							    	
// 								    alert("查無編號!"); ||themeName=="themeId"||themeName=="themeSu"
								    var msg = '<label class="error" for="themeId">'+"編號只能為整數"+'</label>';
								    resetErrors();
									$('input[name="themeid"]').addClass('inputTxtError').after(msg);
									$("#theme_id").val("");
// 									}
							    } 
							if(data.themeInt==v2&&data.themeId=="無此編號"&&data.themeSu=="success"){
// 							    if(themeValue=="無此編號"||themeValue=="success" ){
// 							    	console.log(themeName);||themeName=="themeSu"
									var msg = '<label class="error" for="themeId">'+themeValue+'</label>';
									resetErrors();
									$('input[name="themeid"]').addClass('inputTxtError').after(msg);
									$("#theme_id").val("");
// 							    }
							    }
							     if(data.themeInt==v2&&data.themeId==v2&&data.themeSu=="success") {
// 								$("#success").text("success");
// 							    	if(themeValue=="success"){
// 									console.log(themeName);
									var msg = '<label class="error" for="themeId">'+themeValue+'</label>';
									resetErrors();
									$('input[name="themeid"]').addClass('inputTxtError').after(msg);
									$("#theme_id").val("");
// 									}
								}
						
					
						});
					
					}
				})
			})
			function resetErrors() {
			    $('form input, form select').removeClass('inputTxtError');
			    $('label.error').remove();
			}
			
		})
	</script>
	
</body>
</html>
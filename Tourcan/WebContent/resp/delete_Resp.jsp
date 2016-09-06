<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>delete Resp</title>
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
	<h2>Delete respID</h2>
		<div>
			<div>
				<label for="respId">Search 編號Id</label> <input type="text"
				class="form-control" id="resp_id" name="respid"
				placeholder="respid" >
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
				var resp_id = $("#resp_id").val();
				$.ajax({
					type : "delete",
					url:"RespServlet?" + $.param({"resp_id" : resp_id}),
					dataType : "json",
					success : function(data) {
						
						$.each(data, function(respName, themeValue) {
							var v2;
							console.log((data.respInt))
							console.log((data.respId))
							console.log(data.respSu)
// 							if(respName=="respInt"){
							    if(data.respInt=="編號只能為整數"&&data.respId=="無此編號"&&data.respSu=="success" ){
// 									console.log(themeValue)
// 								    alert("查無編號!"); ||themeName=="themeId"||themeName=="themeSu"
								    var msg = '<label class="error" for="respId">'+"編號只能為整數"+'</label>';
								    resetErrors();
									$('input[name="respid"]').addClass('inputTxtError').after(msg);
									$("#resp_id").val("");
									}
// 							    } 
								if(data.respInt==v2&&data.respId=="無此編號"&&data.respSu=="success"){
// 							    	console.log(respName.indexOf("respSu"))
// 							    if(themeValue=="無此編號" ){
// 							    	console.log(themeName);||themeName=="themeSu"||themeValue=="success"
									var msg = '<label class="error" for="respId">'+"無此編號"+'</label>';
									resetErrors();
									$('input[name="respid"]').addClass('inputTxtError').after(msg);
									$("#resp_id").val("");

// 							    }
							    }
 							    
							     if(data.respInt==v2&&data.respId==v2&&data.respSu=="success") {
// 								$("#success").text("success");
// 							    	if(themeValue=="success"&&themeValue!="無此編號"&&themeValue!="編號只能為整數"){
									var msg = '<label class="error" for="respId">'+"success"+'</label>';
									resetErrors();
									$('input[name="respid"]').addClass('inputTxtError').after(msg);
									$("#resp_id").val("");
// 								}
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
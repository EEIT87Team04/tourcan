<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>刪除一筆景點資料</title>
<script>
	function disp_confirm() {
		if (confirm("是否確認刪除?")) {
		} else {
			window.event.returnValue = false;
		}
	}
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
</head>
<body>
	<form name="deleteAtt">
		<table border="1">
			<tr>
				<td>請輸入景點ID：</td>
				<td><input type="text" name="attId" id="attId" size="45" /> 
				<div style="color: red; font-size: 70%" id="error"></div></td>
			</tr>
		</table>
		<input type="button" value="刪除" id="btnDelete"
			onclick="disp_confirm()"></input> <a href="../index.htm">回首頁</a>
			
			<div style="font-size: 150%" id="success"></div>
	</form>

	<div id="test"></div>

	<script type="text/javascript">
		$(function() {
			$("#btnDelete").click(function() {
				var attId = $("#attId").val();
				$.ajax({
					type : "post",
					url : "AttServlet",
					dataType : "json",
					data : {"attId" : attId},
					success : function(data) {
// 						alert(data.errMsg)
						if(data.errMsg!=null){
							$("#success").empty();
							$("#error").text(data.errMsg);
							$("#attId").val("");
						}else{
							$("#error").empty();
							$("#success").text("刪除成功");
							$("#attId").val("");
						}
					}
				})
			})
		})
	</script>
</body>
</html>
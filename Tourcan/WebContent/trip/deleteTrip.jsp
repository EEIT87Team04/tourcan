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
				<td>請輸入旅遊ID：</td>
				<td><input type="text" name="trip_Id" id="trip_Id" size="45" />
					<div style="color: red; font-size: 70%" id="error"></div></td>
			</tr>
		</table>
		<input type="button" value="刪除" id="btnDelete"
			onclick="disp_confirm()"></input>

		<div style="font-size: 150%" id="success"></div>
	</form>

	<div id="test"></div>

	<script src="../js/jquery-3.1.0.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#btnDelete").click(function() {
				var tripId = $("#trip_Id").val();
				$.ajax({
					type : "delete",
					url : "TripServlet?" + $.param({"trip_Id" : tripId}),
					dataType : "json",
					success : function(data) {
						console.log(data);

						$.each(data, function(errAtt, errMsg) {
							console.log(errMsg);
							console.log(errAtt);
							if (errMsg == "新增成功") {
								document.addAtt.reset();
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
					}
				})
			})
		})
	</script>
</body>
</html>
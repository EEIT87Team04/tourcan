<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增一筆行程資料</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link  rel="stylesheet">
<style type="text/css">
#mapPreview {
	height: 292px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
</head>
<body>
	<div>
		<form name="addTrip">
			<div >
				<div>
					<h1>新增行程</h1>
				</div>
			</div>
			<div>
				<div >
					<div >
						<div >
							<label for="tripName">行程名稱</label> <input type="text"
								id="trip_name" name="trip_name"
								placeholder="行程名稱">
						</div>
					</div>


					<div >
						<div >
							<label for="tripPrice">行程花費</label> <input type="number"
								 id="trip_price" name="trip_price"
								placeholder="行程" min=0>
						</div>
						<div >
							<label for="memUid">會員Id</label> <input type="text"
								 id="mem_uid" name="mem_uid"
								placeholder="會員Id" min=-1>
						</div>
					</div>

					<div >
						<div >
							<button type="button" class="btn btn-success form-control"
								id="btnInsert">Save</button>
						</div>
						<div >
							<button type="button" class="btn btn-danger form-control"
								id="btnReset">Reset</button>
						</div>
					</div>
				</div>
			</div>
			<div >
				<div  id="result"></div>
			</div>
		</form>
	</div>

	<script src="../js/bootstrap.min.js"></script>

	<script type="text/javascript">
	
	$(function() {
		$("#btnInsert").click(function() {
			var errMsgSpan = $('form[name="addTrip"] span');
			errMsgSpan.remove();
			var form = $(document.addTrip).serializeArray(), json = {};
			for (var i = 0; i < form.length; i++) {
				
				if (form[i].value.length > 0) {
					json[form[i].name] = form[i].value;
				}
			}
			$.post("TripServlet", JSON.stringify(json)).done(function(data) {
				console.log(data);
				$.each(data, function(errTrip, errMsg) {
					if (errMsg == "新增成功") {
						document.addTrip.reset();
						errMsgSpan.remove();
					}
					var errSpan = document.createElement("span");
					var errText = document.createTextNode(errMsg);
					var errId = 'err' + errTrip;
					errSpan.appendChild(errText);
					errSpan.setAttribute("style", "color:red");
					errSpan.setAttribute("id", errId);
					$('#' + errId).remove();
					$('#' + errTrip).after(errSpan);
				});
				console.log("200.");
			}).fail(function(xhr) {
				console.log("ERR.");
			});
		});
		$("#btnReset").click(function() {
			document.addTrip.reset();
			errMsgSpan.remove();
		});
	});
	
		</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增一筆行程項目</title>
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
		<form name="addTripitem">
			<div >
				<div>
					<h1>新增行程項目</h1>
				</div>
			</div>
			<div>
				<div >
					<div >
					</div>
					<div >
						<div >
							<label>行程Id</label> <input type="number"
								 id="trip_id" name="trip_id"
								placeholder="明細Id" min=-1>
						</div>
						<div>
							<label>明細序號</label> <input type="number"
								 id="tripitem_serial" name="tripitem_serial"
								placeholder="明細序號" min=-1>
						</div>
						<div>
							<label>景點Id</label> <input type="number"
								 id="att_id" name="att_id"
								placeholder="景點Id" min=-1>
						</div>
						<div>
							<label>旅館Id</label> <input type="number"
								 id="hotel_id" name="hotel_id"
								placeholder="旅館Id" min=-1>
						</div>
						<div>
							<label>逗留時間</label> <input type="number"
								 id="tripitem_staytime" name="tripitem_staytime"
								placeholder="逗留時間" min=0>
						</div>
						<div>
							<label>起始時間</label> <input type="time"
								 id="tripitem_begin" name="tripitem_begin"
								placeholder="起始時間">
						</div>
						<div>
							<label>交通資訊</label><p><textarea rows="10" 
							id="tripitem_traffic" name="tripitem_traffic" placeholder="test"></textarea>
						</div>
						<div>
							<label>註記</label><p><textarea rows="10" 
							id="tripitem_memo" name="tripitem_memo" placeholder="test"></textarea>
						</div>
					</div>

					<div>
						<div>
							<button type="button" class="btn btn-success form-control"
								id="btnInsert">Save</button>
						</div>
						<div>
							<button type="button" class="btn btn-danger form-control"
								id="btnReset">Reset</button>
						</div>
					</div>
				</div>
			</div>
			<div >
				<div id="result"></div>
			</div>
		</form>
	</div>

	<script src="../js/bootstrap.min.js"></script>

	<script type="text/javascript">
	$(function() {
		$("#btnInsert").click(function() {
			var errMsgSpan = $('form[name="addTripitem"] span');
			errMsgSpan.remove();
			var form = $(document.addTripitem).serializeArray(), json = {};
			for (var i = 0; i < form.length; i++) {
				if (form[i].value.length > 0) {
					json[form[i].name] = form[i].value;
				}
			}
			console.log("stringify="+JSON.stringify(json));
			console.log("json="+json);
			$.post("TripitemServlet", JSON.stringify(json)).done(function(data) {
				console.log(data);
				$.each(data, function(errTripitem, errMsg) {
					if (errMsg == "新增成功") {
						document.addTripitem.reset();
						errMsgSpan.remove();
					}
					var errSpan = document.createElement("span");
					var errText = document.createTextNode(errMsg);
					var errId = 'err' + errTripitem;
					errSpan.appendChild(errText);
					errSpan.setAttribute("style", "color:red");
					errSpan.setAttribute("id", errId);
					$('#' + errId).remove();
					$('#' + errTripitem).after(errSpan);
				});
				console.log("200.");
			}).fail(function(xhr) {
				console.log("ERR.");
			});
		});
		$("#btnReset").click(function() {
			document.addTripitem.reset();
			errMsgSpan.remove();
		});
	});
	
		</script>
</body>
</html>
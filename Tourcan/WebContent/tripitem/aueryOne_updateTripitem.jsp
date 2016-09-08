<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查詢一筆行程並更新</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
#mapPreview {
	height: 292px;
}

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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<form name="idCheckTripitem" id="idCheckTripitem">
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3">
					<h1>TripitemIdCheck</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-7">
					<div class="row">
						<div class="col-sm-9 form-group">
							<label for="tripitemId">編號Id</label> <input type="text"
								class="form-control" id="tripitem_id" name="tripitem_id"
								placeholder="tripitemId" >
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-7 form-group">
					<button type="button" class="btn btn-success form-control"
						id="btnIdCheck">IdCheck</button>
				</div>
				<div class="col-sm-5 form-group">
					<button type="button" class="btn btn-danger form-control"
						id="btnReset1">Reset</button>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3" id="idCheck"></div>
			</div>
		</form>
	<div>
		<form name="updateTripitem">
			<div>
				<h1>行程明細修改</h1>
			</div>
			<div>
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
							id="btnUpdate">Update</button>
					</div>
				<div>
					<button type="button" class="btn btn-danger form-control"
						id="btnReset2">Reset</button>
				</div>
				<div >
				<div  id="result"></div>
				</div>
			</div>
			</div>
		</form>
	</div>
	</div>
	
	<script src="../js/jquery-3.1.0.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>

	<script type="text/javascript">
	$(function(){
		$("#btnIdCheck").click(function() {
				var tripitemId = $("#tripitem_id").val(); 
				$.get(("TripitemServlet"),{"tripitemId":tripitemId,"method":"getOneById"},function(data){
					$.each(data,function(tripitemName,tripitemValue){
							$("#"+tripitemName).val(tripitemValue);
						if(tripitemName=="tripitem_id"){
							$("#errId").remove();
				 			$("#tripitem_id").prop("readonly", true);
						}else if(tripitemName=="checkResult"){
							var errSpan = document.createElement("span");
							var errText = document.createTextNode(tripitemValue);
							errSpan.appendChild(errText);
							errSpan.setAttribute("style", "color:red");
							errSpan.setAttribute("id", "errId");
							$("#errId").remove();
							$('#tripitem_id').after(errSpan);							
						}
					})
				})
			});
		$("#btnReset1").click(function() {
			document.idCheckTripitem.reset();
			document.updateTripitem.reset();
			$("#errId").remove();
			$('form[name="updateTripitem"] span').remove();
			$("#tripitem_id").prop("readonly", false);
		});
		
		
		
		$("#btnUpdate").click(function() {
			var tripitem_id = $("#tripitem_id").val();
			var errMsgSpan = $('form[name="updateTripitem"] span');
			errMsgSpan.remove();
			var form = $(document.updateTripitem).serializeArray(), json = {};
			console.log("form="+form);
			json["tripitem_id"]=+tripitem_id;
			for (var i = 0; i < form.length; i++) {
				if (form[i].value.length > 0) {
					json[form[i].name] = form[i].value;
				}
			}
			$.ajax({
				"type":"PUT",
				"url":"TripitemServlet?tripitem_id="+tripitem_id,
				"dataType":"json",
				"data":JSON.stringify(json),
				
				"success":function(data){
					$.each(data, function(errTripitem, errMsg) {
						if (errMsg == "更新成功") {
							document.updateTripitem.reset();
							errMsgSpan.remove();
							$("#result").empty();
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
				}
			})		
			$("#btnReset2").click(function() {
				document.updateTripitem.reset();
				$('form[name="updateTripitem"] span').remove();
				$("#errId").remove();
			});
		});
	})
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		<form name="idCheckTrip" id="idCheckTrip">
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3">
					<h1>TripIdCheck</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-7">
					<div class="row">
						<div class="col-sm-9 form-group">
							<label for="tripId">編號Id</label> <input type="text"
								class="form-control" id="trip_id" name="trip_id"
								placeholder="tripId" >
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
		<form name="updateTrip">
			<div>
				<h1>行程修改</h1>
			</div>
			<div>
				<div>
					<label for="tripName">行程名稱</label> <input type="text"
					id="trip_name" name="trip_name" placeholder="行程名稱">
				</div>
				<div>
					<div>
						<label for="tripPrice">行程花費</label> <input type="number"
						id="trip_price" name="trip_price" placeholder="行程" min=0>
					</div>
					<div>
					<label for="memUid">會員Id</label> <input type="text" id="mem_uid"
						name="mem_uid" placeholder="會員Id" min=-1 readonly>
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
				var tripId = $("#trip_id").val(); 
				$.get(("TripServlet"),{"tripId":tripId,"method":"getOneById"},function(data){
					$.each(data,function(tripName,tripValue){
							$("#"+tripName).val(tripValue);
						if(tripName=="trip_id"){
							$("#errId").remove();
				 			$("#trip_id").prop("readonly", true);
						}else if(tripName=="checkResult"){
							var errSpan = document.createElement("span");
							var errText = document.createTextNode(tripValue);
							errSpan.appendChild(errText);
							errSpan.setAttribute("style", "color:red");
							errSpan.setAttribute("id", "errId");
							$("#errId").remove();
							$('#trip_id').after(errSpan);							
						}
					})
				})
			});
		$("#btnReset1").click(function() {
			document.idCheckTrip.reset();
			document.updateTrip.reset();
			$("#errId").remove();
			$('form[name="updateTrip"] span').remove();
			$("#trip_id").prop("readonly", false);
		});
		
		
		
		$("#btnUpdate").click(function() {
			var trip_id = $("#trip_id").val();
			var errMsgSpan = $('form[name="updateTrip"] span');
			errMsgSpan.remove();
			var form = $(document.updateTrip).serializeArray(), json = {};
			console.log("form="+form);
			json["trip_id"]=trip_id;
			for (var i = 0; i < form.length; i++) {
				if (form[i].value.length > 0) {
					json[form[i].name] = form[i].value;
				}
			}
			$.ajax({
				"type":"PUT",
				"url":"TripServlet?trip_id="+trip_id,
				"dataType":"json",
				"data":JSON.stringify(json),
				
				"success":function(data){
					$.each(data, function(errTrip, errMsg) {
						if (errMsg == "更新成功") {
							document.updateTrip.reset();
							errMsgSpan.remove();
							$("#result").empty();
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
				}
			})		
			$("#btnReset2").click(function() {
				document.updateTrip.reset();
				$('form[name="updateTrip"] span').remove();
				$("#errId").remove();
			});
		});
	})
	</script>
</body>
</html>
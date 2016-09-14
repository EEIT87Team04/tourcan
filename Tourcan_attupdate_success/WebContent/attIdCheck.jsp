<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>景點資料新增</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
#mapPreview {
	height: 292px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
</head>
<body>
	<div class="container">
		<form name="idCheckAtt" >
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3">
					<h1>編號IdCheck</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-7">
					<div class="row">
						<div class="col-sm-9 form-group">
							<label for="attId">編號Id</label> <input type="text"
								class="form-control" id="att_id" name="attId" placeholder="attId">
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
						id="btnReset">Reset</button>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3" id="idCheck" ></div>
			</div>
		</form>
	</div>


	<div class="container">
		<form name="attUpdate">
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3">
					<h1>Title text</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-7">
					<div class="row">
						<div class="col-sm-9 form-group">
							<label for="attName">景點名稱</label> <input type="text"
								class="form-control" id="att_name" name="attName"
								placeholder="景點名稱">
						</div>
						<div class="col-sm-3 form-group">
							<label for="attEat">吃貨</label>
							<select class="form-control"
								id="att_eat" name="attEat">
								<option value="false">否</option>
								<option value="true">是</option>
							</select>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-4 form-group">
							<label>縣市</label> <select class="form-control" id="region_id"
								name="regionId">
								<option value="0">請選擇</option>
							</select>
						</div>
						<div class="col-sm-4 form-group">
							<label for="attLat">緯度</label> <input type="number"
								class="form-control" id="att_lat" name="attLat" placeholder="緯度"
								readonly>
						</div>
						<div class="col-sm-4 form-group">
							<label for="attLng">經度</label> <input type="number"
								class="form-control" id="att_lng" name="attLng" placeholder="經度"
								readonly>
						</div>
						<div class="col-sm-12 form-group">
							<label for="attAddr">街道地址</label> <input type="text"
								class="form-control" id="att_addr" name="attAddr"
								placeholder="地址">
						</div>
					</div>

					<div class="row">
						<div class="col-sm-4 form-group">
							<label for="attStaytime">逗留時長</label> <input type="number"
								class="form-control" id="att_staytime" name="attStaytime"
								placeholder="分鐘" min=0>
						</div>
						<div class="col-sm-4 form-group">
							<label for="attPrice">景點開銷</label> <input type="number"
								class="form-control" id="att_price" name="attPrice"
								placeholder="新台幣元" min=-1>
						</div>
						<div class="col-sm-4 form-group">
							<label for="attPhone">電話</label> <input type="text"
								class="form-control" id="att_phone" name="attPhone"
								placeholder="電話">
						</div>
					</div>

					<div class="row"></div>

					<div class="row">
						<div class="col-sm-12 form-group">
							<label>景點介紹</label>
							<textarea class="form-control" rows="8" id="att_intro"
								name="attIntro" placeholder="景點介紹"></textarea>
						</div>
					</div>
				</div>
				<div class="col-sm-5">
					<div class="row">
						<div class="col-sm-12" id="mapPreview"></div>
					</div>

					<div class="row">
						<div class="col-sm-12 form-group">
							<label for="電話">網址</label> <input type="text"
								class="form-control" id="att_url" name="attUrl" placeholder="網址">
						</div>
					</div>

					<div class="row">
						<div class="col-sm-12 form-group">
							<label>開放時間</label>
							<textarea class="form-control" rows="2" id="att_open"
								name="attOpen" placeholder="開放時間"></textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-7 form-group">
							<button type="button" class="btn btn-success form-control"
								id="btnUpdate">Update</button>
						</div>
						<div class="col-sm-5 form-group">
							<button type="button" class="btn btn-danger form-control"
								id="btnReset2">Reset</button>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3">
					<h1>foot text</h1>
				</div>
			</div>
		</form>
	</div>

	<script src="js/jquery-3.1.0.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	<script type="text/javascript">
		var coder, map, marker, checkTrigger, lastValue = "";

		function initMap() {
			var initPos = new google.maps.LatLng(25.042485, 121.543543);
			coder = new google.maps.Geocoder();
			map = new google.maps.Map(document.getElementById('mapPreview'), {
				center : initPos,
				zoom : 15,
				scrollwheel : false
			});
			marker = new google.maps.Marker({
				map : map,
				position : initPos,
				draggable : true
			});
			marker.addListener("position_changed", function() {
				document.getElementById("att_lat").value = marker.getPosition()
						.lat();
				document.getElementById("att_lng").value = marker.getPosition()
						.lng();
			});
			document.getElementById("att_name").addEventListener("input",
					queryMap);
			document.getElementById("att_addr").addEventListener("input",
					queryMap);
		}
		function queryMap() {
			if (lastValue != this.value) {
				lastValue = this.value;
				clearTimeout(checkTrigger);
				checkTrigger = setTimeout(function() {
					coder.geocode({
						address : lastValue
					}, function(r, s) {
						if (s === google.maps.GeocoderStatus.OK) {
							map.panTo(r[0].geometry.location);
							marker.setPosition(r[0].geometry.location);
						}
					});
				}, 300);
			}
		}

		
		$(function() {
			$("#btnIdCheck").click(function() {
				var att_id = $("#att_id").val();
				console.log(att_id);
				$.getJSON(("AttServlet"), {
					"att_id" : att_id
				}, function(data) {
					console.log(data);
					$.each(data, function(attName, attValue) {
						var form = $(document.attUpdate).serialize();
						
					    	$("#"+attName).val(attValue);
							if(attName=="att_eat")
							{
								if(attValue==false){
									$("#att_eat option:nth-child(2)").prop("selected",null);
									$("#att_eat option:nth-child(1)").prop("selected",true);}
									
								if(attValue==true){
									$("#att_eat option:nth-child(1)").prop("selected",null);
									$("#att_eat option:nth-child(2)").prop("selected",true);}
					        
					        
// 					        if(($("#"+attName).val(attValue))=="true"){
// 					        	console.log(attName);
// 					        $("#att_eat option:contains('是')").prop("selected",true);}
					        
// 					        if(($("#"+attName).val(attValue))=="false"){
// 					        	console.log(attName);
// 						        $("#att_eat option:contains('否')").prop("selected",true);}
					        
// 					        if($("#"+attName).val(attValue)=="true"){
// 					        	document.getElementById("true").selected = true;
// 					        } 
// 					        if($("#"+attName).val(attValue)=="false"){
// 					        	document.getElementById("false").selected = true;
// 					        }
// 								console.log(attValue);
					    }
					});
				});
		
				
			$("#btnReset").click(function() {
				document.idCheckAtt.reset();
				$('form[name="idCheckAtt"] span').remove();
			});
				
				
				
			$("#btnUpdate").click(function() {
				var att_id = $("#att_id").val();
				
				var form = $(document.attUpdate).serializeArray();
				var nameValue = $(document.attUpdate).serialize();
				json = {};
				json["attId"]=att_id;
				console.log("attId");
				console.log(att_id);
				console.log(form[0].name);
				console.log(form[0].value);
				for (var i = 0; i < form.length; i++)
					json[form[i].name] = form[i].value;
				

				console.log(json);
				console.log($(document.attUpdate).serializeArray());
				console.log(JSON.stringify(json));
				
				
				$.ajax({
					"type":"post",
					"url":"AttServlet",
					"dataType":"json",
					"data":JSON.stringify(json),
// 					"headers": {"X-HTTP-Method-Override": "put"}, 
					"success":function(data){
// 		 				console.log($(document.attUpdate).serialize());
						
// 				$.post("AttServlet", JSON.stringify(json)).done(function(data)
// 				{
// 					console.log("200.");
// 				})
// 				.fail(function(xhr) 
// 				{
// 					console.log("ERR.");
// 				});
					}
				});

			});
			
			
			
			
			$("#btnReset2").click(function() {
				document.attUpdate.reset();
				$('form[name="attUpdate"] span').remove();
			});
			
			
	});
			
		});		
			
	</script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBQ5sPydJ0xmpC9Evp8bWZu6O8LmJyuHw&callback=initMap"
		async defer></script>
</body>
</html>
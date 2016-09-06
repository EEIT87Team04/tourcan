<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>加入行程</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
#mapPreview {
	height: 292px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<form name="TripAtt" class="form-inline">
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3">
					<h1>行程規劃</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-7">
					<div class="row">
						<div class="col-sm-9 form-group">
							<label for="attName">出發地點：</label> 
							<input type="text" class="form-control" id="att_name" name="att_name" placeholder="地址或景點名稱">
							<button type="button" class="btn btn-danger form-control" id="btnReset">送出</button>	
						</div>
												<div class="col-sm-9 form-group">
							<label for="attName">出發時間：</label> 
							<input type="time" class="form-control" id="att_name" name="att_name" placeholder="地址或景點名稱">
							<button type="button" class="btn btn-danger form-control" id="btnReset">送出</button>	
						</div>
					</div>

					<div class="row">
						<div class="col-sm-4 form-group">
							<label>縣市</label> <select class="form-control" id="region_id"
								name="region_id">
								<option value="0">請選擇</option>
							</select>
						</div>
						<div class="col-sm-4 form-group">
							<label for="attLat">緯度</label> <input type="number"
								class="form-control" id="att_lat" name="att_lat"
								placeholder="緯度" readonly>
						</div>
						<div class="col-sm-4 form-group">
							<label for="attLng">經度</label> <input type="number"
								class="form-control" id="att_lng" name="att_lng"
								placeholder="經度" readonly>
						</div>
						<div class="col-sm-12 form-group">
							<label for="attAddr">街道地址</label> <input type="text"
								class="form-control" id="att_addr" name="att_addr"
								placeholder="地址">
						</div>
					</div>

					<div class="row">
						<div class="col-sm-4 form-group">
							<label for="attStaytime">逗留時長</label> <input type="number"
								class="form-control" id="att_staytime" name="att_staytime"
								placeholder="分鐘" min=0>
						</div>
						<div class="col-sm-4 form-group">
							<label for="attPrice">景點開銷</label> <input type="number"
								class="form-control" id="att_price" name="att_price"
								placeholder="新台幣元" min=-1>
						</div>
						<div class="col-sm-4 form-group">
							<label for="attPhone">電話</label> <input type="text"
								class="form-control" id="att_phone" name="att_phone"
								placeholder="電話">
						</div>
					</div>

					<div class="row"></div>

					<div class="row">
						<div class="col-sm-12 form-group">
							<label>景點介紹</label>
							<textarea class="form-control" rows="8" id="att_intro"
								name="att_intro" placeholder="景點介紹"></textarea>
						</div>
					</div>
				</div>
				<div class="col-sm-5">
					<div class="row">
						<div class="col-sm-12" id="mapPreview"></div>
					</div>

					<div class="row">
						<div class="col-sm-25 form-group">
							<label for="電話">網址</label> <input type="text"
								class="form-control" id="att_url" name="att_url"
								placeholder="網址">
						</div>
					</div>

					<div class="row">
						<div class="col-sm-12 form-group">
							<label>開放時間</label>
							<textarea class="form-control" rows="2" id="att_open"
								name="att_open" placeholder="開放時間"></textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-7 form-group">
							<button type="button" class="btn btn-success form-control"
								id="btnSave">Save</button>
						</div>
						<div class="col-sm-5 form-group">
							<button type="button" class="btn btn-danger form-control"
								id="btnReset">Reset</button>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3" id="result"></div>
			</div>
		</form>
	</div>


	<script src="../js/jquery-3.1.0.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>

	<script type="text/javascript">
		var coder, map, marker, checkTrigger, lastValue = "";
		var regionList;

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

	</script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBQ5sPydJ0xmpC9Evp8bWZu6O8LmJyuHw&callback=initMap"
		async defer></script>
</body>
</html>
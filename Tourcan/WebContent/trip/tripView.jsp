<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>加入行程</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">
#mapPreview {
	height: 400px;
	width: 500px;
	border: 2px solid #E0E0E0;
}

.div1 {
	background-color: #FFECEC;
	border: 2px solid #E0E0E0;
	border-radius: 20px;
	margin-bottom: 5px;
	padding-top: 3px;
}

.div2 {
	background-color: #F0F0F0;
	border: 2px solid #E0E0E0;
	border-radius: 20px;
	margin-bottom: 5px;
	padding-top: 3px;
	text-align: left;
	color: #9D9D9D;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<form name="TripAtt">
			<div class="row">
				<div class="col-sm-6 col-sm-offset-2">
					<h1>行程規劃</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-7 form-inline">
					<div class="row div1 col-sm-12">
						<div class="col-sm-10 form-group">
							<label>出發地點：</label> <input type="text" id="possition"
								placeholder="地址或景點名稱">
							<button type="button">送出</button>
						</div>
						<div class="col-sm-10 form-group">
							<label for="attName">出發時間：</label> <input type="time">
							<button type="button">送出</button>
						</div>
						<div class="col-sm-10 form-group">
							<label>交通方式：</label> <input type="radio" name="traffic"
								value="car">開車 <input type="radio" name="traffic"
								value="public">大眾運輸 <input type="radio" name="traffic"
								value="walk">步行
						</div>
					</div>
					<input type="button" class="row div2 col-sm-12" value="+新增行程...">
				</div>
				<div style = "display:none" class="row">
					<div class="col-sm-4 form-group">
						<label for="attLat">緯度</label> <input type="number"
							class="form-control" id="att_lat" name="att_lat" placeholder="緯度"
							readonly>
					</div>
					<div class="col-sm-4 form-group">
						<label for="attLng">經度</label> <input type="number"
							class="form-control" id="att_lng" name="att_lng" placeholder="經度"
							readonly>
					</div>
				</div>
				<div class="col-sm-5">
					<div class="row">
						<div class="col-sm-12" id="mapPreview"></div>
					</div>
					<div class="row" style="margin-top: 20px">
						<div class="col-sm-7 form-group">
							<button type="button" class="btn btn-success form-control">Save</button>
						</div>
						<div class="col-sm-5 form-group">
							<button type="button" class="btn btn-danger form-control">Reset</button>
						</div>
					</div>
				</div>
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
			document.getElementById("possition").addEventListener("input",
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
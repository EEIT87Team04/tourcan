<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>住宿資料維護</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
.mapPreview {
	height: 315px;
}

.panel-group {
	overflow: hidden;
	max-width: 600px;
}

.panel-heading {
	padding: 0px 15px;
}

.panel-collapse img {
	max-width: 100%;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-8 col-sm-offset-3">
				<div class="row">
					<div class="col-sm-12" data-toggle="collapse"
						data-target="#datalist">
						<h3>查詢資料</h3>
					</div>
				</div>
				<div class="row">
					<div id="datalist" class="panel-group collapse in">
						<div class="panel panel-info">
							<div class="panel-heading">
								<div class="row">
									<div class="col-sm-1">No.</div>
									<div class="col-sm-5">名稱</div>
									<div class="col-sm-1">Rank</div>
									<div class="col-sm-1">Price</div>
									<div class="col-sm-4">電話</div>
									<div class="col-sm-7">地址</div>
									<div class="col-sm-5">地址</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="editorModal" class="modal fade" data-backdrop="static"
			tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3 class="modal-title">編輯資料</h3>
					</div>
					<div class="modal-body">
						<div class="container-fluid">
							<form name="new_data">
								<div id="alertBox" class="row"></div>
								<div class="row">
									<div class="col-sm-2 form-group">
										<label for="hotel_id"></label><input type="text"
											class="form-control form-control-sm" id="hotel_id"
											name="hotel_id" readonly>
									</div>
									<div class="col-sm-7 form-group">
										<label for="hotel_name">旅店名稱</label> <input type="text"
											class="form-control form-control-sm" id="hotel_name"
											name="hotel_name" placeholder="旅店名稱">
									</div>
									<div class="col-sm-3 form-group">
										<label>星等</label> <select class="form-control form-control-sm"
											id="hotel_class" name="hotel_class">
											<option value="0">請選擇</option>
											<option value="7">7</option>
											<option value="6">6</option>
											<option value="5">5</option>
											<option value="4">4</option>
											<option value="3">3</option>
											<option value="2">2</option>
											<option value="1">1</option>
										</select>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-4 form-group">
										<label for="hotel_price">基本消費</label> <input type="number"
											class="form-control form-control-sm" id="hotel_price"
											name="hotel_price" placeholder="新台幣元" min=-1>
									</div>
									<div class="col-sm-4 form-group">
										<label for="hotel_lat">緯度</label> <input type="number"
											class="form-control form-control-sm" id="hotel_lat"
											name="hotel_lat" placeholder="緯度" readonly>
									</div>
									<div class="col-sm-4 form-group">
										<label for="hotel_lng">經度</label> <input type="number"
											class="form-control form-control-sm" id="hotel_lng"
											name="hotel_lng" placeholder="經度" readonly>
									</div>
								</div>
								<div class="row">
									<div class="panel-group" id="hotel_addr_panel">
										<div class="panel panel-default">
											<div class="panel-heading">
												<div class="row">
													<div class="col-sm-4 form-group">
														<label>縣市</label> <select
															class="form-control form-control-sm" id="region_id"
															name="region_id">
															<option value="0">請選擇</option>
														</select>
													</div>
													<div class="col-sm-8">
														<label for="hotel_addr">街道地址</label> <input type="text"
															class="form-control form-control-sm" id="hotel_addr"
															name="hotel_addr" placeholder="地址">
													</div>
												</div>
											</div>
											<div id="collapse1" class="panel-collapse collapse">
												<div class="mapPreview"></div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-5 form-group">
										<label for="hotel_phone">電話</label> <input type="text"
											class="form-control form-control-sm" id="hotel_phone"
											name="hotel_phone" placeholder="電話">
									</div>
									<div class="col-sm-7 form-group">
										<label for="hotel_url">網址</label> <input type="text"
											class="form-control form-control-sm" id="hotel_url"
											name="hotel_url" placeholder="網址">
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger pull-left"
							id="btnDelete">Delete</button>
						<button type="button" class="btn btn-success" id="btnSave">&nbsp;&nbsp;Save&nbsp;&nbsp;</button>
						<button type="button" class="btn btn-success" id="btnUpdate">Update</button>
						<button type="button" class="btn btn-secondary" id="btnReset">Cancel</button>
					</div>
				</div>
			</div>
		</div>


	</div>
	<script src="js/jquery-3.1.0.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	<script type="text/javascript">
		var coder, map, marker, checkTrigger, lastValue = "";
		var tmp, regionList, hotelList;
		var serviceProvider = "${contextPath}/hotels";
		//var serviceProvider = "HotelServlet";
		var gmbase = "https://maps.googleapis.com/maps/api/staticmap?key=AIzaSyCBQ5sPydJ0xmpC9Evp8bWZu6O8LmJyuHw&size=640x240&markers=color:blue%7C";

		function initMap() {
			coder = new google.maps.Geocoder();
			// initMap_post();
		}
		function initMap_post() {
			var initPos = new google.maps.LatLng(25.042485, 121.543543);
			map = new google.maps.Map(document.querySelector('.mapPreview'), {
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
				document.getElementById("hotel_lat").value = marker
						.getPosition().lat();
				document.getElementById("hotel_lng").value = marker
						.getPosition().lng();
			});
			document.getElementById("hotel_name").addEventListener("input",
					queryMap);
			document.getElementById("hotel_addr").addEventListener("input",
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
		function loadForm() {
			$.ajax(
					{
						url : serviceProvider + "/"
								+ $(this).attr("data-target").substr(6),
						method : "GET",
						contentType : "application/json; charset=UTF-8",
						dataType : "json"
					}).done(function(data) {
				document.new_data.hotel_id.value = data.hotel_id;
				document.new_data.hotel_name.value = data.hotel_name;
				document.new_data.region_id.value = data.region_id;
				document.new_data.hotel_addr.value = data.hotel_addr;
				document.new_data.hotel_phone.value = data.hotel_phone;
				document.new_data.hotel_url.value = data.hotel_url;
				document.new_data.hotel_class.value = data.hotel_class;
				document.new_data.hotel_price.value = data.hotel_price;
				document.new_data.hotel_lat.value = data.hotel_lat;
				document.new_data.hotel_lng.value = data.hotel_lng;
			});
			$(".alert").alert('close');
			$("#btnDelete").show();
			$("#btnSave").hide();
			$("#btnUpdate").show();
			$("#editorModal").modal('show');
		}
		function popAlert(title, data) {
			var $alt = $("<div>").addClass(
					"alert alert-danger alert-dismissible fade in").attr(
					"role", "alert").append(
					[
							$("<button>").addClass("close").attr("type",
									"button").attr("data-dismiss", "alert")
									.attr("aria-label", "Close").append(
											$("<span>").attr("aria-hidden",
													"true").text("×")),
							$("<h4>").addClass("alert-heading").text(title),
							$("<p>").text(data) ]);
			$("#alertBox").append($alt);
		}
		function makeItem() {
			var $pl = $("<div>").addClass("panel panel-default");
			var $plh = $("<div>").addClass("panel-heading");
			var $tr = $("<div>").addClass("row");
			var $td1 = $("<div>").text(this.hotel_id).addClass("col-sm-1");
			var $td2 = $("<div>").text(this.hotel_name).addClass("col-sm-5");
			var $td3 = $("<div>").text(this.hotel_class).addClass("col-sm-1");
			var $td4 = $("<div>").text(this.hotel_price).addClass("col-sm-2");
			var $td5 = $("<div>").text(this.hotel_phone).addClass("col-sm-3");
			var $td6 = $("<div>").text(
					regionList[this.region_id].region_name + this.hotel_addr)
					.addClass("col-sm-7");
			var $td7 = $("<div>").text(this.hotel_url).addClass("col-sm-5");
			$tr.append([ $td1, $td2, $td3, $td4, $td5, $td6, $td7 ]);
			$plh.append($tr);
			$plh.attr("data-toggle", "collapse").attr("data-parent",
					"#datalist").attr("data-target", "#hotel" + this.hotel_id);
			$plh.dblclick(loadForm);
			var $plc = $("<div>").attr("id", "hotel" + this.hotel_id).addClass(
					"panel-collapse collapse");
			var $img = $("<img>").attr("src",
					gmbase + this.hotel_lat + "," + this.hotel_lng);
			$plc.append($img);
			$pl.append($plh);
			$pl.append($plc);
			$("#datalist").append($pl);
		}
		function refreshView() {
			$.when($.ajax({
				url : "${contextPath}/att/RegionServlet",
				method : "GET",
				contentType : "application/json; charset=UTF-8",
				dataType : "json"
			}), $.ajax({
				url : serviceProvider,
				method : "GET",
				contentType : "application/json; charset=UTF-8",
				dataType : "json"
			})).done(
					function(d2, d1) {
						regionList = d2[0];
						hotelList = d1[0];
						$("#datalist .panel:not(:first-child)").remove()
						var frag = document.createDocumentFragment();
						for (var i = 1; i < regionList.length; i++)
							frag.appendChild(new Option(
									regionList[i].region_name,
									regionList[i].region_id));
						$("#region_id").append(frag);

						$.each(d1[0], makeItem);
					}).fail(function() {
			});
		}

		$(function() {

			refreshView();

			$("#hotel_addr_panel").mouseenter(function() {
				$("#collapse1").collapse("show");
				if (map == null) {
					initMap_post();
				}
			}).mouseleave(function() {
				$("#collapse1").collapse("hide");
			});

			$('#hotel_price').change(function() {
				if ($(this).val() % 1 != 0) {
					console.log('hotel_price is not a integer.');
				}
			});

			$("#btnSave").click(function() {
				var form = $(document.new_data).serializeArray(), json = {};
				for (var i = 0; i < form.length; i++)
					if (form[i].value.length > 0)
						json[form[i].name] = form[i].value;

				$.ajax({
					url : serviceProvider,
					method : "POST",
					data : JSON.stringify(json),
					contentType : "application/json; charset=UTF-8"
				}).done(function(data) {
					console.log("200: " + data);
					refreshView();
					$("#editorModal").modal('hide');
				}).fail(function(xhr) {
					console.log("ERR.");
					var errmsg = JSON.parse(xhr.responseText);
					popAlert("錯誤", errmsg.result);
				});
			});
			$("#btnUpdate").click(function() {
				var form = $(document.new_data).serializeArray(), json = {};
				for (var i = 0; i < form.length; i++)
					if (form[i].value.length > 0)
						json[form[i].name] = form[i].value;
				$.ajax({
					url : serviceProvider,
					method : "PUT",
					data : JSON.stringify(json),
					contentType : "application/json; charset=UTF-8"
				}).done(function(data) {
					console.log("200: " + data);
					refreshView();
					$("#editorModal").modal('hide');
				}).fail(function(xhr) {
					console.log("ERR.");
				});
			});
			$("#btnDelete").click(function() {
				var form = $(document.new_data).serializeArray(), json = {};
				for (var i = 0; i < form.length; i++)
					if (form[i].value.length > 0)
						json[form[i].name] = form[i].value;
				$.ajax({
					url : serviceProvider + "/" + json.hotel_id,
					method : "DELETE"
				//data : JSON.stringify(json),
				//contentType : "application/json; charset=UTF-8"
				}).done(function(data) {
					console.log("200: " + data);
					refreshView();
					$("#editorModal").modal('hide');
				}).fail(function(xhr) {
					console.log("ERR.");
				});
			});
			$("#btnReset").click(function() {
				document.new_data.reset();
				$("#editorModal").modal('hide');
			});
			$("#datalist .panel:first-child").dblclick(function() {
				document.new_data.reset();
				$(".alert").alert('close');
				$("#btnDelete").hide();
				$("#btnSave").show();
				$("#btnUpdate").hide();
				$("#editorModal").modal('show');
			});
		});
	</script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBQ5sPydJ0xmpC9Evp8bWZu6O8LmJyuHw&callback=initMap"
		async defer></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/emsPart1.jsp" />
<div class="container">
	<form name="addHotel">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">
				<h1>新增飯店</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-7">
				<div class="row">
					<div class="col-sm-9 form-group">
						<label for="hotel_name">飯店名稱</label> <input type="text"
							class="form-control" id="hotel_name" name="hotel_name"
							placeholder="飯店名稱">
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
						<label for="hotel_lat">緯度</label> <input type="number"
							class="form-control" id="hotel_lat" name="hotel_lat"
							placeholder="緯度" readonly>
					</div>
					<div class="col-sm-4 form-group">
						<label for="hotel_lng">經度</label> <input type="number"
							class="form-control" id="hotel_lng" name="hotel_lng"
							placeholder="經度" readonly>
					</div>
					<div class="col-sm-12 form-group">
						<label for="hotel_addr">街道地址</label> <input type="text"
							class="form-control" id="hotel_addr" name="hotel_addr"
							placeholder="地址">
					</div>
				</div>

				<div class="row">
					<div class="col-sm-4 form-group">
						<label for="hotel_price">平均消費</label> <input type="number"
							class="form-control" id="hotel_price" name="hotel_price"
							placeholder="新台幣元" min=-1>
					</div>
					<div class="col-sm-4 form-group">
						<label for="hotel_phone">電話</label> <input type="text"
							class="form-control" id="hotel_phone" name="hotel_phone"
							placeholder="電話">
					</div>
					<div class="col-sm-4 form-group">
						<label for="hotel_class">星評</label> <select
							class="form-control form-control-sm" id="hotel_class"
							name="hotel_class">
							<option value="7">&#9733;7</option>
							<option value="6">&#9733;6</option>
							<option value="5">&#9733;5</option>
							<option value="4">&#9733;4</option>
							<option value="3">&#9733;3</option>
							<option value="2">&#9733;2</option>
							<option value="1">&#9733;1</option>
							<option value="0">&#9734;0</option>
						</select>
					</div>
				</div>

				<div class="row"></div>

			</div>
			<div class="col-sm-5">
				<div class="row">
					<div class="col-sm-12" id="mapPreview"></div>
				</div>

				<div class="row">
					<div class="col-sm-12 form-group">
						<label for="hotel_url">網址</label> <input type="text"
							class="form-control" id="hotel_url" name="hotel_url"
							placeholder="網址">
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
	<div id="photo" style="display: none;">
		<form name="photos">
			<input type="hidden" name="hotel_id" id="hotelId">
			<%-- 	<input type="hidden" name="uri" id="uri" value="${pageContext.request.requestURI}"> --%>
			<input type="file" name="imgs" style="display: inline;"> <input
				type="file" name="imgs" style="display: none;"> <input
				type="file" name="imgs" style="display: none;">
			<button id="photoSubmit" style="display: inline;">上傳檔案</button>
		</form>
	</div>
</div>

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
			document.getElementById("hotel_lat").value = marker.getPosition()
					.lat();
			document.getElementById("hotel_lng").value = marker.getPosition()
					.lng();
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

	var hotel_id;
	$(function() {
		//------------------page buttons------------------
		var pBtn = $("<a></a>").append(
				$("<strong></strong>").text("≡飯店列表").addClass("pageBtn")).attr(
				"href", "${contextPath}/adridores/hotel");
		$("#pageBtns").append(pBtn);
		//------------------page buttons------------------

		$.get("${contextPath}/att/RegionServlet").done(
				function(list) {
					regionList = list;
					var frag = document.createDocumentFragment();
					for (var i = 1; i < list.length; i++)
						frag.appendChild(new Option(list[i].region_name,
								list[i].region_id));
					$("#region_id").append(frag);
				}).fail(function(xhr) {
			console.log("Get region list unsuccessful.");
		});

		$('#hotel_price').change(function() {
			if ($(this).val() % 1 != 0) {
				console.log('hotelPrice is not a integer.');
			}
		});

		$("#btnSave").click(function() {
			var errMsgSpan = $('form[name="addHotel"] span');
			$('form[name="addHotel"] span').remove();
			var form = $(document.addHotel).serializeArray(), json = {};
			for (var i = 0; i < form.length; i++) {
				if (form[i].value.length > 0) {
					json[form[i].name] = form[i].value;
				}
			}
			json["regionVO"] = regionList[json.region_id];

			$.ajax({
				url : "${contextPath}/hotels",
				data : JSON.stringify(json),
				method : "POST",
				dataType : "json",
				contentType : "application/json",
			}).done(function(data) {
				$.each(data, function(errAtt, errMsg) {
					if (errMsg == "新增成功" || errMsg == "success") {
						errMsg = "新增成功";
						$('form[name="addHotel"] span').remove();
					}
					var errSpan = $("<span/>").text(errMsg);
					// 						var errText = document.createTextNode(errMsg);
					var errId = 'err' + errAtt;
					// 						errSpan.appendChild(errText);
					errSpan.attr("style", "color:red");
					errSpan.attr("id", errId);

					$('#' + errId).remove();
					$('#' + errAtt).after(errSpan);

					if (errAtt == "hotel_id") {
						// 							console.log(errMsg[0]);
						$("#hotelId").val(errMsg);
						$("#photo").css("display", "inherit");
					}
				});
				console.log("200.");

			}).fail(function(xhr) {
				console.log("ERR.");
			});
		});
		$("#btnReset").click(function() {
			document.addHotel.reset();
			$('form[name="addHotel"] span').remove();
		});

		$("#photoSubmit").click(function(event) {
			event.preventDefault();
			var formdata = new FormData();
			var file_count = 0;

			$(":file").each(function(d) {
				$(this.files).each(function() {
					formdata.append('file' + file_count++, this)
				})
			});
			formdata.append("hotel_id", $("#hotelId").val());

			$.ajax({
				type : 'POST',
				url : '${contextPath}/att/PhotoServlet',
				data : formdata,
				cache : false,
				contentType : false,
				processData : false
			}).done(function(d) {
				if (d.result[0] == "新增成功") {
					alert("圖片上傳成功");
					document.addHotel.reset();
					document.photos.reset();
					$('form[name="addHotel"] span').remove();
					$("#photo").css("display", "none");
				}
			}).fail(function(d) {
				console.log(d.result[0]);
			})
		});

		$(":file").change(function() {
			$(this).each(function() {
				console.log($(this.files).length);
				if ($(this.files).length > 0) {
					$(this).next("input").css("display", "inline");
				} else {
					$(this).next("input").css("display", "none");
				}
			});
		});

	});
</script>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBQ5sPydJ0xmpC9Evp8bWZu6O8LmJyuHw&callback=initMap"
	async defer></script>

<jsp:include page="/emsPart2.jsp" />
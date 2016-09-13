<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/emsPart1.jsp" />
<div class="container">
	<form name="addAtt">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">
				<h1>新增景點</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-7">
				<div class="row">
					<div class="col-sm-9 form-group">
						<label for="att_name">景點名稱</label> <input type="text"
							class="form-control" id="att_name" name="att_name"
							placeholder="景點名稱">
					</div>
					<div class="col-sm-3 form-group">
						<label for="att_eat">吃貨</label> <select class="form-control"
							id="att_eat" name="att_eat">
							<option value="false">否</option>
							<option value="true">是</option>
						</select>
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
						<label for="att_lat">緯度</label> <input type="number"
							class="form-control" id="att_lat" name="att_lat" placeholder="緯度"
							readonly>
					</div>
					<div class="col-sm-4 form-group">
						<label for="att_lng">經度</label> <input type="number"
							class="form-control" id="att_lng" name="att_lng" placeholder="經度"
							readonly>
					</div>
					<div class="col-sm-12 form-group">
						<label for="att_addr">街道地址</label> <input type="text"
							class="form-control" id="att_addr" name="att_addr"
							placeholder="地址">
					</div>
				</div>

				<div class="row">
					<div class="col-sm-4 form-group">
						<label for="att_staytime">逗留時長</label> <input type="number"
							class="form-control" id="att_staytime" name="att_staytime"
							placeholder="分鐘" min=0>
					</div>
					<div class="col-sm-4 form-group">
						<label for="att_price">景點開銷</label> <input type="number"
							class="form-control" id="att_price" name="att_price"
							placeholder="新台幣元" min=-1>
					</div>
					<div class="col-sm-4 form-group">
						<label for="att_phone">電話</label> <input type="text"
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
					<div class="col-sm-12 form-group">
						<label for="att_url">網址</label> <input type="text"
							class="form-control" id="att_url" name="att_url" placeholder="網址">
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
			<div class="col-sm-6 col-sm-offset-3" id="result">
			</div>
		</div>
	</form>
	<div id="photo" style="display: none">
	<form method="post" action="PhotoServlet" id="photoUpload" name="photoUpload" enctype="multipart/form-data">
	<input type="hidden" name="att_id" id="attId">
	<input type="hidden" name="uri" id="uri" value="${pageContext.request.requestURI}">
	<input type="file" name="imgs" id="imgs" style="display: inline;">
	<input type="button" id="photoSubmit" value="上傳檔案" style="display: inline;">
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

			var att_id;
		$(function() {
			$.get("RegionServlet").done(
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
			
			$('#att_price').change(function() {
				if ($(this).val() % 1 != 0) {
					console.log('attPrice is not a integer.');
				}
			});

			$("#btnSave").click(function() {
				var errMsgSpan = $('form[name="addAtt"] span');
				 $('form[name="addAtt"] span').remove();
				var form = $(document.addAtt).serializeArray(), json = {};
				for (var i = 0; i < form.length; i++) {
					if (form[i].value.length > 0) {
						json[form[i].name] = form[i].value;
					}
				}
				json["regionVO"] = regionList[json.region_id];

				$.post("AttServlet", JSON.stringify(json)).done(function(data) {
					$.each(data, function(errAtt, errMsg) {
						if (errMsg == "新增成功") {
							 $('form[name="addAtt"] span').remove();
						}
						var errSpan = $("<span/>").text(errMsg);
// 						var errText = document.createTextNode(errMsg);
						var errId = 'err' + errAtt;
// 						errSpan.appendChild(errText);
						errSpan.attr("style", "color:red");
						errSpan.attr("id", errId);
						
						$('#' + errId).remove();
						$('#' + errAtt).after(errSpan);
						
						if(errAtt=="att_id")
						{
// 							console.log(errMsg[0]);
							$("#attId").val(errMsg[0]);
							$("#photo").css("display","initial");
 						}
					});
					console.log("200.");
					
// 					$('#fileupload').fileupload({
// 						type:'post',						
// 						dataType: 'json',
// 						url:"PhotoServlet?att_id=" + att_id,
// 						limitMultiFileUploads:1,
// 						limitMultiFileUploadSize:5000000,
// 						replaceFileInput:false,
// 						singleFileUploads:true,
// 						add:function(e, data){
// 							$("#upload").off('click').one('click',function(){
// 								data.submit();
// // 								document.addAtt.reset();
// 							});
// 						}
// 					});
				}).fail(function(xhr) {
					console.log("ERR.");
				});
			});
			$("#btnReset").click(function() {
				document.addAtt.reset();
				 $('form[name="addAtt"] span').remove();
			});
			$("#photoSubmit").click(function(){
				$("#photoUpload").submit();
			});
		});
	</script>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBQ5sPydJ0xmpC9Evp8bWZu6O8LmJyuHw&callback=initMap"
	async defer></script>

<jsp:include page="/emsPart2.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/emsPart1.jsp"%>
<center>
	<input type='hidden' id='current_page' /> <input type='hidden'
		id='show_per_page' />
	<table id="attList" class="table table-hover tableList">
		<thead>
			<tr>
				<th>id</th>
				<th>景點名稱</th>
				<th>地址</th>
				<th>基本消費</th>
				<th>電話</th>
				<th>平均時間</th>
				<th>首頁</th>
				<th>修改</th>
				<th>刪除</th>
			</tr>
		</thead>
		<tbody id="dataList">
			<c:forEach items="${attVO}" var="item">
				<tr>
					<td>${item.att_id}</td>
					<td>${item.att_name}</td>
					<td>${item.regionVO.region_name}${item.att_addr}</td>
					<td>${item.att_price}</td>
					<td>${item.att_phone}</td>
					<td>${item.att_staytime}</td>
					<td><a href="${item.att_url}">${item.att_url}</a></td>
					<td><button class="upNdelBtns shadowBtn"
							data-aid="${item.att_id}" data-aact="upd">修改</button></td>
					<td><button class="upNdelBtns shadowBtn"
							data-aid="${item.att_id}" data-aact="del">刪除</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBQ5sPydJ0xmpC9Evp8bWZu6O8LmJyuHw&callback=initMap"
		async defer></script>
	<script>
		var region_list;
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
				$("#att_lat").val(marker.getPosition().lat());
				$("#att_lng").val(marker.getPosition().lng());
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
			//------------------page buttons------------------
			var pBtn = $("<a></a>").append($("<strong></strong>")
								   .text("＋新增景點").addClass("pageBtn"))
								   .attr("href","${contextPath}/att/insert.jsp");
			$("#pageBtns").append(pBtn);			
			//------------------page buttons------------------
			
			//↓↓↓↓↓↓↓↓↓prepare RegionVO↓↓↓↓↓↓↓↓↓
			$.ajax({
				url : "${contextPath}/att/RegionServlet",
				method : "GET",
				dataType : "json"
			}).done(function(l) {
				region_list = l;
			}).fail(function(xhr) {
				console.log("Get region list unsuccessful.");
			});

			//↓↓↓↓↓↓↓↓↓Button binding↓↓↓↓↓↓↓↓↓
			$('#dataList').on(
					'click',
					'.upNdelBtns[data-aact="upd"]',
					function(e) {
						var $node = $(this).parentsUntil('#dataList');
						e.stopPropagation();
						$.ajax(
								{
									type : 'GET',
									url : '${contextPath}/attractions/'
											+ $(this).attr('data-aid')
								}).done(function(d) {
							coder.geocode({
								location : {
									lat : d.att_lat,
									lng : d.att_lng
								}
							}, function(r, s) {
								if (s === google.maps.GeocoderStatus.OK) {
									marker.setPosition(r[0].geometry.location);
								}
							});
							$("#att_id").val(d.att_id);
							$("#att_eat").val(d.att_eat.toString());
							$("#att_name").val(d.att_name);
							$("#region_id").val(d.regionVO.region_id);
							$("#att_addr").val(d.att_addr);
							$("#att_lat").val(d.att_lat);
							$("#att_lng").val(d.att_lng);
							$("#att_price").val(d.att_price);
							$("#att_phone").val(d.att_phone);
							$("#att_staytime").val(d.att_staytime);
							$("#att_intro").val(d.att_intro);
							$("#att_open").val(d.att_open);
							$("#att_url").val(d.att_url);

							$('#updPop').modal('show');
						}).fail(function() {
							console.log();
							alert("擷取資料失敗，請再試一次。");
						});

					});
			$('#updPop').on('shown.bs.modal', function() {
				google.maps.event.trigger(map, 'resize');
				map.panTo(marker.position);
			})

			$('#dataList').on(
					'click',
					'.upNdelBtns[data-aact="del"]',
					function(e) {
						var $node = $(this).parentsUntil('#dataList');
						e.stopPropagation();
						$("#tgValue").val($(this).attr('data-aid'));
						$("#attName").siblings('h3').text(
								'刪除 ' + $node.find('td:eq(1)').text() + ' ?')
						$("#attName").html("");
						$('#delPop').modal('show')
					});

			//↓↓↓↓↓↓↓↓↓Popup Window↓↓↓↓↓↓↓↓↓
			$(".close,#cancelDel,#cancelUpd").click(function(e) {
				e.stopPropagation();
				$('#delPop').modal('hide');
				$('#updPop').modal('hide');
			});
			$(window).click(function(e) {
				e.stopPropagation();
				if (e.target.id == "delPop")
					$('#delPop').modal('hide');
				if (e.target.id == "updPop")
					$('#updPop').modal('hide');
			});

			//↓↓↓↓↓↓↓↓↓Del Window↓↓↓↓↓↓↓↓↓
			$("#commitDel").click(function() {
				console.log($("#tgValue").val());
				$.ajax({
					type : "delete",
					url : "${contextPath}/attractions/" + $("#tgValue").val()
				}).done(function() {
					console.log("succeed");
					window.location.reload();
				}).fail(function() {
					console.log("failed");
				});
				//
				//$('#delPop').modal('hide');
			});
			//↓↓↓↓↓↓↓↓↓Upd Window↓↓↓↓↓↓↓↓↓
			$("#btnUpdate").click(function() {
				//resetErrors();

				var form = $(document.attUpdate).serializeArray();
				var json = {};
				for (var i = 0; i < form.length; i++) {
					json[form[i].name] = form[i].value;
					if (form[i].name == "region_id" && form[i].value == "0") {
						//var msg = '<label class="error" for="regionId">無效的地區代號。</label>';
						//$('select[name="regionId"]').addClass('inputTxtError').after(msg);
					}
				}
				json["regionVO"] = region_list[json.region_id];

				$.ajax({
					"type" : "PUT",
					"url" : "${contextPath}/attractions/",
					"contentType" : "application/json",
					"data" : JSON.stringify(json)
				}).done(function(data) {
					//console.log("200.");
					//$('#updPop').modal('hide');
					window.location.reload();
				}).fail(function(xhr) {
					console.log({
						MSG : "ERR",
						status : xhr.status,
						statusTest : xhr.statusText,
						DATA : xhr.responseText
					});
					if (xhr.status == 400) {

					} else if (xhr.status == 500) {
						alert("無法處理您的請求，請稍候再試！");
					}
					//alert("修改失敗!");
					//var msg = '<label class="error" for="'+attName+'">'+attValue+'</label>';
					//var $f = $("input[name=" + attName + "], select[name=" + attName + "], textarea[name=" + attName + "]");
					//$f.addClass('inputTxtError').after(msg);
				});
			});
		})
	</script>
	<ul id='page_navigation' class="pagination pagination-lg"></ul>



	<!-- 	DELETE -->
	<div id="delPop" class="modal fade bs-example-modal-sm">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<span class="close">×</span>
					<h2>刪除景點</h2>
				</div>
				<div class="modal-body">
					<h3>刪除景點___?</h3>
					<input type="hidden" id="tgValue">
					<%--<label>email：</label>--%>
					<span id="attName"></span>
				</div>
				<div class="modal-footer">
					<button id="cancelDel" class="celNcmitBtns">取消</button>
					<button id="commitDel" class="celNcmitBtns">刪除</button>
				</div>
			</div>
		</div>
	</div>



	<!-- UPDATE -->
	<div id="updPop" class="modal fade bs-example-modal-lg">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<span class="close">×</span>
					<h2>更新</h2>
				</div>
				<div class="modal-body">
					<form name="attUpdate" id="attUpdate">
						<div class="row">
							<div class="col-sm-12 form-group hide">
								<label for="att_id" class="col-sm-3 control-label">id</label>
								<div class="col-sm-8 col-md-6">
									<input type="text" class="form-control form-control-sm"
										id="att_id" name="att_id" readonly>
								</div>
							</div>
							<div class="col-sm-12 form-group">
								<label for="att_name" class="col-sm-3 control-label">景點名稱</label>
								<div class="col-sm-8 col-md-6">
									<input type="text" class="form-control form-control-sm"
										id="att_name" name="att_name" placeholder="景點名稱"
										data-checked data-required data-max-length="20">
								</div>
								<div class="col-sm-8 col-md-6 col-sm-offset-3 hide">
									<span class="help-block"></span>
								</div>
								<div class="col-sm-8 col-md-2">
									<select class="form-control form-control-sm" id="att_eat"
										name="att_eat">
										<option value="true">供餐</option>
										<option value="false">不供餐</option>
									</select>
								</div>
							</div>

							<div class="col-sm-12 form-group">
								<label class="col-md-3 col-sm-3 control-label">地址</label>
								<div class="col-md-3 col-sm-8">
									<select class="form-control form-control-sm" id="region_id"
										name="region_id">
										<option value="0">請選擇</option>
										<c:forEach items="${regionVO}" var="item" begin="1">
											<option value="${item.region_id}">${item.region_name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-md-5 col-sm-8 col-md-offset-0 col-sm-offset-3">
									<input type="text" class="form-control" id="att_addr"
										name="att_addr" placeholder="地址">
								</div>
							</div>
							<div class="col-sm-12 form-group">
								<label class="col-sm-3 control-label">座標</label>
								<div class="col-sm-4 col-md-4">
									<input type="number" class="form-control" id="att_lat"
										name="att_lat" placeholder="緯度" readonly>
								</div>
								<div class="col-sm-4 col-md-4">
									<input type="number" class="form-control" id="att_lng"
										name="att_lng" placeholder="經度" readonly>
								</div>
							</div>
							<div class="col-sm-12 form-group">
								<label for="att_staytime" class="col-sm-3 control-label">逗留時間</label>
								<div class="col-sm-8 col-md-6">
									<input type="number" class="form-control" id="att_staytime"
										name="att_staytime" placeholder="逗留時間" min=-1>
								</div>
							</div>
							<div class="col-sm-12 form-group">
								<label for="att_price" class="col-sm-3 control-label">基本消費</label>
								<div class="col-sm-8 col-md-6">
									<input type="number" class="form-control" id="att_price"
										name="att_price" placeholder="新台幣元" min=-1>
								</div>
							</div>
							<div class="col-sm-12 form-group">
								<label for="att_phone" class="col-sm-3 control-label">電話</label>
								<div class="col-sm-8 col-md-6">
									<input type="text" class="form-control form-control-sm"
										id="att_phone" name="att_phone" placeholder="電話"
										data-checked data-max-length="50">
								</div>
							</div>
							<div class="col-sm-12 form-group">
								<label for="att_url" class="col-sm-3 control-label">首頁</label>
								<div class="col-sm-8 col-md-6">
									<input type="text" class="form-control form-control-sm"
										id="att_url" name="att_url" placeholder="首頁">
								</div>
							</div>
							<div class="col-sm-12 form-group">
								<label for="att_intro" class="col-sm-3 control-label">簡介</label>
								<div class="col-sm-8 col-md-6">
									<input type="text" class="form-control form-control-sm"
										id="att_intro" name="att_intro" placeholder="簡介">
								</div>
							</div>
							<div class="col-sm-12 form-group">
								<label for="att_open" class="col-sm-3 control-label">開放資訊</label>
								<div class="col-sm-8 col-md-6">
									<input type="text" class="form-control form-control-sm"
										id="att_open" name="att_open" placeholder="開放資訊">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12" id="mapPreview"></div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" id="btnUpdate"
						class="celNcmitBtns btn-success">更新</button>
					<button type="button" id="cancelUpd" class="celNcmitBtns">取消</button>
				</div>
			</div>
		</div>
	</div>
</center>
<%@ include file="/emsPart2.jsp"%>
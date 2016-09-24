<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/emsPart1.jsp" />
<center>
	<input type='hidden' id='current_page' /> <input type='hidden'
		id='show_per_page' />
	<table id="attList" class="table table-hover tableList">
		<thead>
			<tr>
				<th>編號</th>
				<th>地區</th>
				<th>地名</th>
				<th>網址</th>
				<th>更新</th>
				<th>刪除</th>
			</tr>
		</thead>
		<tbody id="dataList">

		</tbody>
	</table>
	<ul id='page_navigation' class="pagination pagination-lg"></ul>
	
	
	
<!-- 	DELETE -->
	<div id="delPop" class="modal">
		<div class="modal-content">
			<div class="modal-header">
				<span class="close">×</span>
				<h2>刪除此景點</h2>
			</div>
			<div class="modal-body">
				<h3>是否要刪除此景點?</h3>
				<input type="hidden" id="tgValue">
				<label>景點：</label><span id="attName"></span>
			</div>
			<div class="modal-footer">
				<button id="cancelDel" class="celNcmitBtns">取消</button>
				<button id="commitDel" class="celNcmitBtns">刪除</button>
			</div>
		</div>
	</div>
	
	
	
<!-- UPDATE -->
	<div id="updPop" class="modal">
		<div class="modal-content">
			<div class="modal-header">
				<span class="close">×</span>
				<h2>更新</h2>
			</div>
			<div class="modal-body">
				<form name="attUpdate" id="attUpdate">
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3">
				<input type="hidden" id="att_id" name="attId">
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
							<label for="attEat">吃貨</label> <select class="form-control"
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
								id="btnUpdate">更新</button>
						</div>
						<div class="col-sm-5 form-group">
							<button type="button" class="btn btn-danger form-control"
								id="btnReset2">清空</button>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3">
				</div>
			</div>
		</form>
			</div>
			<div class="modal-footer">
				<button id="cancelUpd" class="celNcmitBtns">取消</button>
			</div>
		</div>
	</div>
</center>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBQ5sPydJ0xmpC9Evp8bWZu6O8LmJyuHw&callback=initMap"
		async defer></script>
		<script type="text/javascript">
		var delPop = $("#delPop"),updPop = $("#updPop");
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

		
			function resetErrors() {
			    $('form input, form select').removeClass('inputTxtError');
			    $('label.error').remove();
			}
		
		$(function() {
			var pBtn = $("<a></a>").append($("<strong></strong>").text("+新增景點").addClass("pageBtn")).attr("href","${contextPath}/att/insert.jsp");
			$("#pageBtns").append(pBtn);
			
			
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

			$("#btnReset").click(function() {
				resetErrors();
			});
				
				
			$("#btnUpdate").click(function() {
				resetErrors();
				
				var attId = $("#att_id").val();
				
				var form = $(document.attUpdate).serializeArray();
				var nameValue = $(document.attUpdate).serialize();
				json = {};
				json["attId"]=+attId;
				for (var i = 0; i < form.length; i++){
					$("#att_id").prop("readonly", true);
					json[form[i].name] = form[i].value;
				
				if(form[i].name=="regionId"){
					if(form[i].value=="0"){
							var msg = '<label class="error" for="regionId">無效的地區代號。</label>';
						    $('select[name="regionId"]').addClass('inputTxtError').after(msg);
						 }
				}
				}
				
				$.ajax({
					"type":"PUT",
					"url":"AttServlet?att_id="+att_id,
					"dataType":"json",
					"data":JSON.stringify(json),
					
					"success":function(data){
						$.each(data,function(attName,attValue){
							
							if(attValue=="修改失敗"){
								alert("修改失敗!");
							}
							
							var msg = '<label class="error" for="'+attName+'">'+attValue+'</label>';
							$("input[name=" + attName + "], select[name=" + attName + "], textarea[name=" + attName + "]").addClass('inputTxtError').after(msg);
							
						});
							console.log(JSON.stringify(json));
                        }
					
				})		
				.done(function(data)
			    {
	 				console.log("200.");
	 				
	 				$.each(data,function(attName,attValue){
	 				if(attValue=="修改成功"){
	 				updPop.css("display", "none");
	 				window.location.reload();
	 				}
	 				});
	 				
			    })
				.fail(function(xhr) 
				{
    			   console.log("ERR.");
				});
			});		
			
			 
			$("#btnReset2").click(function() {
				document.attUpdate.reset();
				resetErrors();
			});
			
		//↓↓↓↓↓↓↓↓↓Popup Window↓↓↓↓↓↓↓↓↓
			$(".close,#cancelDel,#cancelUpd").click(function(e){
				e.stopPropagation();
				console.log($(this)[0].id);
				delPop.css("display","none");
				updPop.css("display","none");
			});
			$(window).click(function(e){
				e.stopPropagation();
				if(e.target.id=="delPop")
				{delPop.css("display","none");}
				if(e.target.id=="updPop")
				{updPop.css("display","none");}
			});	
		//↑↑↑↑↑↑↑↑↑↑↑Popup Window↑↑↑↑↑↑↑↑↑↑↑
			
		$.getJSON('AttServlet?method=getAll', function(data) {
			$.each(data, function(index, att) {
				var row = $("<tr/>");
				var id = $("<td/>").text(att.att_id);
				var regName = $("<td/>").text(att.regionVO.region_name);
				var name = $("<td/>").text(att.att_name);
				var url = $("<td/>").text(att.att_url);
				var upd = $("<button/>").addClass("upNdelBtns").addClass("shadowBtn").text("更新").attr("id","upd" + att.att_id).val(att.att_id);
				var del = $("<button/>").addClass("upNdelBtns").addClass("shadowBtn").text("刪除").attr("id","del" + att.att_id).val(att.att_id);
				var ubtn = $("<td/>").append(upd);
				var dbtn = $("<td/>").append(del);
				var table = $("#attList >tbody");
				
				table.append(row.append([id,regName,name,url,ubtn,dbtn]));
		//↓↓↓↓↓↓↓↓↓Popup Window↓↓↓↓↓↓↓↓↓
		//For delete function
		$("#del" + att.att_id).click(function(e){
				e.stopPropagation();
				$("#tgValue").val($(this).val());
				$("#attName").text(att.att_name);
				delPop.css("display","block");
			});	
		//active commition
			$("#commitDel").click(function(){
				console.log($("#tgValue").val());
				$.ajax({
					type : "delete",
					url:"AttServlet?" + $.param({"attId" : $("#tgValue").val()}),
				}).done(function(){
					console.log("succeed");
				}).fail(function(){
					console.log("failed");
				}); 	
				delPop.css("display","none");
				window.location.reload();
			});
		//For update function
			$("#upd" + att.att_id).click(function(e){
				e.stopPropagation();
				
				
					$.getJSON(("AttServlet"), {"att_id" : $(this).val(),"method":"getAttID"}, function(data) {
					resetErrors();
						console.log(data);
						$.each(data, function(attName, attValue) {
						    	console.log($("#"+attName).val(attValue));
						    	$("#"+attName).val(attValue);
						    	
								if(attName=="att_eat"){
									$("#att_eat option[value="+ attValue +"]").prop("selected",true);
					            }
								
								if(attName=="regionVO"){
									$.each(data.regionVO, function(regionVOName, regionVOValue) {
									    if(regionVOName=="region_id"){
									    $("#region_id option[value="+ regionVOValue +"]").prop("selected",true);
									    }
									});
								}
						});
		 				updPop.css("display","block");
// 						$("#updPop").modal("show");
						google.maps.event.trigger(map, 'resize');
						coder.geocode({
							location : {lat: data.att_lat, lng: data.att_lng}
						}, function(r, s) {
							if (s === google.maps.GeocoderStatus.OK) {
								map.panTo(r[0].geometry.location);
								marker.setPosition(r[0].geometry.location);
							}
						});
				   });

			});	
		//↑↑↑↑↑↑↑↑↑↑↑Popup Window↑↑↑↑↑↑↑↑↑↑↑
		});
			
			
		//----------Pagination-----------	
	    //how much items per page to show  
	    var show_per_page = 10;  
	    //getting the amount of elements inside content div  
	    var number_of_items = $('#attList tbody').children().length;
	    console.log(number_of_items);
	    //calculate the number of pages we are going to have  
	    var number_of_pages = Math.ceil(number_of_items/show_per_page);  
	  
	    //set the value of our hidden input fields  
	    $('#current_page').val(0);  
	    $('#show_per_page').val(show_per_page);  
	  
	    //now when we got all we need for the navigation let's make it '  
	  
	    /* 
	    what are we going to have in the navigation? 
	        - link to previous page 
	        - links to specific pages 
	        - link to next page 
	    */  
// 	    var navigation_html = '<li><a class="previous_link" href="javascript:previous();">Prev</a></li>';
        var navigation_html = "";
	    var current_link = 0;  
	    while(number_of_pages > current_link){  
	        navigation_html += '<li><a class="page_link" href="javascript:go_to_page(' + current_link +')" longdesc="' + current_link +'">'+ (current_link + 1) +'</a></li>';  
	        current_link++;  
	    }  
// 	    navigation_html += '<li><a class="next_link" href="javascript:next();">Next</a></li>';  
	  
	    $('#page_navigation').html(navigation_html);  
	  
	    //add active_page class to the first page link  
	    $('#page_navigation .page_link:first').addClass('active_page');  
	  
	    //hide all the elements inside content div  
	    $('#attList tbody').children().css('display', 'none');  
	  
	    //and show the first n (show_per_page) elements  
	    $('#attList tbody').children().slice(0, show_per_page).css('display', 'table-row');  
			
		});
	});
		
	function previous(){  
		  
	    new_page = parseInt($('#current_page').val()) - 1;  
	    //if there is an item before the current active link run the function  
	    if($('.active_page').prev('.page_link').length==true){  
	        go_to_page(new_page);  
	    }  
	  
	}  
	  
	function next(){  
	    new_page = parseInt($('#current_page').val()) + 1;  
	    //if there is an item after the current active link run the function  
	    if($('.active_page').next('.page_link').length==true){  
	        go_to_page(new_page);  
	    }  
	  
	}  
	function go_to_page(page_num){  
	    //get the number of items shown per page  
	    var show_per_page = parseInt($('#show_per_page').val());  
	  
	    //get the element number where to start the slice from  
	    start_from = page_num * show_per_page;  
	  
	    //get the element number where to end the slice  
	    end_on = start_from + show_per_page;  
	  
	    //hide all children elements of content div, get specific items and show them  
	    $('#attList tbody').children().css('display', 'none').slice(start_from, end_on).css('display', 'table-row');  
	  
	    /*get the page link that has longdesc attribute of the current page and add active_page class to it 
	    and remove that class from previously active page link*/  
	    $('.page_link[longdesc=' + page_num +']').addClass('active_page').siblings('.active_page').removeClass('active_page');  
	  
	    //update the current page input field  
	    $('#attList tbody').val(page_num);  
	}  
</script>
<jsp:include page="/emsPart2.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>加入行程</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<style type="text/css">
#mapPreview {
	height: 400px;
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

.div3 {
	background-color: #F0F0F0;
	border: 2px solid #E0E0E0;
	border-radius: 20px;
	margin-bottom: 5px;
	padding-top: 3px;
}

.div4 {
	background-color: #FFFFDF;
	border: 2px solid #E0E0E0;
	margin-bottom: 5px;
	padding-top: 3px;
	text-align:center;
	height: 400PX; 
	overflow:scroll;
}

.divAtt {
	background-color: #DFFFDF;
	border: 2px solid #E0E0E0;
	border-radius: 20px;
	margin: 3px;
	padding-top: 3px;
}

.div5 {
	background-color: #FFFFDF;
	border: 2px solid #E0E0E0;
	margin-bottom: 5px;
	padding-top: 3px;
/* 	text-align:center; */
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>

</head>
<body>
	<div class="container">

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
						<label for="attName">出發時間：</label> <input type="datetime-local">
						<button type="button">送出</button>
					</div>
					<div class="col-sm-10 form-group">
						<label>交通方式：</label> <input type="radio" name="traffic"
							value="car">開車 <input type="radio" name="traffic"
							value="public">大眾運輸 <input type="radio" name="traffic"
							value="walk">步行
					</div>
				</div>
				<input type="button" class="row div2 col-sm-12" id="addTripBtn"
					value="+新增行程..." style="display: block">
				<div class="row div3 col-sm-12" style="display: none" id="div3">
					<div class="col-sm-12 form-group">
						<label>選擇縣市：</label> 
						<select class="form-control" id="region_id" name="region_id">
							<option value="0">請選擇</option>
						</select> 
						<label>類型：</label> 
						<select class="form-control" id="tripType" name="tripType">
							<option value="0">請選擇</option>
							<option value="1">景點</option>
							<option value="2">住宿</option>
						</select> 
					    <button type="button" id="searchRegion">查詢</button>
					</div>
					<div class="col-sm-12 form-group">
						<label>景點查詢：</label>
						<input type="text" class="form-control" id="attname" name="attname" placeholder="直接查詢景點名稱">
						<button type="button" id="searchAtt">查詢</button>
					</div>
				</div>
				<div id="sortable">
					<div class="row div5 col-sm-12" id="div3">
						<div class="col-sm-12 form-group">
							<label>選擇縣市：</label> <select class="form-control" id="region_id"
								name="region_id">
								<option value="0">請選擇</option>
							</select> 
							<label>類型：</label> 
							<select class="form-control" id="tripType" name="tripType">
								<option value="0">請選擇</option>
								<option value="1">景點</option>
								<option value="2">住宿</option>
							</select> 
							<input type="text" class="form-control" id="attname" name="attname" placeholder="直接查詢景點名稱">
							<button type="button" id="search">查詢</button>
						</div>
					</div>
					<div class="row div5 col-sm-12" id="div3">
						<div class="col-sm-12 form-group">
							<label>選擇縣市：</label> <select class="form-control" id="region_id"
								name="region_id">
								<option value="0">請選擇</option>
							</select> <label>類型：</label> <select class="form-control" id="tripType"
								name="tripType">
								<option value="0">請選擇</option>
								<option value="1">景點</option>
								<option value="2">住宿</option>
							</select> <input type="text" class="form-control" id="attname"
								name="attname" placeholder="直接查詢景點名稱">
							<button type="button" id="search">查詢</button>
						</div>
					</div>
				</div>
			</div>
			<div style="display: none" class="row">
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
	</div>

	<div><a href=""></a></div>


	<div id="sortable">
		<div>Item 1</div>
		<div>Item 1</div>
		<div>Item 1</div>
		<div>Item 1</div>
		<div>Item 1</div>
		<div>Item 1</div>
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
		
		$(function(){
			
			//新增行程
			$('#addTripBtn').click(function(){
				$("#addTripBtn").css("display","none");
				$("#div3").css("display","block");
			});
			
			//加入縣市
			$.get("../att/RegionServlet").done(
					function(list) {
						regionList = list;
						var frag = document.createDocumentFragment();
						for (var i = 1; i < list.length; i++)
							frag.appendChild(new Option(list[i].region_name,list[i].region_id));
						$("#region_id").append(frag);
					}).fail(function(xhr) {
				console.log("Get region list unsuccessful.");
			});
			
			//列出所有選擇區域之景點類型form表單
			$("#searchRegion").click(function(){
				var regionId=$('#region_id').val();
				var tripType=$('#tripType').val();
				var nullSpan1=null;
				$("form").remove();
				$("#nullSpan1").remove();
				$("#nullSpan2").remove();
				console.log(regionId);
				console.log(tripType);
				//沒有選擇區域及類型之錯誤驗證
				if(regionId == 0 || tripType ==0){
					var nullSpan1=$("<span></span>").attr("style","color:red;font-size:70%").attr("id","nullSpan1").text("請選擇區域及類型")
					$("#searchRegion").after(nullSpan1);
				//選擇區域及景點類型之判斷
				}else if(regionId != 0 && tripType ==1){
					var attForm=document.createElement("form");
					attForm.setAttribute("class","row div4 col-sm-12");
					$.get(("../att/AttServlet"),{"regionId":regionId,"method":"getByRegionId"},function(data){
					console.log("data="+data);
					$.each(data,function(idx,att){
						console.log("idx="+idx);
						console.log("att="+att);
						console.log("test");
						var attDiv=document.createElement("div");
						attDiv.setAttribute("class","col-sm-12 divAtt form-group");
						var imgDiv=document.createElement("div");
						imgDiv.setAttribute("class","row col-sm-3");
						var urlA=document.createElement("a");
						urlA.setAttribute("href",att.att_url);
						urlA.setAttribute("target","_new");
						var attLabel =document.createElement("label");
						var attText = document.createTextNode(att.att_name);
						attLabel.appendChild(attText);
						urlA.appendChild(attLabel);
						imgDiv.appendChild(urlA);
						var openLabel=document.createElement("label");
						openLabel.setAttribute("class","row col-sm-3");
						var openText=document.createTextNode("開放時間:");
						openLabel.appendChild(openText);
						var contentH5=document.createElement("h5");
						contentH5.setAttribute("class","row col-sm-6");
						contentH5.setAttribute("style","word-break: break-all; margin-right:5px; text-align:left");
						var contentText=document.createTextNode(att.att_open);
						contentH5.appendChild(contentText);
						var butDiv=document.createElement("div");
						butDiv.setAttribute("class","row");
						var checkInput=document.createElement("input");
						checkInput.setAttribute("type","checkbox");
						checkInput.setAttribute("value",att.att_name);
						checkInput.setAttribute("name","attCheck");
// 						checkInput.setAttribute("style","");
						butDiv.appendChild(checkInput);
						attDiv.appendChild(imgDiv);
						attDiv.appendChild(openLabel);
						attDiv.appendChild(contentH5);
						attDiv.appendChild(butDiv);	
						attForm.appendChild(attDiv);
					})
					var sendBtn=document.createElement("input");
					sendBtn.setAttribute("type","button");
					sendBtn.setAttribute("id","sendBtn");
					sendBtn.setAttribute("value","加入選取行程");
					sendBtn.setAttribute("style","margin:5px");
					attForm.appendChild(sendBtn);
					$("#div3").after(attForm);
					
					//景點選單送出
				    $("#sendBtn").click(function(){
						$("#addTripBtn").css("display","block");
						$("#div3").css("display","none");

				    	var selected=[];
				        $("input[name='attCheck']:checked").each(function(){
				           selected.push($(this).val());
				       	});
				        alert("景點名稱 : " + selected.join());
						$("#div3 input").val("");
						$("#region_id option[value='0']").prop("selected",true);
						$("#tripType option[value='0']").prop("selected",true);
						$("form").remove();
				    });
				  })
				}
			});
			
			
			$("#searchAtt").click(function() {
				$("form").remove();
				$("#nullSpan1").remove();
				$("#nullSpan2").remove();
				//判斷attname是否輸入
				var attname = $("#attname").val();
				if(attname.trim().length==0){
					var nullSpan2=$("<span></span>").attr("style","color:red;font-size:70%").attr("id","nullSpan2").text("請輸入景點名稱")
					$("#searchAtt").after(nullSpan2);
				}else{
				//列出所有查詢之景點form表單
				var attForm=document.createElement("form");
				attForm.setAttribute("class","row div4 col-sm-12");
				$.get(("../att/AttServlet"),{"attname":attname,"method":"getByName"},function(data){
					console.log("data="+data);
					if(data<1){
						alert("1");
					}else{
					$.each(data,function(idx,att){
// 						console.log("idx="+idx);
// 						console.log("att="+att);
// 						console.log("test");
						var attDiv=document.createElement("div");
						attDiv.setAttribute("class","col-sm-12 divAtt form-group");
						var imgDiv=document.createElement("div");
						imgDiv.setAttribute("class","row col-sm-3");
						var urlA=document.createElement("a");
						urlA.setAttribute("href",att.att_url);
						urlA.setAttribute("target","_new");
						var attLabel =document.createElement("label");
						var attText = document.createTextNode(att.att_name);
						attLabel.appendChild(attText);
						urlA.appendChild(attLabel);
						imgDiv.appendChild(urlA);
						var openLabel=document.createElement("label");
						openLabel.setAttribute("class","row col-sm-3");
						var openText=document.createTextNode("開放時間:");
						openLabel.appendChild(openText);
						var contentH5=document.createElement("h5");
						contentH5.setAttribute("class","row col-sm-6");
						contentH5.setAttribute("style","word-break: break-all; margin-right:5px; text-align:left");
						var contentText=document.createTextNode(att.att_open);
						contentH5.appendChild(contentText);
						var butDiv=document.createElement("div");
						butDiv.setAttribute("class","row");
						var checkInput=document.createElement("input");
						checkInput.setAttribute("type","checkbox");
						checkInput.setAttribute("value",att.att_name);
						checkInput.setAttribute("name","attCheck");
// 						checkInput.setAttribute("style","");
						butDiv.appendChild(checkInput);
						attDiv.appendChild(imgDiv);
						attDiv.appendChild(openLabel);
						attDiv.appendChild(contentH5);
						attDiv.appendChild(butDiv);	
						attForm.appendChild(attDiv);
					})
					var sendBtn=document.createElement("input");
					sendBtn.setAttribute("type","button");
					sendBtn.setAttribute("id","sendBtn");
					sendBtn.setAttribute("value","送出");
					sendBtn.setAttribute("style","margin:5px");
					attForm.appendChild(sendBtn);
					$("#div3").after(attForm);
					
					//景點選單送出
				    $("#sendBtn").click(function(){
						$("#addTripBtn").css("display","block");
						$("#div3").css("display","none");

				    	var selected=[];
				        $("input[name='attCheck']:checked").each(function(){
				           selected.push($(this).val());
				       	});
				        alert("景點名稱 : " + selected.join());
						$("#div3 input").val("");
						$("#region_id option[value='0']").prop("selected",true);
						$("#tripType option[value='0']").prop("selected",true);
						$("form").remove();
				   });
					}
				})
			  }
		   });
	    });
			
	</script>
	<script >
		$(function(){
			$( "#sortable" ).sortable();
		  	$( "#sortable" ).disableSelection();
		})
	</script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBQ5sPydJ0xmpC9Evp8bWZu6O8LmJyuHw&callback=initMap"
		async defer></script>


</body>
</html>
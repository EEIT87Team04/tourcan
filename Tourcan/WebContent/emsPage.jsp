<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${contextPath}/css/emsBasic.css" />
<style type="text/css">
/* override emsBasic.css */
body {background: url(${contextPath}/images/emsBG.jpg) no-repeat center fixed;}
</style>
<link rel="stylesheet"
	href="//code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tourcan 後台管理系統</title>
</head>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<div class="listContainer" id="iBtnList">
		<div class="col-md-2 listBox">
			<div class="row" style="margin: 15px 15px">
				<button id="index" class="iconBtn shadowBtn">
					<img class="icon" src="${contextPath}/icons/EMS/list.png"><span
						class="liTxt">首頁選單</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="att" class="iconBtn shadowBtn">
					<img class="icon" src="${contextPath}/icons/EMS/location-mark.png"><span
						class="liTxt">景 點</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="hotel" class="iconBtn shadowBtn">
					<img class="icon" src="${contextPath}/icons/EMS/residential-block.png"><span
						class="liTxt">飯 店</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="eat" class="iconBtn shadowBtn">
					<img class="icon" src="${contextPath}/icons/EMS/restaurant.png"><span
						class="liTxt">美 食</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="trip" class="iconBtn shadowBtn">
					<img class="icon" src="${contextPath}/icons/EMS/trajectory.png"><span
						class="liTxt">規劃行程</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="article" class="iconBtn shadowBtn">
					<img class="icon" src="${contextPath}/icons/EMS/media.png"><span
						class="liTxt">管理心得</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="member" class="iconBtn shadowBtn">
					<img class="icon" src="${contextPath}/icons/EMS/social.png"><span
						class="liTxt">管理會員</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="service" class="iconBtn shadowBtn">
					<img class="icon" src="${contextPath}/icons/EMS/tourist-map.png"><span
						class="liTxt">客 服</span>
				</button>
			</div>
		</div>

		<div class="col-md-10">
			<div class="row" style="margin: 15px 15px">
				<div style="float: left;" id="pageBtns"></div>
				<span
					style="float: right; font-family: Arial; font-size: 16px; color: rgb(255, 255, 255);">
					<img src="${contextPath}/icons/EMS/suit-and-bow-tie.png"> Welcome, Admin
				</span>
			</div>
			<div class="row" style="margin: 15px 15px">
				<div class="contentBox"></div>
			</div>
			<div class="row" style="margin: 15px 15px">
				<br>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$(".iconBtn").each(function(){
				btns = $(this).attr("id");
				$("#"+ btns).click(function(){
// 					console.log($(this).attr("id"));
					$(location).attr("href", "${contextPath}"+$(this).attr("id")+"/list.jsp");
				});
			});
			
		});
	</script>
</body>
</html>
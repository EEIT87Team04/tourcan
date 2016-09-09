<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/Tourcan/css/emsBasic.css" />
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
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
		<div class="col-md-2"
			style="background-color: rgba(255, 255, 255, 0.5); height: 100%;">
			<div class="row" style="margin: 15px 15px">
				<button id="index" class="iconBtn shadowBtn">
					<img class="icon" src="/Tourcan/icons/EMS/list.png"><span
						class="liTxt">首頁選單</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="att" class="iconBtn shadowBtn">
					<img class="icon" src="/Tourcan/icons/EMS/location-mark.png"><span
						class="liTxt">景 點</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="hotel" class="iconBtn shadowBtn">
					<img class="icon" src="/Tourcan/icons/EMS/residential-block.png"><span
						class="liTxt">飯 店</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="eat" class="iconBtn shadowBtn">
					<img class="icon" src="/Tourcan/icons/EMS/restaurant.png"><span
						class="liTxt">美 食</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="trip" class="iconBtn shadowBtn">
					<img class="icon" src="/Tourcan/icons/EMS/trajectory.png"><span
						class="liTxt">規劃行程</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="article" class="iconBtn shadowBtn">
					<img class="icon" src="/Tourcan/icons/EMS/media.png"><span
						class="liTxt">管理心得</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="member" class="iconBtn shadowBtn">
					<img class="icon" src="/Tourcan/icons/EMS/social.png"><span
						class="liTxt">管理會員</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="service" class="iconBtn shadowBtn">
					<img class="icon" src="/Tourcan/icons/EMS/tourist-map.png"><span
						class="liTxt">客 服</span>
				</button>
			</div>
		</div>

		<div class="col-md-10">
			<div class="row" style="margin: 15px 15px">
				<div style="float: left;" id="pageBtns"></div>
				<span
					style="float: right; font-family: Arial; font-size: 16px; color: rgb(255, 255, 255);">
					<img src="/Tourcan/icons/EMS/suit-and-bow-tie.png"> Welcome, Admin
				</span>
			</div>
			<div class="row" style="margin: 15px 15px">
				<div class="contentBox">
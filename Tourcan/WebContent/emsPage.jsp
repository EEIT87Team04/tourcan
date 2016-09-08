<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tourcan 後台管理系統</title>
<style type="text/css">
html {
	height: 100%;
}

body {
	background: url("images/emsBG.jpg") no-repeat center fixed;
	background-size: cover;
	height: 100%;
}

.liTxt {
	font-family: '微軟正黑體';
	color: #ffffff;
	margin-left: 10px;
}

.icon {
	width: 28px;
	height: 28px;
}

.listContainer {
	margin: auto;
	width: 1600px;
	/* 	background-color: rgba(255,255,255,0.5); */
	border: thin 1px;
	height: 100%;
}

.iconBtn {
	background-color: rgba(255, 255, 255, 0);
	border-radius: 15px;
	width: 100%;
	border: none;
	color: white;
	padding: 15px 5px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 24px;
	margin: 10px 0px;
	cursor: pointer;
	-webkit-transition-duration: 0.4s;
	transition-duration: 0.4s;
}

.shadowBtn:hover {
	box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0
		rgba(0, 0, 0, 0.19);
}

.icld {
	margin: 5px;
	width:100%;
	height:725px;
	background-color: rgba(255, 255, 255, 0.3);
	border:dotted 3px;
	border-color: rgba(255, 255, 255, 0.5);
	border-radius: 10px;
}
</style>
</head>

<body>
	<div class="listContainer">
		<div class="col-md-2"
			style="background-color: rgba(255, 255, 255, 0.5); height: 100%"
			id="iBtnList">
			<div class="row" style="margin: 15px 15px">
				<button id="list" class="iconBtn shadowBtn">
					<img class="icon" src="icons/EMS/list.png"><span
						class="liTxt">首頁選單</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="att" class="iconBtn shadowBtn">
					<img class="icon" src="icons/EMS/location-mark.png"><span
						class="liTxt">景　　點</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="hotel" class="iconBtn shadowBtn">
					<img class="icon" src="icons/EMS/residential-block.png"><span
						class="liTxt">飯　　店</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="eat" class="iconBtn shadowBtn">
					<img class="icon" src="icons/EMS/restaurant.png"><span
						class="liTxt">美　　食</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="trip" class="iconBtn shadowBtn">
					<img class="icon" src="icons/EMS/trajectory.png"><span
						class="liTxt">規劃行程</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="article" class="iconBtn shadowBtn">
					<img class="icon" src="icons/EMS/media.png"><span
						class="liTxt">管理心得</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="member" class="iconBtn shadowBtn">
					<img class="icon" src="icons/EMS/social.png"><span
						class="liTxt">管理會員</span>
				</button>
			</div>
			<div class="row" style="margin: 15px 15px">
				<button id="service" class="iconBtn shadowBtn">
					<img class="icon" src="icons/EMS/tourist-map.png"><span
						class="liTxt">客　　服</span>
				</button>
			</div>
		</div>
		<div class="col-md-10">
			<div class="row" style="margin: 15px 15px">
				<br> <br>
			</div>
			<div class="row" style="margin: 15px 15px">
				<div class="icld">
				</div>
			</div>
			<div class="row" style="margin: 15px 15px">
				<br>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			var count = 0;
			var btns = new Array();
			$("#iBtnList :button").each(function(){
				btns[count] = $(this).attr("id");
// 				console.log(btns[count]);
				$("#"+btns[count]).click(function(){
					console.log($(this).attr("id"));
				});
				count++;
			});
			
// 			for(var i =0; i<btns.length;i++)
// 			{
// // 				console.log(btns[i]);
// 				$("#"+btns[i]).click(function(){
// 					console.log($(this).attr("id"));
// 				});
// 			}
			
		});
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.liTil{
	font-family: '微軟正黑體';
	color: #ffffff;
}
.icon{
	width:28px;
	height:28px;
}
</style>
</head>

<body>
	<div class="col-md-1" style="background-color: gray">
		<div class="row">
			<br>
			<br>
		</div>
		<div class="row">
			<button type="button" class="btn btn-default btn-lg"
				style="background-color: gray;" id="index">
				<img class="icon" src="icons/EMS/list.png" aria-hidden="true" /> <span class="liTil"> 首　　頁</span>
			</button>
		</div>
		<div class="row">
			<br>
			<br>
		</div>
		<div class="row">
			<button type="button" class="btn btn-default btn-lg"
				style="background-color: gray;" id="att">
				<img class="icon" src="icons/EMS/location-mark.png" aria-hidden="true" /><span class="liTil"> 景　　點</span>
			</button>
		</div>
		<div class="row">
			<br>
			<br>
		</div>
		<div class="row">
			<button type="button" class="btn btn-default btn-lg"
				style="background-color: gray;" id="hotel">
				<img class="icon" src="icons/EMS/residential-block.png" aria-hidden="true" /><span class="liTil"> 住　　宿</span>
			</button>
		</div>
		<div class="row">
			<br>
			<br>
		</div>
		<div class="row">
			<button type="button" class="btn btn-default btn-lg"
				style="background-color: gray;" id="eat">
				<img class="icon" src="icons/EMS/plate-with-fork-and-knife-cross.png"
					aria-hidden="true" /><span class="liTil"> 美　　食</span>
			</button>
		</div>
		<div class="row">
			<br>
			<br>
		</div>
		<div class="row">
			<button type="button" class="btn btn-default btn-lg"
				style="background-color: gray;" id="trip">
				<img class="icon" src="icons/EMS/trajectory.png" aria-hidden="true" /><span class="liTil"> 規劃行程</span>
			</button>
		</div>
		<div class="row">
			<br>
			<br>
		</div>
		<div class="row">
			<button type="button" class="btn btn-default btn-lg"
				style="background-color: gray;" id="article">
				<img class="icon" src="icons/EMS/newspaper-text.png" aria-hidden="true" /><span class="liTil"> 心　　得</span>
			</button>
		</div>
		<div class="row">
			<br>
			<br>
		</div>
		<div class="row">
			<button type="button" class="btn btn-default btn-lg"
				style="background-color: gray;" id="quest">
				<img class="icon" src="icons/EMS/postal.png" aria-hidden="true" /><span class="liTil"> 客　　服</span>
			</button>
		</div>
		<div class="row">
			<br>
			<br>
		</div>

	</div>
	<div class="col-md-11">
		<div id="content">
<!-- 		content code in here -->
		</div>
	</div>
	
	<script type="text/javascript">
	$(function(){
		$("#att").click(function(){
			$("#content").load('att/att.jsp',function(){
				console.log('att.loaded');
			});
			
		});
		
		$("#index").click(function(){
			$("#content").load('index.htm',function(){
				console.log('index.loaded');
			});
			
		});
		
	});
	</script>
</body>
</html>
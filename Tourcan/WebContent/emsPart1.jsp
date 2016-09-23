<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="${contextPath}/css/emsBasic.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tourcan 後台管理系統</title>
<style type="text/css">
body {
	background: url(${contextPath}/images/emsBG.jpg) no-repeat center fixed;
	background-size: cover;
	height: 100%;
	font-family: '微軟正黑體';
}
</style>
</head>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<div class="listContainer" id="iBtnList">
		<div class="col-md-2 listBox">
			<div class="row" style="margin: 15px 15px">
				<a id="index" class="iconBtn shadowBtn">
					<img class="icon" src="${contextPath}/icons/EMS/list.png"><span
						class="liTxt">首頁選單</span>
				</a>
			</div>
			<div class="row" style="margin: 15px 15px">
				<a id="att" class="iconBtn shadowBtn" href="${contextPath}/att/list.jsp">
					<img class="icon" src="${contextPath}/icons/EMS/location-mark.png"><span
						class="liTxt">景 點</span>
				</a>
			</div>
			<div class="row" style="margin: 15px 15px">
				<a id="hotel" class="iconBtn shadowBtn" href="${contextPath}/hotels">
					<img class="icon" src="${contextPath}/icons/EMS/residential-block.png"><span
						class="liTxt">飯 店</span>
				</a>
			</div>
			<div class="row" style="margin: 15px 15px">
				<a id="eat" class="iconBtn shadowBtn" href="${contextPath}/restaurants">
					<img class="icon" src="${contextPath}/icons/EMS/restaurant.png"><span
						class="liTxt">美 食</span>
				</a>
			</div>
			<div class="row" style="margin: 15px 15px">
				<a id="trip" class="iconBtn shadowBtn" href="${contextPath}/trips">
					<img class="icon" src="${contextPath}/icons/EMS/trajectory.png"><span
						class="liTxt">規劃行程</span>
				</a>
			</div>
			<div class="row" style="margin: 15px 15px">
				<a id="article" class="iconBtn shadowBtn" href="${contextPath}/articles/list_ems.jsp">
					<img class="icon" src="${contextPath}/icons/EMS/media.png"><span
						class="liTxt">管理心得</span>
				</a>
			</div>
			<div class="row" style="margin: 15px 15px">
				<a id="member" class="iconBtn shadowBtn" href="${contextPath}/mem">
					<img class="icon" src="${contextPath}/icons/EMS/social.png"><span
						class="liTxt">管理會員</span>
				</a>
			</div>
			<div class="row" style="margin: 15px 15px">
				<a id="service" class="iconBtn shadowBtn" href="${contextPath}/quest/replyGetAll.jsp">
					<img class="icon" src="${contextPath}/icons/EMS/tourist-map.png"><span
						class="liTxt">客 服</span>
				</a>
			</div>
		</div>

		<div class="col-md-10">
			<div class="row" style="margin: 15px 15px">
				<div style="float: left;" id="pageBtns"></div>
				<span
					style="float: right; font-family: Arial; font-size: 16px; color: rgb(255, 255, 255);">
					<img src="${contextPath}/icons/EMS/suit-and-bow-tie.png"> Welcome,
<!-- 					Admin vo EL CODING -->
				</span>
			</div>
			<div class="row" style="margin: 15px 15px">
				<div class="contentBox">
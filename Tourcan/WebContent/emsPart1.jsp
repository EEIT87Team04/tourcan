<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="${contextPath}/css/emsBasic.css" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<style type="text/css">
/* override emsBasic.css */
body {background: url(${contextPath}/images/emsBG.jpg) no-repeat center fixed;}
</style>
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
				<a id="index" class="iconBtn shadowBtn">
					<img class="icon" src="${contextPath}/icons/EMS/list.png"><span
						class="liTxt">首頁選單</span>
				</a>
			</div>
			<div class="row" style="margin: 15px 15px">
				<a id="att" class="iconBtn shadowBtn" href="${contextPath}/adridores/att">
					<img class="icon" src="${contextPath}/icons/EMS/location-mark.png"><span
						class="liTxt">景 點</span>
				</a>
			</div>
			<div class="row" style="margin: 15px 15px">
				<a id="hotel" class="iconBtn shadowBtn" href="${contextPath}/adridores/hotel">
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
				<a id="trip" class="iconBtn shadowBtn" href="${contextPath}/trip/listAllTrip.jsp">
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
				<a id="member" class="iconBtn shadowBtn" href="${contextPath}/adridores/mem">
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
				<span style="float: left;" id="pageBtns" ></span>
				<span
					style="float: right; font-family: Arial; font-size: 16px; color: rgb(255, 255, 255);">
					<img src="${contextPath}/icons/EMS/suit-and-bow-tie.png"> Welcome, <a href="${contextPath}/adridores/logout">${admin.admin_name}.</a>
<%-- 					<c:if test="${vo!=null}"> --%>
<%-- 						${vo} --%>
<%-- 					</c:if> --%>
				</span>
			</div>
			<div class="row" style="margin: 15px 15px">
				<div class="contentBox">
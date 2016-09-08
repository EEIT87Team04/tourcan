<%@page import="com.tourcan.att.model.AttService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active"><a href="#attPreview"
			aria-controls="attPreview" role="tab" data-toggle="tab">景點列表</a></li>
		<li role="presentation"><a href="#insertAtt"
			aria-controls="insertAtt" role="tab" data-toggle="tab">新增景點</a></li>
		<li role="presentation"><a href="#updateAtt"
			aria-controls="updateAtt" role="tab" data-toggle="tab">修改景點</a></li>
		<li role="presentation"><a href="#deleteAtt"
			aria-controls="deleteAtt" role="tab" data-toggle="tab">刪除景點</a></li>
		<li role="presentation"><a href="#searchAtt"
			aria-controls="searchAtt" role="tab" data-toggle="tab">查詢景點</a></li>
	</ul>

	<div class="tab-content">
		<div role="tabpanel" class="tab-pane active" id="attPreview">
			<div id="attList">
				<table id="tb1" class="table table-hover">
					<thead>
						<tr>
							<th>編號</th>
							<th>地點</th>
							<!-- 							<th>staytime</th> -->
							<th>地址</th>
							<!-- 							<th>price</th> -->
							<!-- 							<th>phone</th> -->
							<th>網址</th>
							<!-- 							<th>eat</th> -->
							<!-- 							<th>intro</th> -->
							<!-- 							<th>open</th> -->
							<!-- 							<th>att_lat</th> -->
							<!-- 							<th>att_lng</th> -->
							<th id="th1">地區</th>
							<th>更新</th>
							<th>刪除</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
		<div role="tabpanel" class="tab-pane" id="insertAtt"></div>
		<div role="tabpanel" class="tab-pane" id="updateAtt"></div>
		<div role="tabpanel" class="tab-pane" id="deleteAtt"></div>
		<div role="tabpanel" class="tab-pane" id="searchAtt"></div>
	</div>

	<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
	
	<div>
		<a href="#abc" data-rel="popup" data-position-to="window"
			class="ui-btn ui-corner-all ui-btn-inline ui-btn-a"
			data-transition="slidedown" aria-haspopup="true" aria-owns="abc"
			aria-expanded="false">Sign in</a>
	</div>
		<div data-role="popup"  id="abc" data-theme="a" class="ui-corner-all">
		<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">Close</a>
			Hey!<br>
			This is the testing page context.
		</div>
	<script type="text/javascript">
		$(function() {

			// 			Attractions list
			$.getJSON(("AttServlet"), function(data) {
				var myBody = $('#tb1>tbody');
				$.each(data,
						function(idx, att2) {

							var ubtn = $("<a></a>").text("更新")
										.attr("href","#"+att2.att_id)
										.attr("data-rel","popup")
										.attr("data-position-to","window")
										.attr("class","ui-btn ui-corner-all ui-btn-inline ui-btn-a")
										.attr("data-transition","slidedown")
										.attr("aria-haspopup",true)
										.attr("aria-owns",att2.att_id)
										.attr("aria-expanded",false);
// 							var form = $("<div></div>");

							var itd = $("<td></td>").text(att2.att_id);
							var ntd = $("<td></td>").text(att2.att_name);
							var addtd = $("<td></td>").text(att2.att_addr);
							var utd = $("<td></td>").text(att2.att_url);
							var rntd = $("<td></td>").text(
									att2.regionVO.region_name);
							var udbtn = $("<td></td>").append(ubtn);
							var row = $("<tr></tr>").append(
									[ itd, ntd, addtd, utd, rntd,
									  udbtn]);
							myBody.append(row);

						});
			});

			// 			Insert page
			$("#insertAtt").load('att/insert.html', function() {
				console.log("insert.loaded");
			});
			// 			Update page
			// 			$("#updateAtt").load('att/update.jsp', function() {
			// 				console.log("update.loaded");
			// 			});

		});
	</script>
</body>
</html>
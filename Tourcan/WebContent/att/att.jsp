<%@page import="com.tourcan.att.model.AttService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
				<table id="tb1" class="table table-striped table table-hover">
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

	<div>
		<input type="button" id="rdct" value="Redirect">
	</div>
	<script type="text/javascript">
		$(function() {

			// 			Attractions list
			$.getJSON(("AttServlet"), function(data) {
				var myBody = $('#tb1>tbody');
				$.each(data,
						function(idx, att2) {
							
							var ubtn = $("<button></button>").text("update");
							var dbtn = $("<button></button>").text("delete");
														
							var cell1 = $("<td></td>").text(att2.att_id);
							var cell2 = $("<td></td>").text(att2.att_name);
							// 							var cell3 = $("<td></td>").text(att2.att_staytime);
							var cell4 = $("<td></td>").text(att2.att_addr);
// 							var cell5 = $("<td></td>").text(att2.att_price);
							// 							var cell6 = $("<td></td>").text(att2.att_phone);
							var cell7 = $("<td></td>").text(att2.att_url);
							// 							var cell8 = $("<td></td>").text(att2.att_eat);
							// 							var cell9 = $("<td></td>").text(att2.att_intro);
							// 							var cell10 = $("<td></td>").text(att2.att_open);
							// 							var cell11 = $("<td></td>").text(att2.att_lat);
							// 							var cell12 = $("<td></td>").text(att2.att_lng);
							var cell13 = $("<td></td>").text(att2.regionVO.region_name);
							// 							var row = $("<tr></tr>").append(
							// 									[ cell1, cell2, cell3, cell4, cell5, cell6,
							// 											cell7, cell8, cell9, cell10,
							// 											cell11, cell12, cell13 ]);
							var cell14 = $("<td></td>").append(ubtn);
							var cell15 = $("<td></td>").append(dbtn);
							var row = $("<tr></tr>")
									.append(
											[ cell1, cell2, cell4, cell7, cell13, cell14, cell15 ]);
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
			
			$("#rdct").click(function() {
				$('#insertAtt').tab('show');

			});

		});
	</script>
</body>
</html>
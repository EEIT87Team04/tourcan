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
		<div role="tabpanel" class="tab-pane active" id="attPreview"></div>
		<div role="tabpanel" class="tab-pane" id="insertAtt"></div>
		<div role="tabpanel" class="tab-pane" id="updateAtt"></div>
		<div role="tabpanel" class="tab-pane" id="deleteAtt"></div>
		<div role="tabpanel" class="tab-pane" id="searchAtt"></div>
	</div>



	<script type="text/javascript">
		$(function() {
			
// 			$("#attPreview").load('', function() {
// 				console.log("list.loaded");
// 			});
			
			$("#insertAtt").load('att/insert.html', function() {
				console.log("insert.loaded");
			});

			
			
			
			
			
			
			
		});
	</script>



</body>
</html>
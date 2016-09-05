<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
<div class="col-md-2" style="background-color: silver" >


</div>
<div class="col-md-10">

  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#insertAtt" aria-controls="insertAtt" role="tab" data-toggle="tab">新增景點</a></li>
    <li role="presentation"><a href="#updateAtt" aria-controls="updateAtt" role="tab" data-toggle="tab">修改景點</a></li>
    <li role="presentation"><a href="#deleteAtt" aria-controls="deleteAtt" role="tab" data-toggle="tab">刪除景點</a></li>
    <li role="presentation"><a href="#searchAtt" aria-controls="searchAtt" role="tab" data-toggle="tab">查詢景點</a></li>
  </ul>

  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="insertAtt"></div>
    <div role="tabpanel" class="tab-pane" id="updateAtt"></div>
    <div role="tabpanel" class="tab-pane" id="deleteAtt"></div>
    <div role="tabpanel" class="tab-pane" id="searchAtt"></div>
  </div>

</div>

<script type="text/javascript">
$(function(){
	$("#insertAtt").load('att/attImport.html',function(){
		console.log("html.uploaded");
	});

	
	
});


</script>
</body>
</html>
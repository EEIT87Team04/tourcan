<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tourcan.att.model.*"%>
<%@ page import="com.tourcan.photo.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 此頁為script取值 ，應練習改用採用 EL 的寫法取值 --%>

<%
AttVO attVO = (AttVO) request.getAttribute("attVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
PhotoVO Photoj =(PhotoVO) request.getAttribute("Photoj"); 

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> OneAtt.jsp</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
</head>
<body >
<div class="container" style="padding: 10px">
	<div class="row" >
		
		<div class="col-md-6 col-md-offset-4">
			<h1>(景點名稱)</h1>
		</div>
	</div>
	<div  style="border: solid 1px" class="col-md-10">
<%-- 		<c:forEach var="attVO" items="${list}"> --%>
		<div style="border: solid 1px;height: 600px;" class="col-md-5">
		照片:照片放這
		</div>
		<div style="border: solid 1px" class="col-md-7">
		
			<div  style="border: solid 1px;height: 100px" class="row">
				<div class="col-md-7"> att_name</div>
				<div class="col-md-5">region_name</div>
			</div>
			<div  style="border: solid 1px;height: 100px" class="row">
				<div>att_attr</div>
			</div>
			<div  style="border: solid 1px;height: 100px" class="row">
				<div class="col-md-7"> att_price</div>
				<div class="col-md-5">att_phone</div>
			</div>
			<div  style="border: solid 1px;height: 100px" class="row">
				<div>att_open</div>
			</div>
			<div  style="border: solid 1px;height: 200px" class="row">
				<div>att_intro</div>
			</div>
		
		</div>				
<%-- 			</c:forEach> --%>
		
	
	</div>
<!--  <input type="button" value="回首頁" onclick="javascript:location.href='../index.jsp'"/> -->

<br>
	<div >

	</div>
</div>

 <script type="text/javascript">
 







 
 </script>
 
 
 
 
</body>
</html>

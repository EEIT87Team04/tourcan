<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tourcan.theme.model.*"%>
<%@ page import="com.tourcan.resp.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 此頁為script取值 ，應練習改用採用 EL 的寫法取值 --%>

<%
ThemeVO themeVO1 = (ThemeVO) request.getAttribute("themeVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
RespVO respVO1 =(RespVO) request.getAttribute("resVO"); 
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> listOneTheme.jsp</title>
 <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
</head>
<body bgcolor='white'>
<div class="container">

<table  class="table table-bordered">
		<thead>
			<tr align='center' valign='middle'>
				<th>id</th>				
				<th>topic</th>
				<th>memUID</th>
				<th>catalog</th>
				
			</tr>
		</thead>
		<tbody>
			<tr align='center' valign='middle'>
				<td><%=themeVO1.getTheme_id()%></td>
				<td><%=themeVO1.getTheme_topic()%></td>
				<td><%=themeVO1.getMem_uid()%></td>
				<td><%=themeVO1.getTheme_catalog()%></td>
		
			</tr>
	</tbody>
</table>

	<div id="div1">
	<!-- <textarea id="testa1"> -->
		<%=themeVO1.getTheme_article()%>	
	<!-- </textarea> -->
	
	</div>
<!--  <input type="button" value="回首頁" onclick="javascript:location.href='../index.jsp'"/> -->
<br>
<br>
<div >
<%--  <p>Theme_id: <%=respVO1.getTheme_id()%></p> --%>
<%--  <p>topic:  <%=respVO1.getResp_topic()%></p> --%>
<%--  <p>Mem_uid: <%=respVO1.getMem_uid()%></p> --%>
<%--  <p>respid: <%=respVO1.getResp_id()%></p> --%>
 <table class="table table-bordered">
 <c:forEach var="RespVO" items="${list}">
 <thead>
			<tr >
				<th>theme_id</th>				
				<th>resp_topic</th>
				<th>mem_uid</th>
				<th>resp_id</th>
				
			</tr>
		</thead>
	<tr>
		<td>${RespVO.theme_id}</td>
		<td>${RespVO.resp_topic}</td>
		<td>${RespVO.mem_uid}</td>
		<td>${RespVO.resp_id}</td>
 	</tr>	
			<tr>
				<th> resp_article</th>	
			</tr>
 	<tr>
 		<td id="d2" colspan="4">${RespVO.resp_article }</td>
 	</tr>
 </c:forEach>
 </table>
</div> 


 <script type="text/javascript">
	 $('#div1').summernote('code', themeValue);
 
// 	 $('#div1').summernote('code', themeValue);
 
 </script>
 
 
 
 
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tourcan.att.model.*"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%-- 此頁為script取值 ，應練習改用採用 EL 的寫法取值 --%>

<%
	AttVO attVO = (AttVO) request.getAttribute("attVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
	
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Att資料 - listOneAtt.jsp</title>
</head>
<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='600'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td><h3>ListOneAtt.jsp</h3> <!-- <a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td> -->
		</tr>
	</table>

	<table border='1' bordercolor='#CCCCFF' width='600'>

		<tr>
			<th>id</th>
			<th>name</th>
			<th>addr</th>
			<th>intro</th>
			<th>open</th>
			<th>phone</th>
			<th>url</th>
			<th>price</th>
			<th>staytime</th>
			<th>region_id</th>
		</tr>
		<tr align='center' valign='middle'>
		
<%-- 			<td><%=attVO.getAtt_id()%></td> --%>
<%-- 			<td><%=attVO.getAtt_name()%></td> --%>
<%-- 			<td><%=attVO.getAtt_addr()%></td> --%>
<%-- 			<td><%=attVO.getAtt_intro()%></td> --%>
<%-- 			<td><%=attVO.getAtt_open()%></td> --%>
<%-- 			<td><%=attVO.getAtt_phone()%></td> --%>
<%-- 			<td><%=attVO.getAtt_url()%></td> --%>
<%-- 			<td><%=attVO.getAtt_price()%></td> --%>
<%-- 			<td><%=attVO.getAtt_staytime()%></td> --%>
<%-- 			<td>${attVO.regionVO.region_id }</td> --%>
			
		</tr>
	</table>
	<table>
	<c:forEach var="attVO" items="${list}">
	<tr align='center' valign='middle'>
	<td>id:${attVO.att_id }</td>
	<td>name:${attVO.att_name }</td>
	<td>addr:${attVO.att_addr }</td>
	<td>intro:${attVO.att_intro }</td>
	<td>open:${attVO.att_open }</td>
	<td>phone:${attVO.att_phone }</td>
	<td>Region:${attVO.regionVO.region_id }</td>
	</tr>
</c:forEach>

</table>

	<input type="button" value="回首頁"
		onclick="javascript:location.href='../index.jsp'" />

</body>
</html>

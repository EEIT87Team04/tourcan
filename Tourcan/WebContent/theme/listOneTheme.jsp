<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tourcan.theme.model.*"%>
<%@ page import="com.tourcan.resp.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- ������script���� �A���m�ߧ�αĥ� EL ���g�k���� --%>

<%
ThemeVO themeVO1 = (ThemeVO) request.getAttribute("themeVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
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
 <input type="button" value="�^����" onclick="javascript:location.href='../index.jsp'"/>
<div >
 <p>respid:</p><p><%=respVO1.getResp_id()%></p>
 <p>topic:</p><p><%=respVO1.getResp_topic()%></p>
 <p><%=respVO1.getMem_uid()%></p>
 <p><%=respVO1.getTheme_id()%></p>
 
</div> 
 </div>


 <script type="text/javascript">
	 $('#div1').summernote('code', themeValue);
 
 
 
 </script>
 
 
 
 
</body>
</html>

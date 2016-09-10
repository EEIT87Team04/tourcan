<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tourcan.theme.model.*"%>
<%@ page import="java.util.*" %>
<%
	ThemeService tsv =new ThemeService();
	List<ThemeVO> list =tsv.getAll();
	pageContext.setAttribute("list",list);

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show all Theme and update</title>
</head>
<body bgcolor='white'>
<div class="container">
	<div class="row" >
		
		<div class="col-md-9">
			<h1>討論區主題列表</h1>
		</div>
		
		
		<div class="col-md-3 ">
			<button type="button" class="btn btn-info form-control" id="newtheme" onclick="javascript:location.href='<%=request.getContextPath()%>/theme/InsertThem.jsp'">發表新主題</button>
<%-- 		<p><%=request.getContextPath()%> --%> 
		</div>
		
	</div>

	<table  class="table table-hover">
		<thead>
			<tr >
				<th>id</th>				
				<th>topic</th>
				<th>memUID</th>
				<th>catalog</th>
<!-- 				<th>修改</th> -->
<!-- 				<th>刪除</th>	 -->
		
			</tr>
		</thead>
		<tbody>
			<c:forEach var="ThemeVO" items="${list}">
				<tr >
					<td >
						<FORM METHOD="post" ACTION="ThemeServlet">
						<input class="btn btn-primary" type="submit" value="${ThemeVO.theme_id}">
						<input type="hidden"name="theme_id" value="${ThemeVO.theme_id}">
						<input type="hidden"name="action" value="getOne_For_Display">				
						</FORM>
					 </td>
					 <td>
						<FORM METHOD="post" ACTION="ThemeServlet">
						<input class="btn btn-default" type="submit" value="${ThemeVO.theme_topic}">
						<input type="hidden"name="theme_id" value="${ThemeVO.theme_id}">
						<input type="hidden"name="action" value="getOne_For_Display">
					</FORM>
					</td>
					<td>${ThemeVO.mem_uid}</td>
					<td>${ThemeVO.theme_catalog}</td>
					
	<!-- 				<td> -->
	<!-- 				<FORM METHOD="post" ACTION="ThemeServlet"> -->
	<!-- 					<input type="submit" value="修改"> -->
	<%-- 					 <input type="hidden"name="theme_id" value="${ThemeVO.theme_id}"> --%>
	<!-- 					  <input type="hidden"name="action" value="getOne_For_Update"> -->
	<!-- 				</FORM>  -->
	<!-- 				</td> -->
		<!-- 			<td> -->
		<!-- 			<FORM METHOD="post" ACTION="mem.do" > -->
		<!-- 				 <input	type="submit" value="刪除"    onclick="toDelete()" /> -->
	<%-- 					 <input type="hidden" name="memno" value="${ThemeVO.theme_id}">  --%>
		<!-- 				  <input type="hidden"name="action" value="delete"> -->
		<!-- 				   <input type="hidden" onclick="toDelete()" > -->
		<!-- 			</FORM> -->
		<!-- 			</td> -->
					
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.tourcan.theme.model.*"%>

<%
	ThemeVO themeVO = (ThemeVO) request.getAttribute("themeVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
  <script type="text/javascript">
//   var theme_id=null;
//   $.getJSON(("ThemeServlet"),{"theme_id" :${themeVO.theme_id}},function(data) {
// 	$.each(data, function(themeName, themeValue) {
  		console.log(Content-Length)
// 	});
  
//   });
 
  </script>
</head>
<body>
<p>${themeVO.theme_id} </p>
</body>
</html>
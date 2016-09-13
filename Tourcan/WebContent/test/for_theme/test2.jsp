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
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
  <script src="../../lang/summernote-zh-TW.js"></script>
</head>
<body>
<p>123 </p>

<div id="summernote"></div>




<script type="text/javascript">
// $('#summernote').summernote('createLink', {
// 	  text: "asdffghhh",
// 	  url: 'http://summernote.org',
// 	  lang:'zh-TW',
// 	  newWindow: true
// 	});
$(document).ready(function() {
	  $('#summernote').summernote({
		  text:"abc",
	    lang: 'zh-TW' // default: 'en-US'
	  });
	});
// 	$('#summernote').summernote('unlink');





</script>

</body>
</html>
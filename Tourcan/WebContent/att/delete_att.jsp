<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>刪除一筆景點資料</title>
<script>
function disp_confirm()
  {
  if (confirm("是否確認刪除?")){
    }else{
    	window.event.returnValue = false;
    }
  }
</script>
</head>
<body>
<form action="AttServlet" method="">
<table border="1">
<tr>
<td>請輸入景點ID：</td>
<td><input type="text" name="att_id" size="45"></input>  
	<span style="color:red;font-size:70%">${errorMsgs}</span></td>
</tr>
</table>
<input type="submit" value="刪除" onclick="disp_confirm()"></input>
<input type="hidden" name="action" value="delete"></input>
<a href="../index.htm">回首頁</a>
</form>
</body>
</html>
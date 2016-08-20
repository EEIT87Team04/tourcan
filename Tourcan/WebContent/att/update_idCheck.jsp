<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="mem.do" method="POST">

<table border="1">

<tr>
<td>會員Id:</td>
<td><input type="TEXT" name="att_id" value="${param.att_id}" size="12" >
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.id}</div>

</tr>

</table>

<input type="submit">
<input type="hidden" name="action" value="update_idCheck">
</form>


</body>
</html>
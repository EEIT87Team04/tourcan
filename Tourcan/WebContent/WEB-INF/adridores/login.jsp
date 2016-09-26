<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理員登入</title>
</head>
<body>
	<form action="${contextPath}/adridores/login" method="post">
		<table>
			<tr>
				<td>帳號：</td>
				<td><input type="text" name="admin_account"></td>
			</tr>
			<tr>
				<td>密碼：</td>
				<td><input type="password" name="admin_pwd"></td>
			</tr>
			<tr>
				<td colspan="2"><button type="submit">登入</button></td>
			</tr>
		</table>
	</form>
</body>
</html>
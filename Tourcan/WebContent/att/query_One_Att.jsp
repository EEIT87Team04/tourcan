<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>select one</title>
</head>
<body bgcolor='white'>

	<p>This is the select one</p>

	<h3>資料查詢/刪除/修改:</h3>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>

	<ul>



		<li>
			<FORM METHOD="post" ACTION="AttServlet">
				<b>查詢:輸入att編號 (如7):</b>
				 <input type="text" name="attno"> 
				 <input	type="submit" value="查詢單筆">
				  <input type="hidden"name="action" value="getOne_For_Display">
			</FORM>
		</li>	
		
		<li>
			<FORM METHOD="post" ACTION="AttServlet">
				<b>查詢:輸入att姓名 (如taipei):</b>
				 <input type="text" name="attname"> 
				 <input	type="submit" value="查詢單筆">
				  <input type="hidden"name="action" value="getOne_For_Name">
			</FORM>
		</li>	
		 	
			

		



	</ul>
<input type="button" value="回首頁" onclick="javascript:location.href='../index.jsp'"/>


</body>

</html>

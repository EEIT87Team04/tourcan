<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tourcan.att.model.*"%>
<%-- <%@ page import="com.memImg.model.*"%> --%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    AttService asv =new AttService();
    List<AttVO> list = asv.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>�Ҧ���Att��� - query_All_att.jsp</title>
<script>
function toDelete() {
    var x;
    if (confirm("�O�_�T�{�R��!") == true) {
       
    } else {
    	window.event.returnValue = false;

    }
  
}
</script>



</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='800'>
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
	</tr>
	
	<c:forEach var="attVO" items="${list}">
		<tr align='center' valign='middle'>
		<td>${attVO.att_id }</td>
		<td>${attVO.att_name }</td>
		<td>${attVO.att_addr }</td>
		<td>${attVO.att_intro }</td>
		<td>${attVO.att_open }</td>
		<td>${attVO.att_phone }</td>
		<td>${attVO.att_url }</td>
		<td>${attVO.att_price }</td>
		<td>${attVO.att_staytime }</td>
		
	
		
<!-- 			<td> -->
<!-- 			<FORM METHOD="post" ACTION="mem.do"> -->
<!-- 				<input type="submit" value="�ק�"> -->
<%-- 				 <input type="hidden"name="mem_id" value="${MemVO.mem_id}"> --%>
<!-- 				  <input type="hidden"name="action" value="getOne_For_Update"> -->
<!-- 			</FORM>  -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 			<FORM METHOD="post" ACTION="mem.do" > -->
<!-- 				 <input	type="submit" value="�R��"    onclick="toDelete()" /> -->
<%-- 				 <input type="hidden" name="memno" value="${MemVO.mem_id}">  --%>
<!-- 				  <input type="hidden"name="action" value="delete"> -->
<!-- 				   <input type="hidden" onclick="toDelete()" > -->
<!-- 			</FORM> -->
<!-- 			</td> -->
			
			
			
			
		</tr>
	</c:forEach>
</table>
<input type="button" value="�^����" onclick="javascript:location.href='../index.jsp'"/>
</body>
</html>
<%-- <%=request.getContextPath()%> --%>
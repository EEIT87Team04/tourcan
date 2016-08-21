<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%-- <c:if test="${empty LoginOK}"> --%>
<%--    <c:set var="target" value="${pageContext.request.servletPath}" scope="session" /> --%>
<%--    <c:redirect url="/66mem/login.jsp" /> --%>
<%-- </c:if> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
${ErrorMsgKey.error}
<form action=mem.do method="POST">

<table border="1">

<tr>
<td>會員Id:</td>
<td><input type="TEXT" name="mem_id" value="${param.att_id}" size="12" readonly="readonly"></td>
</tr>

<tr>
<td>會員姓名:</td>
<td><input type="TEXT" name="mem_name" value="${param.att_name}${attVO.att_name}" size="12" >
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.name}</div>
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.nameReg}</div></td>

</tr>

<tr>
<td>att_staytime:</td>
<td><input type="TEXT" name="mem_bdate" value="${param.att_staytime}${attVO.att_staytime}" size="12" >
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.staytime}</div>
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.staytimeReg}</div></td>

</tr>

<tr>
<td>region_id:</td>
<td><input type="TEXT" name="mem_name" value="${param.region_id}${attVO.region_id}" size="12" >
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.regionId}</div>
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.regionIdReg}</div></td>

</tr>

<tr>
<td>att_addr:</td>
<td><input type="TEXT" name="mem_bdate" value="${param.att_addr}${attVO.att_addr}" size="12" >
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.addr}</div>


</tr>
<tr>
<td>att_price:</td>
<td><input type="TEXT" name="mem_name" value="${param.att_price}${attVO.att_price}" size="12" >
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.price}</div>
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.priceReg}</div></td>

</tr>

<tr>
<td>att_phone:</td>
<td><input type="TEXT" name="mem_bdate" value="${param.att_phone}${attVO.att_phone}" size="12" >
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.phone}</div>
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.phoneReg}</div></td>

</tr>
<tr>
<td>att_url:</td>
<td><input type="TEXT" name="mem_name" value="${param.att_url}${attVO.att_url}" size="12" >
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.url}</div>

</tr>

<tr>
<td>att_eat:</td>
<td><input type="TEXT" name="mem_bdate" value="${param.att_eat}${attVO.att_eat}" size="12" >
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.eat}</div>
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.eatReg}</div></td>

</tr>
<tr>
<td>att_intro:</td>
<td><input type="TEXT" name="mem_name" value="${param.att_intro}${attVO.att_intro}" size="12" >
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.intro}</div>

</tr>

<tr>
<td>att_open:</td>
<td><input type="TEXT" name="mem_bdate" value="${param.att_open}${attVO.att_open}" size="12" >
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.open}</div>

</tr>
<tr>
<td>att_lat:</td>
<td><input type="TEXT" name="mem_name" value="${param.att_lat}${attVO.att_lat}" size="12" >
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.lat}</div>
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.latReg}</div></td>

</tr>

<tr>
<td>att_lng:</td>
<td><input type="TEXT" name="mem_bdate" value="${param.att_lng}${attVO.att_lng}" size="12" >
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.lng}</div>
<div style="color:red; font-size:x-small display:inline">${ErrorMsgKey.lngReg}</div></td>

</tr>


</table>
<input type="hidden" name="action" value="update_reNew">
<input type="submit">
</form>

</body>
</html>
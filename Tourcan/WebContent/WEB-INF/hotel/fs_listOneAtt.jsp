<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<jsp:include page="/frontPageHeader.jsp" />
<div class="row">
	<div class="col-md-6 col-md-offset-4">
		<h1 style="display: inline;">${hotelVO.hotel_name}</h1>
		<h4 style="display: inline;">${hotelVO.regionVO.region_name}${hotelVO.hotel_addr}</h4>
	</div>
</div>
<div class="col-md-10 col-md-offset-1">
	<div class="row">
		<div class="col-md-5">
			<c:forEach items="${imgs}" var="img">
				<div class="col-md-12">
					<img src="${img}" class="imgSize" />
				</div>
			</c:forEach>
		</div>
		<div class="col-md-7">
			<div class="row">
				<div class="col-md-12 info">
					<h3>平均消費：</h3>${hotelVO.hotel_price}</div>
				<div class="col-md-12 info">
					<h3>聯絡電話：</h3>${hotelVO.hotel_phone}</div>
				<div class="col-md-12 info">
					<h3>網　　站：</h3>${hotelVO.hotel_url}</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/frontPageFooter.jsp" />

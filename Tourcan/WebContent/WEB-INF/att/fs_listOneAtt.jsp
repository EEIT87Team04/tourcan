<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<jsp:include page="/frontPageHeader.jsp" />
		<div class="row">
			<div class="col-md-6 col-md-offset-4">
				<h1>${attVO.att_name}</h1>
			</div>
		</div>
		<div style="border: solid 1px" class="col-md-10 col-md-offset-1">
			<div class="row">
				<div style="border: solid 1px" class="col-md-4 col-md-offset-1">
					<c:forEach items="${imgs}" var="img">
						<div class="col-md-12">
							<img src="${img}" width="150px" height="150px" />
						</div>
					</c:forEach>
				</div>
				<div style="border: solid 1px" class="col-md-7">
					<div style="border: solid 1px" class="row">
						<div class="col-md-7">${attVO.att_name}</div>
						<div class="col-md-5">${attVO.regionVO.region_name}</div>
						<div class="col-md-12">${attVO.att_addr}</div>
						<div class="col-md-7">${attVO.att_price}</div>
						<div class="col-md-5">${attVO.att_phone}</div>
						<div class="col-md-12">${attVO.att_open}</div>
						<div class="col-md-12">${attVO.att_intro}</div>
					</div>
				</div>
			</div>
		</div>
<jsp:include page="/frontPageFooter.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>會員列表</title>
<link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/css/ui-ExciteBike/jquery-ui.min.css"
	rel="stylesheet">
<link href="${contextPath}/css/jqGrid/ui.jqgrid-bootstrap.css"
	rel="stylesheet" media="screen" />
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-10 col-sm-offset-1">
				<table class="table table-hover table-bordered table-condensed">
					<thead>
						<tr>
							<th>Uid</th>
							<th>姓名</th>
							<th>暱稱</th>
							<th>email</th>
							<th>註冊日期</th>
							<th>居住地</th>
							<th>電話</th>
							<th>性別</th>
							<th>生日</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${members}" var="member">
							<tr>
								<td><a href="<c:url value="/mem/edituser?uid=${member.mem_uid}" />">${member.mem_uid}</a></td>
								<td>${member.mem_lname} ${member.mem_fname}</td>
								<td>${member.mem_nick}</td>
								<td>${member.mem_email}</td>
								<td><fmt:formatDate value="${member.mem_regtime}"
										type="date" pattern="yyyy-MM-dd" /></td>
								<td>${member.regionVO.region_id}</td>
								<td>${member.mem_mobile}</td>
								<td>${member.mem_sex}</td>
								<td><fmt:formatDate value="${member.mem_bdate}" type="date"
										pattern="yyyy-MM-dd" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</div>
	<script src="${contextPath}/js/jquery-3.1.0.min.js"></script>
	<script src="${contextPath}/js/ui-ExciteBike/jquery-ui-1.12.0.min.js"></script>
	<script src="${contextPath}/js/jqGrid/i18n/grid.locale-en.js"></script>
	<script src="${contextPath}/js/jqGrid/jquery.jqGrid.min.js"></script>
	<script src="${contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>
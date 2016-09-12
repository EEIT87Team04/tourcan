<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${data == null && vo != null}">
	<c:set var="data" value="${vo}" scope="page"></c:set>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯個人資料</title>
<link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/css/ui-ExciteBike/jquery-ui.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">
				<h3 class="">編輯個人資料</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<div class="row">
					<form name="new_data" class="form-horizontal" method="post"
						action="${contextPath}${listener}">
						<div id="alertBox" class="col-sm-12"></div>
						<div class="col-sm-12 form-group">
							<label for="mem_lname" class="col-sm-3 control-label">姓名</label>
							<div class="col-sm-8 col-md-6">
								<input type="text" class="form-control form-control-sm"
									id="mem_lname" name="mem_lname" placeholder="姓"
									value="${data.mem_lname}" data-checked data-required
									data-max-length="20">
							</div>
							<div class="col-sm-8 col-md-6 col-sm-offset-3">
								<span class="help-block">${msg.validation.mem_lname}</span>
							</div>
							<div class="col-sm-8 col-md-6 col-sm-offset-3">
								<input type="text" class="form-control form-control-sm"
									id="mem_fname" name="mem_fname" placeholder="名"
									value="${data.mem_fname}" data-checked data-required
									data-max-length="20">
							</div>
							<div class="col-sm-8 col-md-6 col-sm-offset-3">
								<span class="help-block">${msg.validation.mem_fname}</span>
							</div>
						</div>
						<div class="col-sm-12 form-group">
							<label for="mem_nick" class="col-sm-3 control-label">暱稱</label>
							<div class="col-sm-8 col-md-6">
								<input type="text" class="form-control form-control-sm"
									id="mem_nick" name="mem_nick" placeholder="暱稱"
									value="${data.mem_nick}" data-checked data-max-length="20">
							</div>
							<div class="col-sm-8 col-md-6 col-sm-offset-3">
								<span class="help-block">${msg.validation.mem_nick}</span>
							</div>
						</div>
						<div class="col-sm-12 form-group">
							<label class="col-sm-3 control-label">會員編號</label> <label
								class="col-sm-8 col-md-6 control-label">${data.mem_uid}</label>
							<div class="col-sm-8 col-md-6">
								<input type="text" class="hide form-control form-control-sm"
									id="mem_uid" name="mem_uid" value="${data.mem_uid}" readonly>
							</div>
						</div>
						<div class="col-sm-12 form-group">
							<label for="mem_email" class="col-sm-3 control-label">聯絡信箱</label>
							<div class="col-sm-8 col-md-6">
								<input type="text" class="form-control form-control-sm"
									id="mem_email" name="mem_email" value="${data.mem_email}"
									data-checked data-required data-max-length="50">
							</div>
							<div class="col-sm-8 col-md-6 col-sm-offset-3">
								<span class="help-block">${msg.validation.mem_email}</span>
							</div>
						</div>
						<div class="col-sm-12 form-group">
							<label for="mem_mobile" class="col-sm-3 control-label">手機</label>
							<div class="col-sm-8 col-md-6">
								<input type="text" class="form-control form-control-sm"
									id="mem_mobile" name="mem_mobile" placeholder="電話"
									value="${data.mem_mobile}" data-checked data-max-length="50">
							</div>
							<div class="col-sm-8 col-md-6 col-sm-offset-3">
								<span class="help-block">${msg.validation.mem_mobile}</span>
							</div>
						</div>

						<div class="col-sm-12 form-group">
							<label class="col-sm-3 control-label">性別</label>
							<div class="col-sm-8 col-md-6">
								<select class="form-control form-control-sm" id="mem_sex"
									name="mem_sex">
									<option value="0">不提供</option>
									<option value="1">男</option>
									<option value="2">女</option>
									<option value="9">其他</option>
								</select>
							</div>
							<div class="col-sm-8 col-md-6 col-sm-offset-3">
								<span class="help-block">${msg.validation.mem_sex}</span>
							</div>
						</div>

						<div class="col-sm-12 form-group">
							<label class="col-sm-3 control-label">現居地</label>
							<div class="col-sm-8 col-md-6">
								<select class="form-control form-control-sm" id="region_id"
									name="region_id">
									<option value="0">請選擇</option>
								</select>
							</div>
							<div class="col-sm-8 col-md-6 col-sm-offset-3">
								<span class="help-block">${msg.validation.region_id}</span>
							</div>
						</div>
						<div class="col-sm-12 form-group">
							<c:if test="${data.mem_bdate != null}">
								<fmt:formatDate value="${data.mem_bdate}" var="formattedBdate"
									type="date" pattern="yyyy-MM-dd" />
							</c:if>
							<label for="mem_bdate" class="col-sm-3 control-label">生日</label>
							<div class="col-sm-8 col-md-6">
								<input type="text"
									class="form-control form-control-sm datepicker" id="mem_bdate"
									name="mem_bdate" placeholder="生日" value="${formattedBdate}"
									data-checked data-required>
							</div>
							<div class="col-sm-8 col-md-6 col-sm-offset-3">
								<span class="help-block">${msg.validation.mem_bdate}</span>
							</div>
						</div>
						<div class="col-sm-12 form-group">
							<c:if test="${data.mem_regtime != null}">
								<fmt:formatDate value="${data.mem_regtime}"
									var="formattedRegtime" type="date" pattern="yyyy-MM-dd" />
							</c:if>
							<label for="mem_regtime" class="col-sm-3 control-label">註冊日期</label>
							<div class="col-sm-8 col-md-6">
								<input type="text"
									class="form-control form-control-sm datepicker"
									id="mem_regtime" name="mem_regtime" placeholder="註冊日期"
									value="${formattedRegtime}" data-checked data-required>
							</div>
							<div class="col-sm-8 col-md-6 col-sm-offset-3">
								<span class="help-block">${msg.validation.mem_regtime}</span>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">
				<button type="button" class="btn btn-success" id="btnSave">&nbsp;&nbsp;Save&nbsp;&nbsp;</button>
				<!-- <button type="button" class="btn btn-secondary" id="btnReset">Cancel</button> -->
			</div>
		</div>
	</div>
	<script src="${contextPath}/js/jquery-3.1.0.min.js"></script>
	<script src="${contextPath}/js/ui-ExciteBike/jquery-ui-1.12.0.min.js"></script>
	<script src="${contextPath}/js/bootstrap.min.js"></script>
	<script src="https://www.gstatic.com/firebasejs/3.3.0/firebase-app.js"></script>
	<script src="https://www.gstatic.com/firebasejs/3.3.0/firebase-auth.js"></script>
	<script type="text/javascript">
		$(function() {
			$.ajax({
				url : "${contextPath}" + "/RegionServlet",
				method : "GET",
				contentType : "application/json; charset=UTF-8",
				dataType : "json"
			}).done(function(l) {
				var f = document.createDocumentFragment();
				for (var i = 1; i < l.length; i++)
					f.appendChild(new Option(l[i].region_name, l[i].region_id));
				$("#region_id").append(f);
			}).fail(function(xhr) {
				console.log("Get region list unsuccessful.");
			}).then(function(){
				$("#region_id").val("${data.region_id}");
			});
			
			$(".datepicker").datepicker({
				dateFormat : "yy-mm-dd",
				//dayNamesMin : [ "日", "月", "火", "水", "木", "金", "土" ],
				dayNamesMin : [ "日", "一", "二", "三", "四", "五", "六" ],
				//monthNames: ["睦月", "如月", "彌生", "卯月", "皐月", "水無月", "文月", "葉月", "長月", "神無月", "霜月", "師走" ],
				//monthNames: ["端月", "花月", "梅月", "桐月", "蒲月", "伏月", "荔月", "桂月", "菊月", "陽月", "葭月", "臘月" ],
				monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
				monthNamesShort: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
				showMonthAfterYear: true,
				changeYear: true,
				changeMonth: true,
				firstDay : 1,
				onClose : function(){$(this).blur()}
			});
		
			$("input[data-checked]").blur(
					function(e) {
						if (this.hasAttribute("data-required")
								&& this.value.trim().length == 0) {
							$(this).parent().addClass("has-error");
							$(this).parent().next().find("span.help-block")
									.text("欄位不得為空");
						} else if (this.hasAttribute("data-max-length")
								&& this.value.trim().length > this
										.getAttribute("data-max-length")) {
							$(this).parent().addClass("has-error");
							$(this).parent().next().find("span.help-block")
									.text("輸入的字串太長");
						} else if (false) {

						} else if (false) {

						} else if (false) {

						} else {
							$(this).parent().removeClass("has-error");
							$(this).parent().next().find("span.help-block")
									.text("");
						}
					});
			$("#btnSave").click(function() {
				$(document.new_data).submit();
			});
			$("#mem_sex").val(${data.mem_sex});
			<%-- $("#region_id").val(${data.region_id}); --%>
			<%-- #region_id should generated in a more static way --%>
			<%-- otherwise above code may not work on slow network--%>
		})
	</script>
</body>
</html>
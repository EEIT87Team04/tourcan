<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/emsPart1.jsp"%>
<center>
	<input type='hidden' id='current_page' /> <input type='hidden'
		id='show_per_page' />
	<table id="memList" class="table table-hover tableList">
		<thead>
			<tr>
				<th>姓名</th>
				<th>暱稱</th>
				<th>uid</th>
				<th>e-mail</th>
				<th>地區</th>
				<th>註冊時間</th>
				<th>修改</th>
				<th>刪除</th>
			</tr>
		</thead>
		<tbody id="dataList">
			<c:forEach items="${memVO}" var="item">
				<tr>
					<td><span
						style="color: ${(item.mem_sex==1)?'blue':(item.mem_sex==2)?'red':'black'}">${item.mem_lname}${item.mem_fname}</span></td>
					<td>${item.mem_nick}</td>
					<td>${item.mem_uid}</td>
					<td><a href="mailto:${item.mem_email}">${item.mem_email}</a></td>
					<td>${item.regionVO.region_name}</td>
					<td><fmt:formatDate value="${item.mem_regtime}"
							pattern="yyyy-MM-dd" /></td>
					<td><button class="upNdelBtns shadowBtn"
							data-muid="${item.mem_uid}" data-mact="upd">修改</button></td>
					<td><button class="upNdelBtns shadowBtn"
							data-muid="${item.mem_uid}" data-mact="del">刪除</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script>
		var region_list;
		$(function() {
			//↓↓↓↓↓↓↓↓↓prepare RegionVO↓↓↓↓↓↓↓↓↓
			$.ajax({
				url : "${contextPath}/att/RegionServlet",
				method : "GET",
				dataType : "json"
			}).done(function(l) {
				region_list = l;
			}).fail(function(xhr) {
				console.log("Get region list unsuccessful.");
			});

			//↓↓↓↓↓↓↓↓↓Button binding↓↓↓↓↓↓↓↓↓
			$('#dataList').on(
					'click',
					'.upNdelBtns[data-mact="upd"]',
					function(e) {
						var $node = $(this).parentsUntil('#dataList');
						e.stopPropagation();
						$.ajax({type : 'GET',
								url : '${contextPath}/members/' + $(this).attr('data-muid')
						}).done(function(d) {
							$("#mem_lname").val(d.mem_lname);
							$("#mem_fname").val(d.mem_fname);
							$("#mem_nick").val(d.mem_nick);
							$("#mem_uid").val(d.mem_uid);
							$("#mem_email").val(d.mem_email);
							$("#mem_mobile").val(d.mem_mobile);
							$("#mem_sex").val(d.mem_sex);
							$("#region_id").val(d.regionVO.region_id);
							var mem_bdate = new Date(d.mem_bdate);
							mem_bdate = mem_bdate.toLocaleDateString();
							$("#mem_bdate").val(mem_bdate.replace(/\//g, "-"));
							var mem_reg = new Date(d.mem_regtime);
							mem_reg = mem_reg.toLocaleDateString();
							$("#mem_regtime").val(mem_reg.replace(/\//g, "-"));
						}).fail();
						$('#updPop').modal('show')
					});
			$('#dataList').on(
					'click',
					'.upNdelBtns[data-mact="del"]',
					function(e) {
						var $node = $(this).parentsUntil('#dataList');
						e.stopPropagation();
						$("#tgValue").val($(this).attr('data-muid'));
						$("#memName").siblings('h3').text(
								'刪除 ' + $node.find('td:eq(0)').text() + ' ?')
						$("#memName").html(
								$node.find('td:eq(3)').text() + '<br />'
										+ $node.find('td:eq(2)').text());
						$('#delPop').modal('show')
					});

			//↓↓↓↓↓↓↓↓↓Popup Window↓↓↓↓↓↓↓↓↓
			$(".close,#cancelDel,#cancelUpd").click(function(e) {
				e.stopPropagation();
				$('#delPop').modal('hide');
				$('#updPop').modal('hide');
			});
			$(window).click(function(e) {
				e.stopPropagation();
				if (e.target.id == "delPop")
					$('#delPop').modal('hide');
				if (e.target.id == "updPop")
					$('#updPop').modal('hide');
			});

			//↓↓↓↓↓↓↓↓↓Del Window↓↓↓↓↓↓↓↓↓
			$("#commitDel").click(function() {
				console.log($("#tgValue").val());
				$.ajax({
					type : "delete",
					url : "${contextPath}/members/" + $("#tgValue").val()
				}).done(function() {
					console.log("succeed");
					window.location.reload();
				}).fail(function() {
					console.log("failed");
				});
				//
				//$('#delPop').modal('hide');
			});
			//↓↓↓↓↓↓↓↓↓Upd Window↓↓↓↓↓↓↓↓↓
			$("#btnUpdate").click(function() {
				//resetErrors();

				var form = $(document.memUpdate).serializeArray();
				var json = {};
				for (var i = 0; i < form.length; i++) {
					json[form[i].name] = form[i].value;
					if (form[i].name == "region_id" && form[i].value == "0") {
						//var msg = '<label class="error" for="regionId">無效的地區代號。</label>';
						//$('select[name="regionId"]').addClass('inputTxtError').after(msg);
					}
				}
				json["regionVO"] = region_list[json.region_id];

				$.ajax({
					"type" : "PUT",
					"url" : "${contextPath}/members/",
					"contentType" : "application/json",
					"data" : JSON.stringify(json)
				}).done(function(data) {
					//console.log("200.");
					//$('#updPop').modal('hide');
					window.location.reload();
				}).fail(function(xhr) {
					console.log({
						MSG : "ERR",
						status : xhr.status,
						statusTest : xhr.statusText,
						DATA : xhr.responseText
					});
					if(xhr.status == 400){
						
					}else if(xhr.status == 500){
						alert("無法處理您的請求，請稍候再試！");
					}
					//alert("修改失敗!");
					//var msg = '<label class="error" for="'+attName+'">'+attValue+'</label>';
					//var $f = $("input[name=" + attName + "], select[name=" + attName + "], textarea[name=" + attName + "]");
					//$f.addClass('inputTxtError').after(msg);
				});
			});
		})
	</script>
	<ul id='page_navigation' class="pagination pagination-lg"></ul>



	<!-- 	DELETE -->
	<div id="delPop" class="modal fade bs-example-modal-sm">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<span class="close">×</span>
					<h2>刪除會員</h2>
				</div>
				<div class="modal-body">
					<h3>刪除會員___?</h3>
					<input type="hidden" id="tgValue">
					<%--<label>email：</label>--%>
					<span id="memName"></span>
				</div>
				<div class="modal-footer">
					<button id="cancelDel" class="celNcmitBtns">取消</button>
					<button id="commitDel" class="celNcmitBtns">刪除</button>
				</div>
			</div>
		</div>
	</div>



	<!-- UPDATE -->
	<div id="updPop" class="modal fade bs-example-modal-lg">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<span class="close">×</span>
					<h2>更新</h2>
				</div>
				<div class="modal-body">
					<form name="memUpdate" id="memUpdate">
						<div class="row">
							<div class="col-sm-12 form-group">
								<label for="mem_lname" class="col-sm-3 control-label">姓名</label>
								<div class="col-sm-8 col-md-6">
									<input type="text" class="form-control form-control-sm"
										id="mem_lname" name="mem_lname" placeholder="姓" data-checked
										data-required data-max-length="20">
								</div>
								<div class="col-sm-8 col-md-6 col-sm-offset-3">
									<span class="help-block"></span>
								</div>
								<div class="col-sm-8 col-md-6 col-sm-offset-3">
									<input type="text" class="form-control form-control-sm"
										id="mem_fname" name="mem_fname" placeholder="名" data-checked
										data-required data-max-length="20">
								</div>
								<div class="col-sm-8 col-md-6 col-sm-offset-3">
									<span class="help-block"></span>
								</div>
							</div>
							<div class="col-sm-12 form-group">
								<label for="mem_nick" class="col-sm-3 control-label">暱稱</label>
								<div class="col-sm-8 col-md-6">
									<input type="text" class="form-control form-control-sm"
										id="mem_nick" name="mem_nick" placeholder="暱稱" data-checked
										data-max-length="20">
								</div>
								<div class="col-sm-8 col-md-6 col-sm-offset-3">
									<span class="help-block"></span>
								</div>
							</div>
							<div class="col-sm-12 form-group">
								<label class="col-sm-3 control-label">會員編號</label>
								<div class="col-sm-8 col-md-6">
									<input type="text" class="form-control form-control-sm"
										id="mem_uid" name="mem_uid" readonly placeholder="會員編號">
								</div>
							</div>
							<div class="col-sm-12 form-group">
								<label for="mem_email" class="col-sm-3 control-label">聯絡信箱</label>
								<div class="col-sm-8 col-md-6">
									<input type="text" class="form-control form-control-sm"
										id="mem_email" name="mem_email" data-checked data-required
										data-max-length="50" placeholder="聯絡信箱">
								</div>
								<div class="col-sm-8 col-md-6 col-sm-offset-3">
									<span class="help-block"></span>
								</div>
							</div>
							<div class="col-sm-12 form-group">
								<label for="mem_mobile" class="col-sm-3 control-label">手機</label>
								<div class="col-sm-8 col-md-6">
									<input type="text" class="form-control form-control-sm"
										id="mem_mobile" name="mem_mobile" placeholder="電話"
										data-checked data-max-length="50">
								</div>
								<div class="col-sm-8 col-md-6 col-sm-offset-3">
									<span class="help-block"></span>
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
									<span class="help-block"></span>
								</div>
							</div>

							<div class="col-sm-12 form-group">
								<label class="col-sm-3 control-label">現居地</label>
								<div class="col-sm-8 col-md-6">
									<select class="form-control form-control-sm" id="region_id"
										name="region_id">
										<option value="0">請選擇</option>
										<c:forEach items="${regionVO}" var="item" begin="1">
											<option value="${item.region_id}">${item.region_name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-sm-8 col-md-6 col-sm-offset-3">
									<span class="help-block"></span>
								</div>
							</div>
							<div class="col-sm-12 form-group">
								<label for="mem_bdate" class="col-sm-3 control-label">生日</label>
								<div class="col-sm-8 col-md-6">
									<input type="text"
										class="form-control form-control-sm datepicker" id="mem_bdate"
										name="mem_bdate" placeholder="生日" data-checked data-required>
								</div>
								<div class="col-sm-8 col-md-6 col-sm-offset-3">
									<span class="help-block"></span>
								</div>
							</div>
							<div class="col-sm-12 form-group">
								<label for="mem_regtime" class="col-sm-3 control-label">註冊日期</label>
								<div class="col-sm-8 col-md-6">
									<input type="text"
										class="form-control form-control-sm datepicker"
										id="mem_regtime" name="mem_regtime" placeholder="註冊日期"
										data-checked data-required>
								</div>
								<div class="col-sm-8 col-md-6 col-sm-offset-3">
									<span class="help-block"></span>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" id="btnUpdate"
						class="celNcmitBtns btn-success">更新</button>
					<button type="button" id="cancelUpd" class="celNcmitBtns">取消</button>
				</div>
			</div>
		</div>
	</div>
</center>
<%@ include file="/emsPart2.jsp"%>
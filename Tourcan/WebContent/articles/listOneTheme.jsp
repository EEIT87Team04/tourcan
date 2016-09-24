
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tourcan.theme.model.*"%>
<%@ page import="com.tourcan.resp.model.*"%>
<%@ page import="com.tourcan.mem.model.*"%>
<%@ page import="com.tourcan.util.ApplicationContextUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%
	ThemeVO themeVO1 = (ThemeVO) request.getAttribute("themeVO"); 
	RespVO respVO1 =(RespVO) request.getAttribute("resVO"); 
	MemVO memVO1 =(MemVO) request.getAttribute("data");
	MemDAO dao = (MemDAO) ApplicationContextUtils.getContext().getBean("memDAO");

%>


<!DOCTYPE html >
<jsp:include page="/frontPageHeader.jsp" />
<div class="container" style="padding: 10px">
	<div class="row" >
		<div class=" pull-left">
			<h1>　討論區</h1>
		</div>
		<div class="col-md-3 btn pull-right">
			<button type="button" class="btn btn-info form-control" id="newtheme" onclick="javascript:location.href='<%=request.getContextPath()%>/articles/InsertThem.jsp'">發表新主題</button>
		</div>
	</div>
	<div style="border: solid 1px;">
		<table  class="table table-hover">
			<thead>
				<tr class="success"  >
					 <th class="col-md-5"><span class="glyphicon glyphicon-star" aria-hidden="true"> 主題: </span></th>
					<th class="col-md-4"><span class="glyphicon glyphicon-user" aria-hidden="true"> 發表者姓名: </span></th>
					<th class="col-md-3"><span class="glyphicon glyphicon-time" aria-hidden="true"> 建立時間:</span></th>
				</tr>
			</thead>
			<tbody>
				<tr >
					<td class="col-md-5"><%=themeVO1.getTheme_topic()%></td>
					<td class="col-md-4"><%=memVO1.getMem_lname() %><%=memVO1.getMem_fname() %></td>				
					<td class="col-md-3"><%=themeVO1.getTheme_time().toString().substring(0, 16) %></td>
				</tr>
				<tr class="warning">
					<th colspan="4"> 主題內容:</th>	
				</tr>
				<tr>
					<td id="div1" colspan="4"> <%=themeVO1.getTheme_article()%>	</td>
				</tr>
			</tbody>
		</table>
	</div>
  <br>
  <div>
	 <table class="table table-hover" >
		 <c:forEach var="RespVO" items="${list}">
		 <% 
 			RespVO respVO2 =(RespVO) pageContext.getAttribute("RespVO");
			MemVO mvo = dao.findByUid(respVO2.getMem_uid()); 
 		 	pageContext.setAttribute("mvo", mvo);
 		 %> 
		 	<thead style="padding: 10px">
				<tr class="success">
					<th class="col-md-5"><span class="glyphicon glyphicon-star" aria-hidden="true"> 回覆標題:</span></th>
					<th class="col-md-4"><span class="glyphicon glyphicon-user" aria-hidden="true"> 回覆者姓名:</span></th>
					<th class="col-md-3"><span class="glyphicon glyphicon-time" aria-hidden="true"> 回覆時間:</span></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="col-md-5">${RespVO.resp_topic}</td>
					<td class="col-md-4">${mvo.mem_lname}${mvo.mem_fname}</td>
					<td class="col-md-3">${RespVO.resp_time.toString().substring(0, 16)}</td>
			 	</tr>	
				<tr class="warning">
					<th colspan="4"> 回覆內容:</th>	
				</tr>
				<tr>
					<td id="d2" colspan="4" >${RespVO.resp_article }</td>
				</tr>
				<tr>
					<td></td>				
				</tr>
		 	</tbody>
		 </c:forEach>
	 </table>	
	<div>	
		<form name="addResp">
			<div class="row">
				<div  class="col-sm-6 ">
					<h3>發表新回覆</h3>
				</div>
			</div>
			<div>
				<div class="form-inline">
				
					<div class="form-group">
						<span class="glyphicon glyphicon-star-empty" aria-hidden="true"><label for="respTopic">回覆標題</label></span>						
						<input type="text" id="resp_topic" name="resp_topic"  class="form-control" value="回覆:<%=themeVO1.getTheme_topic()%>"	
						placeholder="respTopic">
					</div>
					<div class="form-group" >
						<label for="memId" >會員UId</label> <input type="text"
							id="mem_uid" name="mem_uid" class="form-control" value="${vo.mem_uid}"
							placeholder="會員Id" readonly="readonly" >
					</div>
					<div class="form-group" >
						<label for="themeID" >themeId</label> <input type="number"
							id="theme_id" name="theme_id" class="form-control" value="<%=themeVO1.getTheme_id()%>"
							placeholder="theme_id" min=1>
					</div>		
				</div>
			</div>				
<!-- 				<div id="d2"></div> -->
					<textarea name="resp_article" id="resp_article" class="form-control"></textarea>
			<div class="from-inline">
				<div class="col-md-3" > 
					<button type="button" class="btn btn-default form-control" id="backall" onclick="javascript:location.href='<%=request.getContextPath()%>/articles/list.jsp'">回到所有主題</button>
				</div>  
				<div class="col-md-3 col-sm-offset-6" >
					<button type="button" class="btn btn-success form-control" id="btnInsert"  > 確定回覆</button>
				</div>
			</div>
		</form>
	</div>	
	</div>
</div>
	<script src="//cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
 <script type="text/javascript">
 
		//----------summernote---------------
		 $('#resp_article').summernote({
			 height:200,
			 lang: 'zh-TW', // default: 'en-US'
			 placeholder:"請在此編輯內容"
			 });
		 $('#div2').summernote('code');
		//----------End---------------------
		 $(function() {
				
			 $("#btnInsert").click(function() {
					var errMsgSpan = $('form[name="addResp"] span');
					errMsgSpan.remove();
					var form = $(document.addResp).serializeArray(), json = {};
					for (var i = 0; i < form.length; i++) {
						
						if (form[i].value.length > 0) {
							json[form[i].name] = form[i].value;
						}
					}
						console.log(json);
					$.post("../resp/RespServlet", JSON.stringify(json)).done(function(data) {
						$.each(data, function(errAtt, errMsg) {
							console.log(errMsg);
							if (errMsg == "success") {
								document.addResp.reset();
								errMsgSpan.remove();
								window.location.reload();
							}
							var errSpan = document.createElement("span");
							var errText = document.createTextNode(errMsg);
							var errId = 'err' + errAtt;
							errSpan.appendChild(errText);
							errSpan.setAttribute("style", "color:red");
							errSpan.setAttribute("id", errId);
							$('#' + errId).remove();
							$('#' + errAtt).after(errSpan);
						});
						console.log("200.");
						var makrup = $('#resp_article').summernote('code');
					}).fail(function(xhr) {
						console.log("ERR.");
					});
						
				});
		 
		 });
	
	 </script>
<jsp:include page="/frontPageFooter.jsp" />

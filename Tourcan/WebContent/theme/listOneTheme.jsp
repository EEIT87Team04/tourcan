<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tourcan.theme.model.*"%>
<%@ page import="com.tourcan.resp.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 此頁為script取值 ，應練習改用採用 EL 的寫法取值 --%>

<%
ThemeVO themeVO1 = (ThemeVO) request.getAttribute("themeVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
RespVO respVO1 =(RespVO) request.getAttribute("resVO"); 

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> listOneTheme.jsp</title>
 <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
</head>
<body >
<div class="container" style="padding: 10px">
	<div class="row" >
		
		<div class="col-md-9">
			<h1>討論區</h1>
		</div>
		
		
		<div class="col-md-3 ">
			<button type="button" class="btn btn-info form-control" id="newtheme" onclick="javascript:location.href='<%=request.getContextPath()%>/theme/InsertThem.jsp'">發表新主題</button>
<%-- 		<p><%=request.getContextPath()%> --%> 
		</div>
		
	</div>
	<div style="border: solid 1px;">
		<table  class="table table-hover">
				<thead >
					<tr class="success"  >
<!-- 						<th>ThemeID</th>				 -->
						<th class="col-md-4">主題:</th>
						<th class="col-md-3">memUID</th>
						<th class="col-md-3">分類:</th>
						
					</tr>
				</thead>
				<tbody>
					<tr >
<%-- 						<td><%=themeVO1.getTheme_id()%></td> --%>
						<td><%=themeVO1.getTheme_topic()%></td>
						<td><%=themeVO1.getMem_uid()%></td>				
						<td><%=themeVO1.getTheme_catalog()%></td>
					</tr>
					<tr class="warning">
						<th colspan="4"> 主題內容:</th>	
					</tr>
					<tr>
						<td id="div1" colspan="4"> <%=themeVO1.getTheme_article()%>	</td>
					</tr>
			</tbody>
		</table>
	
<!-- 		<div id="div1"> -->
<!-- 		<!-- <textarea id="testa1"> --> 
<%-- 			<%=themeVO1.getTheme_article()%>	 --%>
<!-- 		<!-- </textarea> --> 
		
<!-- 		</div> -->
	</div>
<!--  <input type="button" value="回首頁" onclick="javascript:location.href='../index.jsp'"/> -->

<br>
	<div >
	<%--  <p>Theme_id: <%=respVO1.getTheme_id()%></p> --%>
	<%--  <p>topic:  <%=respVO1.getResp_topic()%></p> --%>
	<%--  <p>Mem_uid: <%=respVO1.getMem_uid()%></p> --%>
	<%--  <p>respid: <%=respVO1.getResp_id()%></p> --%>
	 <table class="table table-hover" style=" border:dashed 1px black">
		 <c:forEach var="RespVO" items="${list}">
		 	<thead style="padding: 10px">
					<tr  class="success">
<!-- 						<th>resp_id</th> -->
						<th>回覆標題</th>
						<th>mem_uid</th>
<!-- 						<th>theme_id</th>										 -->
					</tr>
				</thead>
				<tbody>
					<tr>
	<%-- 					<td>${RespVO.resp_id}</td> --%>
						<td>${RespVO.resp_topic}</td>
						<td>${RespVO.mem_uid}</td>
	<%-- 					<td>${RespVO.theme_id}</td> --%>
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
						<label for="respTopic" >回覆標題</label>
						
						<input type="text" id="resp_topic" name="resp_topic"  class="form-control" value="回覆:<%=themeVO1.getTheme_topic()%>"	
						placeholder="respTopic">
						
					</div>
					<div class="form-group" >
						<label for="memId" >會員UId</label> <input type="text"
							id="mem_uid" name="mem_uid" class="form-control"
							placeholder="會員Id" >
					</div>
					<div class="form-group" >
						<label for="themeID" >themeId</label> <input type="number"
							id="theme_id" name="theme_id" class="form-control" value="<%=themeVO1.getTheme_id()%>"
							placeholder="theme_id" min=1>
					</div>
					
				
				</div>
			</div>
			<div class="from-grop">
				
				<div id="d2"></div>
					<textarea name="resp_article" id="resp_article" class="form-control"></textarea>
				</div>
			  
			<div></div>
			<div class="col-md-3 col-sm-offset-8" >
				<button type="button" class="btn btn-success form-control" id="btnInsert">確定回覆</button>
			</div>
		</form>
	</div>	
	</div>
</div>

 <script type="text/javascript">
	 $('#resp_article').summernote({
		 height:200
		 });
	 $('#div1').summernote('code', themeValue);
	 
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
				$.post("RespServlet", JSON.stringify(json)).done(function(data) {
					$.each(data, function(errAtt, errMsg) {
						console.log(errMsg);
						if (errMsg == "success") {
							document.addResp.reset();
							errMsgSpan.remove();
							
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
// 					$('#theme_article').summernote('destroy');
// 					$( "#theme_article" ).remove();
				}).fail(function(xhr) {
					console.log("ERR.");
				});
			});
	 
	 });







 
 </script>
 
 
 
 
</body>
</html>

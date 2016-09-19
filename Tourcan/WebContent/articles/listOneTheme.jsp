<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.tourcan.theme.model.*"%>
<%@ page import="com.tourcan.resp.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- ������script���� �A���m�ߧ�αĥ� EL ���g�k���� --%>

<%
ThemeVO themeVO1 = (ThemeVO) request.getAttribute("themeVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
RespVO respVO1 =(RespVO) request.getAttribute("resVO"); 

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> listOneTheme.jsp</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
  <script src="../lang/summernote-zh-TW.js"></script>
</head>
<body >
<div class="container" style="padding: 10px">
	<div class="row" >
		
		<div class="col-md-9">
			<h1>�@�Q�װ�</h1>
		</div>
		
		
		<div class="col-md-3 ">
			<button type="button" class="btn btn-info form-control" id="newtheme" onclick="javascript:location.href='<%=request.getContextPath()%>/theme/InsertThem.jsp'">�o���s�D�D</button>
<%-- 		<p><%=request.getContextPath()%> --%> 
		</div>
		
	</div>
	<div style="border: solid 1px;">
		<table  class="table table-hover">
				<thead >
					<tr class="success"  >
						<th class="col-md-4">�D�D:</th>
						<th class="col-md-3">memUID</th>
						
					</tr>
				</thead>
				<tbody>
					<tr >
<%-- 						<td><%=themeVO1.getTheme_id()%></td> --%>
						<td><%=themeVO1.getTheme_topic()%></td>
						<td><%=themeVO1.getMem_uid()%></td>				
<%-- 						<td><%=themeVO1.getTheme_catalog()%></td> --%>
					</tr>
					<tr class="warning">
						<th colspan="4"> �D�D���e:</th>	
					</tr>
					<tr>
						<td id="div1" colspan="4"> <%=themeVO1.getTheme_article()%>	</td>
					</tr>
			</tbody>
		</table>
	
	</div>

<br>
	<div >
	 <table class="table table-hover" >
		 <c:forEach var="RespVO" items="${list}">
		 	<thead style="padding: 10px">
					<tr  class="success">
						<th>�^�м��D</th>
						<th>mem_uid</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${RespVO.resp_topic}</td>
						<td>${RespVO.mem_uid}</td>
				 	</tr>	
						<tr class="warning">
							<th colspan="4"> �^�Ф��e:</th>	
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
					<h3>�o���s�^��</h3>
				</div>
			</div>
			<div>
				<div class="form-inline">
				
					<div class="form-group">
						<label for="respTopic" >�^�м��D</label>
						
						<input type="text" id="resp_topic" name="resp_topic"  class="form-control" value="�^��:<%=themeVO1.getTheme_topic()%>"	
						placeholder="respTopic">
						
					</div>
					<div class="form-group" >
						<label for="memId" >�|��UId</label> <input type="text"
							id="mem_uid" name="mem_uid" class="form-control"
							placeholder="�|��Id" >
					</div>
					<div class="form-group" >
						<label for="themeID" >themeId</label> <input type="number"
							id="theme_id" name="theme_id" class="form-control" value="<%=themeVO1.getTheme_id()%>"
							placeholder="theme_id" min=1>
					</div>
					
				
				</div>
			</div>
				
				<div id="d2"></div>
					<textarea name="resp_article" id="resp_article" class="form-control"></textarea>
			<div class="from-inline">
				<div class="col-md-3" > 
					<button type="button" class="btn btn-default form-control" id="backall" onclick="javascript:location.href='<%=request.getContextPath()%>/theme/listAllTheme.jsp'">�^��Ҧ��D�D</button>
			
				</div>  
				<div class="col-md-3 col-sm-offset-6" >
					<button type="button" class="btn btn-success form-control" id="btnInsert"  > �T�w�^��</button>
				</div>
			</div>
		</form>
	</div>	
	</div>
</div>

 <script type="text/javascript">
 
		//----------summernote---------------
		 $('#resp_article').summernote({
			 height:200,
			 lang: 'zh-TW', // default: 'en-US'
			 placeholder:"�Цb���s�褺�e"
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
	 
	</body>
</html>
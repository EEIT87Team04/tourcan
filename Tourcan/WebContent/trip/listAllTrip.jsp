<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tourcan.trip.model.*"%>
<%@ page import="java.util.*" %>
<%
	TripService trsv =new TripService();
	List<TripVO> list =trsv.getAll();
	pageContext.setAttribute("list",list);

		
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/emsPart1.jsp"/>
<div class="container">
	<div class="row" >
		
		<div class="col-md-6">
			<h1>Trip主題列表</h1>
		</div>
		
		<div class="col-md-6 ">
			<form class=form-inline>
				<div class="col-md-8">
					<div class="form-group">
						<label for="themeTopic">TopicSearch</label><input type="text" class="form-control" id="theme_topic" name="themetopic" placeholder="themeTopic">
					<button type="button" class="btn btn-success form-control" id="btnTopicCheck">搜尋</button>
					</div>
				</div>
			</form>
			<div class="col-md-4">
				<button type="button" class="btn btn-info form-control" id="newtheme" onclick="javascript:location.href='<%=request.getContextPath()%>/theme/InsertThem.jsp'">發表新主題</button>
			</div>	
<%-- 		<p><%=request.getContextPath()%> --%> 
		</div>
		
	</div>
<input type='hidden' id='current_page' /> <input type='hidden'
		id='show_per_page' />

	<table id="themeList" class="table table-hover tableList">
		<thead>
			<tr >
				<th>id</th>				
				<th>TripName</th>
				<th>TripCtime</th>
				<th>TripPrice</th>
				<th>memUID</th>
		
			</tr>
		</thead>
		<tbody id="dataList">
			<c:forEach var="TripVO" items="${list}">
				<tr >
					<td >
						<FORM METHOD="get" ACTION="TripServlet">
						<input class="btn btn-primary" type="submit" value="${TripVO.trip_id}">
						<input type="hidden"name="trip_id" value="${ThemeVO.theme_id}">
						<input type="hidden"name="trip_id" value="${ThemeVO.trip_id}">
						<input type="hidden"name="method" value="getOne_For_Display">				
						</FORM>
					 </td>
					 <td>
						<FORM METHOD="get" ACTION="TripServlet">
						<input class="btn btn-default" type="submit" value="${TripVO.trip_name}">
						<input type="hidden"name="trip_name" value="${TripVO.trip_name}">
						<input type="hidden"name="trip_name" value="${TripVO.trip_name}">
						<input type="hidden"name="method" value="getOne_For_Display">
					</FORM>
					</td>
					<td>${TripVO.trip_ctime}</td>
					<td>${TripVO.trip_price}</td>
					<td>${TripVO.mem_uid}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<ul id='page_navigation' class="pagination pagination-lg"></ul>
</div>

<script type="text/javascript">

$(function(){
// 	var serviceProvider = "${contextPath}/members";
// 	var umd = "t3L6KuZlh4W4X3IIzexqIzTFtGh1";
// 	console.log(serviceProvider);
// 	$.ajax(
// 			{
// 				url:serviceProvider+"/t3L6KuZlh4W4X3IIzexqIzTFtGh1",
// 				method:"Get",
// 				contentType:"application/json ; charset=UTF-8",
// // 				data:{uid:"t3L6KuZlh4W4X3IIzexqIzTFtGh1"},
// 				dataType:"json"	,
// 				success:function(data){
// 							console.log(data) 
// // 							console.log("index"+attName)
// // 							console.log(attValue)
						
// 				}
						
// 	})
		

// 	$("#btnTopicCheck").click(function() {
// 		var theme_topic = $("#theme_topic").val();
// 		$('#themeList>tbody').empty();
// 		$("#d1").empty();
	
// 		$.getJSON(("ThemeServlet"),{"theme_topic" : theme_topic},function(data){
// 			var myBody = $('#themeList>tbody');
// 			$.each(data,function(themeName,themeValue){
// 				if(themeName=="themeTopic"){
// 						console.log(themeName);
// 				    if(themeValue=="themeTopic error" || themeValue=="無此Topic"){
// 					    alert("查無編號!");
// 					    var msg = '<label class="error" for="themeTopic">'+themeValue+'</label>';
// 						$('input[name="themetopic"]').addClass('inputTxtError').after(msg);
// 					}
		        
// 				}else{
// 						var cell1 = $("<td/>").text(themeValue.theme_id);
// 						var cell2 = $("<td/>").text(themeValue.theme_topic);
// 						var input3= $("<input/>").attr("type",'hidden').attr('name',"action").attr("value","getOne_For_Display");
// 						var input2= $("<input/>").attr("type",'hidden').attr('name',"theme_id").attr("value",themeValue.theme_id);
// 						var input1= $("<input/>").attr("class","btn btn-default").attr("type","submit").attr("value",themeValue.theme_topic);
// 						var form  = $("<form/>").attr("METHOD","post").attr("ACTION","ThemeServlet").append([input1,input2,input3]);
// 						var cell5 = $("<td/>").append([form])
// 						var cell3 = $("<td></td>").text(themeValue.mem_uid);
// 						var row = $("<tr></tr>").append([cell1,cell5,cell3]);
// 						myBody.append(row);
// 					}
// 			})
// 	})
// })
	
	
	
	
	//----------Pagination-------------------------------------------------	
	//how much items per page to show  
	var show_per_page = 10;  
	//getting the amount of elements inside content div  
	var number_of_items = $('#themeList tbody').children().length;
// 	console.log(number_of_items);
	//calculate the number of pages we are going to have  
	var number_of_pages = Math.ceil(number_of_items/show_per_page);  
	
	//set the value of our hidden input fields  
	$('#current_page').val(0);  
	$('#show_per_page').val(show_per_page);  
	
	//now when we got all we need for the navigation let's make it '  
	
	/* 
	what are we going to have in the navigation? 
	    - link to previous page 
	    - links to specific pages 
	    - link to next page 
	*/  
	// var navigation_html = '<li><a class="previous_link" href="javascript:previous();">Prev</a></li>';
	var navigation_html = "";
	var current_link = 0;  
	while(number_of_pages > current_link){  
	    navigation_html += '<li><a class="page_link" href="javascript:go_to_page(' + current_link +')" longdesc="' + current_link +'">'+ (current_link + 1) +'</a></li>';  
	    current_link++;  
	}  
	// navigation_html += '<li><a class="next_link" href="javascript:next();">Next</a></li>';  
	
	$('#page_navigation').html(navigation_html);  
	
	//add active_page class to the first page link  
	$('#page_navigation .page_link:first').addClass('active_page');  
	
	//hide all the elements inside content div  
	$('#themeList tbody').children().css('display', 'none');  
	
	//and show the first n (show_per_page) elements  
	$('#themeList tbody').children().slice(0, show_per_page).css('display', 'table-row');  
		
	});
	
	
	function previous(){  
	  
	new_page = parseInt($('#current_page').val()) - 1;  
	//if there is an item before the current active link run the function  
	if($('.active_page').prev('.page_link').length==true){  
	    go_to_page(new_page);
		}
	}
	
	function next(){  
	    new_page = parseInt($('#current_page').val()) + 1;  
	    //if there is an item after the current active link run the function  
	    if($('.active_page').next('.page_link').length==true){  
	        go_to_page(new_page);  
	    }  
	  
	}  
	function go_to_page(page_num){  
	    //get the number of items shown per page  
	    var show_per_page = parseInt($('#show_per_page').val());  
	  
	    //get the element number where to start the slice from  
	    start_from = page_num * show_per_page;  
	  
	    //get the element number where to end the slice  
	    end_on = start_from + show_per_page;  
	  
	    //hide all children elements of content div, get specific items and show them  
	    $('#themeList tbody').children().css('display', 'none').slice(start_from, end_on).css('display', 'table-row');  
	  
	    /*get the page link that has longdesc attribute of the current page and add active_page class to it 
	    and remove that class from previously active page link*/  
	    $('.page_link[longdesc=' + page_num +']').addClass('active_page').siblings('.active_page').removeClass('active_page');  
	  
	    //update the current page input field  
	    $('#themeList tbody').val(page_num);  
	}  
	//----------End_Pagination-------
	



</script>
<jsp:include page="/emsPart2.jsp"/>
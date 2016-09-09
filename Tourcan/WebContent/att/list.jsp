<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/emsPart1.jsp" />
<center>
	<input type='hidden' id='current_page' />  
	<input type='hidden' id='show_per_page' />  
	<table id="attList" class="table table-hover tableList" >
	<thead>
		<tr>
			<th>編號</th>
			<th>地區</th>
			<th>地名</th>
			<th>網址</th>
			<th>更新</th>
			<th>刪除</th>
		</tr>
 	</thead>
 	<tbody id="dataList">
 	
 	</tbody>
	</table>
	<ul id='page_navigation' class="pagination pagination-lg"></ul>
</center>
<script>
	$(function() {
		$.getJSON('AttServlet', function(data) {
			$.each(data, function(index, att) {
				var row = $("<tr/>");
				var id = $("<td/>").text(att.att_id);
				var regName = $("<td/>").text(att.regionVO.region_name);
				var name = $("<td/>").text(att.att_name);
				var url = $("<td/>").text(att.att_url);
				var ubtn = $("<td/>").append($("<button/>").addClass("upNdelBtns").text("更新").val(att.att_id));
				var dbtn = $("<td/>").append($("<button/>").addClass("upNdelBtns").text("刪除").val(att.att_id));
				var table = $("#attList >tbody");
				
				table.append(row.append([id,regName,name,url,ubtn,dbtn]));
			});
			
	    //how much items per page to show  
	    var show_per_page = 10;  
	    //getting the amount of elements inside content div  
	    var number_of_items = $('#attList tbody').children().length;
	    console.log(number_of_items);
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
	    var navigation_html = '<li><a class="previous_link" href="javascript:previous();">Prev</a></li>';  
	    var current_link = 0;  
	    while(number_of_pages > current_link){  
	        navigation_html += '<li><a class="page_link" href="javascript:go_to_page(' + current_link +')" longdesc="' + current_link +'">'+ (current_link + 1) +'</a></li>';  
	        current_link++;  
	    }  
	    navigation_html += '<li><a class="next_link" href="javascript:next();">Next</a></li>';  
	  
	    $('#page_navigation').html(navigation_html);  
	  
	    //add active_page class to the first page link  
	    $('#page_navigation .page_link:first').addClass('active_page');  
	  
	    //hide all the elements inside content div  
	    $('#attList tbody').children().css('display', 'none');  
	  
	    //and show the first n (show_per_page) elements  
	    $('#attList tbody').children().slice(0, show_per_page).css('display', 'table-row');  
			
		});
	
				
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
	    $('#attList tbody').children().css('display', 'none').slice(start_from, end_on).css('display', 'table-row');  
	  
	    /*get the page link that has longdesc attribute of the current page and add active_page class to it 
	    and remove that class from previously active page link*/  
	    $('.page_link[longdesc=' + page_num +']').addClass('active_page').siblings('.active_page').removeClass('active_page');  
	  
	    //update the current page input field  
	    $('#attList tbody').val(page_num);  
	}  
</script>
<script>
	$(function(){
		var path = window.location.pathname;		
		console.log(new RegExp("/att/").test(path));
		
	});
</script>
<jsp:include page="/emsPart2.jsp" />
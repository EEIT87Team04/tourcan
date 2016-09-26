<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<jsp:include page="/frontPageHeader.jsp" />
		<div class="row">
			<div class="col-md-6">
				<h1>旅遊熱點</h1>
			</div>
			<div class="col-md-6 ">
				<form class=form-inline>
					<div class="col-md-8">
						<div class="form-group">
							<input type="text"
								class="form-control" id="theme_topic" name="themetopic"
								placeholder="search">
							<button type="button" class="btn btn-success form-control"
								id="btnTopicCheck">搜尋</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<input type='hidden' id='current_page' /> <input type='hidden'
			id='show_per_page' />
		<div class="row" id="dataList">
			<hr>
			<c:forEach var="AttVO" items="${attVO}" varStatus="count">
				<div class="row">
					<div class="col-md-4 col-md-offset-1" style="max-height: 300px; overflow: hidden;">
						<p><img src="${imgs[count.index]}" class="imgSize"></p>
					</div>
					<div class="col-md-7">
						<div class="row">
							<div class="col-md-12">
								<h3>
									<a href='<c:url value="/attractions/${AttVO.att_id}" />'>${AttVO.att_name}</a>
								</h3>
							</div>
							<div class="col-md-12">${AttVO.att_intro}</div>
							<div class="col-md-12">交通情報：${AttVO.att_addr}</div>
							<div class="col-md-12">開放資訊：${AttVO.att_open}</div>
						</div>
					</div>
				</div>
			<hr>
			</c:forEach>
		</div>
		<ul id='page_navigation' class="pagination pagination-lg"></ul>
	<script type="text/javascript">
		$(function() {
			//----------Pagination-------------------------------------------------	
			//how much items per page to show  
			var show_per_page = 10;
			//getting the amount of elements inside content div  
			var number_of_items = $('#dataList').children().length;
			// 	console.log(number_of_items);
			//calculate the number of pages we are going to have  
			var number_of_pages = Math.ceil(number_of_items / show_per_page);

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
			while (number_of_pages > current_link) {
				navigation_html += '<li><a class="page_link" href="javascript:go_to_page('
						+ current_link
						+ ')" longdesc="'
						+ current_link
						+ '">'
						+ (current_link + 1) + '</a></li>';
				current_link++;
			}
			// navigation_html += '<li><a class="next_link" href="javascript:next();">Next</a></li>';  

			$('#page_navigation').html(navigation_html);

			//add active_page class to the first page link  
			$('#page_navigation .page_link:first').addClass('active_page');

			//hide all the elements inside content div  
			$('#dataList').children().css('display', 'none');

			//and show the first n (show_per_page) elements  
			$('#dataList').children().slice(0, show_per_page)
					.css('display', '');

		});

		function previous() {

			new_page = parseInt($('#current_page').val()) - 1;
			//if there is an item before the current active link run the function  
			if ($('.active_page').prev('.page_link').length == true) {
				go_to_page(new_page);
			}
		}

		function next() {
			new_page = parseInt($('#current_page').val()) + 1;
			//if there is an item after the current active link run the function  
			if ($('.active_page').next('.page_link').length == true) {
				go_to_page(new_page);
			}

		}
		function go_to_page(page_num) {
			//get the number of items shown per page  
			var show_per_page = parseInt($('#show_per_page').val());

			//get the element number where to start the slice from  
			start_from = page_num * show_per_page;

			//get the element number where to end the slice  
			end_on = start_from + show_per_page;

			//hide all children elements of content div, get specific items and show them  
			$('#dataList').children().css('display', 'none').slice(start_from,
					end_on).css('display', '');

			/*get the page link that has longdesc attribute of the current page and add active_page class to it 
			and remove that class from previously active page link*/
			$('.page_link[longdesc=' + page_num + ']').addClass('active_page')
					.siblings('.active_page').removeClass('active_page');

			//update the current page input field  
			$('#dataList').val(page_num);
		}
		//----------End_Pagination-------
	</script>
<jsp:include page="/frontPageFooter.jsp" />
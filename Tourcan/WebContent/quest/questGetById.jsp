<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/frontPageHeader.jsp"/>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.35.3/js/bootstrap-dialog.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.35.3/js/bootstrap-dialog.min.js"></script>
  <div>
    <span style="font-size: 200%;font-weight:bold">Question List</span>
    <button class="btn btn-primary btn-lg outline pull-right"  id="quest_raise" style="font-size:13px">搜尋問題</button>
    <input type="text" class="pull-right tooltip-show" id="txt_topic" data-placement="left" title="請輸入查詢標題" style="margin:3px">
  </div>
  <button class="btn btn-primary" id="quest_getAll">查詢全部</button>
  <input type="hidden" id="memuid" value="${vo.mem_uid }">
  <button class="btn btn-info" id="quest_cancel">取消查詢</button>
  <button class="btn btn-danger pull-right" id="quest_insert">提出問題</button>
  <p></p>
  <div id="table1">
  <input type='hidden' id='current_page' /> <input type='hidden'
		id='show_per_page' />
  <table class="table table-striped" id="replyAll">
    <thead>
      <tr>
        <th>問題序號</th>
        <th>問題分類</th>
        <th>標題</th>
        <th>提問時間</th>
        <th>功能</th>
      </tr>
    </thead>
    <tbody></tbody>
  </table>
  <ul id='page_navigation' class="pagination pagination-lg"></ul>
  </div>
</div>


<div class="container">
  <!-- Trigger the modal with a buttom n -->
  <!-- <button type="button" class="btn btn-default btn-lg" id="quest_editReply">Reply</button> -->
    
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="padding:35px 50px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 id="h4_1"><span class="glyphicon glyphicon-lock"></span> Raise Your Question</h4>
        </div>
        <div class="modal-body" style="padding:40px 50px;">
          <form role="form" name="addQuest">
            <div class="form-group">
              <label for="questCategory"><span class="glyphicon glyphicon-list"></span> Question Category</label>
              <input type="number" class="form-control" id="quest_catalog" name="quest_catalog" placeholder="Enter Question Category" min=1>
            </div>
            <div class="form-group">
              <label for="usrname"><span class="glyphicon glyphicon-tags"></span> Question Topic</label>
              <input type="text" class="form-control" id="quest_topic" name="quest_topic" placeholder="Enter Question Topic">
            </div>
            <div class="form-group">
              <label for="questReply"><span class="glyphicon glyphicon-edit"></span> Reply Text</label>
              <textarea class="form-control" id="quest_quiz" name="quest_quiz" placeholder="Enter Reply Text"></textarea>
            </div>
            <div class="checkbox">
              <label></label>
            </div>
              <button type="button" class="btn btn-success btn-block form-control" id="btnInsert"><span class="glyphicon glyphicon-off"></span> 確定</button>
            <div >
				<div  id="result"></div>
			</div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
          <p></p>
          <p></p>
        </div>
      </div>
      
    </div>
  </div>
	<script type="text/javascript">
	$(function(){
        $('#replyOne').hide();
        $('#replyAll').hide();
	    
        
        $("#quest_getAll").click(function() {
		    $('#replyAll').show();
		    $('#replyAll>tbody').empty();
		
		var memUid=$('#memuid').val();
		$.getJSON(("QuestServlet"),{"memUid":memUid,"method":"getAllByUid"},function(data){
		    console.log(memUid);
		    var myBody = $('#replyAll>tbody');
			$.each(data,function(idx,data){
				console.log(data);
			    var but1=$('<button class="btn btn-success btn-xs" id="quest_edit">查看</button>');
			    var but2=$('<button class="btn btn-warning btn-xs" id="quest_editCancel">取消查看</button>');
				var cell1 = $("<td></td>").text(data.quest_id);
				var cell2 = $("<td></td>").text(data.quest_catalog);
				var cell3 = $("<td></td>").text(data.quest_topic);
				var cell4 = $("<td></td>").text(data.quest_qtime);
				var cell5 = $("<td></td>").append(but1).append("  ").append(but2);
				var row = $("<tr></tr>").append([cell1,cell2,cell3,cell4,cell5]);
				myBody.append(row);				
			})
			
			$("tr").not(':first').hover(
				function () {
				    $(this).css("background","#FFD9EC");},
			    function () {
					$(this).css("background","");});
			
			//----------Pagination-----------	
		    //how much items per page to show  
		    var show_per_page = 10;  
		    //getting the amount of elements inside content div  
		    var number_of_items = $('#replyAll tbody').children().length;
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
//	 	    var navigation_html = '<li><a class="previous_link" href="javascript:previous();">Prev</a></li>';
	        var navigation_html = "";
		    var current_link = 0;  
		    while(number_of_pages > current_link){  
		        navigation_html += '<li><a class="page_link" href="javascript:go_to_page(' + current_link +')" longdesc="' + current_link +'">'+ (current_link + 1) +'</a></li>';  
		        current_link++;  
		    }  
//	 	    navigation_html += '<li><a class="next_link" href="javascript:next();">Next</a></li>';  
		  
		    $('#page_navigation').html(navigation_html);  
		  
		    //add active_page class to the first page link  
		    $('#page_navigation .page_link:first').addClass('active_page');  
		  
		    //hide all the elements inside content div  
		    $('#replyAll>tbody').children().css('display', 'none');  
		  
		    //and show the first n (show_per_page) elements  
		    $('#replyAll>tbody').children().slice(0, show_per_page).css('display', 'table-row');  
			
			
			})
		})
		
		
		$("#quest_raise").click(function() {
		    $('#replyAll').show();
		    $('#replyAll>tbody').empty();
		
		    var questTopic = $('#txt_topic').val();
		    if(questTopic == null || questTopic.trim().length == 0 ){
		    	$('.tooltip-show').attr('data-original-title', '請勿空白!')
		                          .tooltip('show'); 
		    	$('#replyAll').hide();
		    }else{
		        $.getJSON(("QuestServlet"),{"questTopic":questTopic,"method":"getByName"},function(data){
		            var myBody = $('#replyAll>tbody');
		            
			        $.each(data,function(idx,data){
                        console.log("idx:"+idx);
                        console.log("data:"+data);
		                if(idx=="result"){
		                	$('#replyAll').hide();
		            	    $('.tooltip-show').attr('data-original-title', '查無資料!')
	                                          .tooltip('show');
		                }else{
			                var but1=$('<button class="btn btn-success btn-xs" id="quest_edit">查看</button>');
			                var but2=$('<button class="btn btn-warning btn-xs" id="quest_editCancel">取消查看</button>');
				            var cell1 = $("<td></td>").text(data.quest_id);
				            var cell2 = $("<td></td>").text(data.quest_catalog);
			         	    var cell3 = $("<td></td>").text(data.quest_topic);
			        	    var cell4 = $("<td></td>").text(data.quest_qtime);
			        	    var cell5 = $("<td></td>").append(but1).append("  ").append(but2);
			        	    var row = $("<tr></tr>").append([cell1,cell2,cell3,cell4,cell5]);
			        	    myBody.append(row);		
		                }
		            })
			        
			
			    $("tr").not(':first').hover(
				    function () {
				        $(this).css("background","#FFD9EC");},
			        function () {
					    $(this).css("background","");});
			        
		            
			    })
		    }
		})
		

		
        $("#replyAll").on('click','.btn-success',function(){
//          $('#replyOne').show();
            quest_replyValue=null;
            $('#replyOne').remove();
		    var rowClosest = $(this).closest("tr");              
		    var questId = rowClosest.find("td:eq(0)").text();    
		    console.log(questId);
		    
		    $.get(("QuestServlet"),{"questId":questId,"method":"getOneById"},function(data){
			    $.each(data,function(questName,questValue){
// 		   	   	    console.log(questName+" : "+questValue);
				    if(questName=="quest_reply"){
                        quest_replyValue=questValue;
				    	console.log(quest_replyValue);
				    }
				    if(questName=="quest_quiz"){
				    	quest_quizValue=questValue;
				    	console.log(quest_quizValue);		
				    }
				    if(questName=="quest_qtime"){
				    	quest_qtimeValue=questValue;
				    	console.log(quest_qtimeValue);		
				    }
				    if(questName=="quest_rtime"){
				    	quest_rtimeValue=questValue;
				    	console.log(quest_rtimeValue);		
				    }
			    })
			       
			    if(typeof quest_replyValue === "undefined" ||quest_replyValue == null){
			        var frag = document.createDocumentFragment();
		  			var div_head = document.createElement("div");
// 		  			div_head.className="container";
		  			div_head.setAttribute("id","replyOne");
		  			
		  			var ul_afterHead= document.createElement("ul");
		  			ul_afterHead.className="timeline";
		  			div_head.appendChild(ul_afterHead);
		  			
		  			
		  			var li_afterUl=document.createElement("li");
		  			ul_afterHead.appendChild(li_afterUl);
		  			
		  			
		  			var div_afterLi1 = document.createElement("div");
		  			div_afterLi1.className="timeline-badge";
		  			li_afterUl.appendChild(div_afterLi1);
		  			
		  			var i_afterDiv = document.createElement("i");
		  			i_afterDiv.className="glyphicon glyphicon-check";
		  			div_afterLi1.appendChild(i_afterDiv);
		  			
		  			var div_afterLi2 = document.createElement("div");
		  			div_afterLi2.className="timeline-panel";
		  			li_afterUl.appendChild(div_afterLi2);
		  			
		  			var div_afterDiv2 = document.createElement("div");
		  			div_afterDiv2.className="timeline-heading";
		  			div_afterLi2.appendChild(div_afterDiv2);
		  			
		  			var h4_afterDiv2_1 = document.createElement("h4");
		  			h4_afterDiv2_1.className="timeline-title";
		  			var txt_h4=document.createTextNode("會員提問");
		  			h4_afterDiv2_1.appendChild(txt_h4);
		  			div_afterDiv2.appendChild(h4_afterDiv2_1);
		  			
		  			var p_afterDiv2_1 = document.createElement("p");
		  			div_afterDiv2.appendChild(p_afterDiv2_1);
		  			
		  			var small_afterP = document.createElement("small");
		  			small_afterP.className="text-muted";
		  			
		  			var i_afterP = document.createElement("i");
		  			i_afterP.className="glyphicon glyphicon-time";
		  			small_afterP.appendChild(i_afterP);
		  			small_afterP.setAttribute("style","word-break: break-all");
		  			var txt_small=document.createTextNode(quest_qtimeValue);  //須拿提問時間
		  			small_afterP.appendChild(txt_small);
		  			p_afterDiv2_1.appendChild(small_afterP);
					
		  			var div2_afterDiv2 = document.createElement("div");
		  			div2_afterDiv2.className="timeline-body";
		  			div_afterLi2.appendChild(div2_afterDiv2);
		  			
		  			var p_afterDiv2_2 = document.createElement("p");
		  			p_afterDiv2_2.setAttribute("style","word-break: break-all");
		  			var txt_p=document.createTextNode(quest_quizValue);       //須拿提問內容
		  			p_afterDiv2_2.appendChild(txt_p);
		  			div2_afterDiv2.appendChild(p_afterDiv2_2);
		  			
		  			frag.appendChild(div_head);
		  			
		  			rowClosest.after(frag);
			    }else{
			        // Question div
	                var frag = document.createDocumentFragment();
			  		var div_head = document.createElement("div");
// 			  		div_head.className="container";
			  		div_head.setAttribute("id","replyOne");
			  			
			  		var ul_afterHead= document.createElement("ul");
			  		ul_afterHead.className="timeline";
			  		div_head.appendChild(ul_afterHead);
			  			
			  			
			  		var li_afterUl=document.createElement("li");
			  		ul_afterHead.appendChild(li_afterUl);
			  			
			  			
			  		var div_afterLi1 = document.createElement("div");
			  		div_afterLi1.className="timeline-badge";
			  		li_afterUl.appendChild(div_afterLi1);
			  			
			  		var i_afterDiv = document.createElement("i");
			  		i_afterDiv.className="glyphicon glyphicon-check";
			  		div_afterLi1.appendChild(i_afterDiv);
			  			
			  		var div_afterLi2 = document.createElement("div");
			  		div_afterLi2.className="timeline-panel";
			  		li_afterUl.appendChild(div_afterLi2);
			  			
			  		var div_afterDiv2 = document.createElement("div");
			  		div_afterDiv2.className="timeline-heading";
			  		div_afterLi2.appendChild(div_afterDiv2);
			  			
			  		var h4_afterDiv2_1 = document.createElement("h4");
					h4_afterDiv2_1.className="timeline-title";
			  		var txt_h4=document.createTextNode("會員提問");
			  		h4_afterDiv2_1.appendChild(txt_h4);
			  		div_afterDiv2.appendChild(h4_afterDiv2_1);
			  			
			  		var p_afterDiv2_1 = document.createElement("p");
			  		div_afterDiv2.appendChild(p_afterDiv2_1);
			  			
			  		var small_afterP = document.createElement("small");
			  		small_afterP.className="text-muted";
			  			
			  		var i_afterP = document.createElement("i");
			  		i_afterP.className="glyphicon glyphicon-time";
			  		small_afterP.appendChild(i_afterP);
			  		small_afterP.setAttribute("style","word-break: break-all");
			  		var txt_small=document.createTextNode(quest_qtimeValue);  //須拿提問時間
			  		small_afterP.appendChild(txt_small);
			  		p_afterDiv2_1.appendChild(small_afterP);
						
			  		var div2_afterDiv2 = document.createElement("div");
			  		div2_afterDiv2.className="timeline-body";
			  		div_afterLi2.appendChild(div2_afterDiv2);
			  			
			  		var p_afterDiv2_2 = document.createElement("p");
			  		p_afterDiv2_2.setAttribute("style","word-break: break-all");
			  		var txt_p=document.createTextNode(quest_quizValue);       //須拿提問內容
			  		p_afterDiv2_2.appendChild(txt_p);
			  		div2_afterDiv2.appendChild(p_afterDiv2_2);
			  			
			  		// Reply div
			  		var li2_afterUl=document.createElement("li");
			  		li2_afterUl.className="timeline-inverted";
			  		ul_afterHead.appendChild(li2_afterUl);
			  			
			  		var div1_afterLi2 = document.createElement("div");
			  		div1_afterLi2.className="timeline-badge warning";
			  		li2_afterUl.appendChild(div1_afterLi2);
			  			
			  		var i2_afterDiv = document.createElement("i");
			  		i2_afterDiv.className="glyphicon glyphicon-credit-card";
			  		div1_afterLi2.appendChild(i2_afterDiv);
			  			
			  		var div2_afterLi2 = document.createElement("div");
			  		div2_afterLi2.className="timeline-panel";
			  		li2_afterUl.appendChild(div2_afterLi2);
			  			
			  		var div_afterLi2Div2 = document.createElement("div");
			  		div_afterLi2Div2.className="timeline-heading";
			  		div2_afterLi2.appendChild(div_afterLi2Div2);
			  			
			  		var h42_afterDiv2_1 = document.createElement("h4");
			  		h42_afterDiv2_1.className="timeline-title";
			  		var txt_h42=document.createTextNode("管理員回覆");
			  		h42_afterDiv2_1.appendChild(txt_h42);
			  		div_afterLi2Div2.appendChild(h42_afterDiv2_1);
			  			
			  		var p2_afterDiv2_1 = document.createElement("p");
	                div_afterLi2Div2.appendChild(p2_afterDiv2_1);
			  			
			  		var small2_afterP = document.createElement("small");
			  		small2_afterP.className="text-muted";
			  			
			  		var i2_afterP = document.createElement("i");
			  		i2_afterP.className="glyphicon glyphicon-time";
			  		small2_afterP.appendChild(i_afterP);
			  		small2_afterP.setAttribute("style","word-break: break-all");
			  		var txt2_small=document.createTextNode(quest_rtimeValue);  //須拿回覆時間
			  		small2_afterP.appendChild(txt2_small);
			  		p2_afterDiv2_1.appendChild(small2_afterP);
			  			
		  			var div2_afterLi2Div2 = document.createElement("div");
		  			div2_afterLi2Div2.className="timeline-body";
		  			div2_afterLi2.appendChild(div2_afterLi2Div2);
			  			
		  			var p2_afterDiv2_2 = document.createElement("p");
		  			p2_afterDiv2_2.setAttribute("style","word-break: break-all");
		  			var txt2_p=document.createTextNode(quest_replyValue);       //須拿回覆內容
		  			p2_afterDiv2_2.appendChild(txt2_p);
		  			div2_afterLi2Div2.appendChild(p2_afterDiv2_2);
			  			
		  			frag.appendChild(div_head);
			  			
		  			rowClosest.after(frag);
			    }
           })
		    
  			
           $(".btn-warning").click(function() {
               $(this).parent().parent().next('#replyOne').remove();
	       })
	       
	       
       })    
    
	
	   $("#quest_cancel").click(function() {
		   $('#replyAll').remove();
	   })
	   
	   $(".btn-danger").click(function(){
	       $("#myModal").modal();
	       errMsgSpan = $('form[name="addQuest"] p');
	       errMsgSpan.remove();
		        
		   $("#btnInsert").off('click').on('click',function() {
// 		   errMsgSpan = $('form[name="addQuest"] span');
// 		   errMsgSpan.remove();
		       var form = $(document.addQuest).serializeArray(), json = {};
					
				for (var i = 0; i < form.length; i++) {
						
					if (form[i].value.length > 0) {
						json[form[i].name] = form[i].value;
					}
						console.log(json);
					}
					$.post("QuestServlet", JSON.stringify(json)).done(function(data) {
						console.log(JSON.stringify(json));
						$.each(data, function(errTrip, errMsg) {
							
							if (errMsg == "新增成功") {
								document.addQuest.reset();
								errMsgSpan.empty();
								setTimeout(function() { $('#myModal').modal('hide'); }, 1500);
								
								BootstrapDialog.alert({
									 title: $('<h4 style="color:white !important;text-align: center;font-weight:bold"><span class="glyphicon glyphicon-check"></span>新增問題成功</h4>'),
							         message: $('<p style="color:blue;font-weight:bold;text-align:ccenter">謝謝您的提問，我們會盡快為您處理！</p>')
							    });
							}
							
							var errSpan = document.createElement("p");
							var errText = document.createTextNode(errMsg);
							var errId = 'err' + errTrip;
							errSpan.appendChild(errText);
							errSpan.setAttribute("style", "color:red");
							errSpan.setAttribute("id", errId);
							$('#' + errId).remove();
							$('#' + errTrip).after(errSpan);
							console.log(errMsg);
							
						});
						console.log("200.");
					}).fail(function(xhr) {
						console.log("ERR.");
					});
				});
	   })
	
	   $("#txt_topic").hover(function(){
		   $('.tooltip-show').tooltip('hide')
		                     .attr('data-original-title', '請輸入查詢標題')
		                     .tooltip('show');
		});
        
		
		
	})
	
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
	    $('#replyAll>tbody').children().css('display', 'none').slice(start_from, end_on).css('display', 'table-row');  
	  
	    /*get the page link that has longdesc attribute of the current page and add active_page class to it 
	    and remove that class from previously active page link*/  
	    $('.page_link[longdesc=' + page_num +']').addClass('active_page').siblings('.active_page').removeClass('active_page');  
	  
	    //update the current page input field  
	    $('#replyAll>tbody').val(page_num);  
	    
	}  
	
	</script>
<jsp:include page="/frontPageFooter.jsp"/>


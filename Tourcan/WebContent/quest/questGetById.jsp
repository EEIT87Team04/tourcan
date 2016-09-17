<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Question BackStagew</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!--   <link href="/Tourcan/css/style.css" rel="stylesheet"> -->
<!--   <script src="/Tourcan/js/jquery.paginate.js"></script> -->
<!--   <script src="/Tourcan/js/jquery.paginate.js"></script> -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style type="text/css">
.timeline {
	list-style: none;
	padding: 20px 0 20px;
	position: relative;
}

.timeline:before {
	top: 0;
	bottom: 0;
	position: absolute;
	content: " ";
	width: 3px;
	background-color: #eeeeee;
	left: 50%;
	margin-left: -1.5px;
}

.timeline>li {
	margin-bottom: 20px;
	position: relative;
}

.timeline>li:before, .timeline>li:after {
	content: " ";
	display: table;
}

.timeline>li:after {
	clear: both;
}

.timeline>li:before, .timeline>li:after {
	content: " ";
	display: table;
}

.timeline>li:after {
	clear: both;
}

.timeline>li>.timeline-panel {
	width: 46%;
	float: left;
	border: 1px solid #d4d4d4;
	border-radius: 2px;
	padding: 20px;
	position: relative;
	-webkit-box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
	box-shadow: 0 1px 6px rgba(0, 0, 0, 0.175);
}

.timeline>li>.timeline-panel:before {
	position: absolute;
	top: 26px;
	right: -15px;
	display: inline-block;
	border-top: 15px solid transparent;
	border-left: 15px solid #ccc;
	border-right: 0 solid #ccc;
	border-bottom: 15px solid transparent;
	content: " ";
}

.timeline>li>.timeline-panel:after {
	position: absolute;
	top: 27px;
	right: -14px;
	display: inline-block;
	border-top: 14px solid transparent;
	border-left: 14px solid #fff;
	border-right: 0 solid #fff;
	border-bottom: 14px solid transparent;
	content: " ";
}

.timeline>li>.timeline-badge {
	color: #fff;
	width: 50px;
	height: 50px;
	line-height: 50px;
	font-size: 1.4em;
	text-align: center;
	position: absolute;
	top: 16px;
	left: 50%;
	margin-left: -25px;
	background-color: #999999;
	z-index: 100;
	border-top-right-radius: 50%;
	border-top-left-radius: 50%;
	border-bottom-right-radius: 50%;
	border-bottom-left-radius: 50%;
}

.timeline>li.timeline-inverted>.timeline-panel {
	float: right;
}

.timeline>li.timeline-inverted>.timeline-panel:before {
	border-left-width: 0;
	border-right-width: 15px;
	left: -15px;
	right: auto;
}

.timeline>li.timeline-inverted>.timeline-panel:after {
	border-left-width: 0;
	border-right-width: 14px;
	left: -14px;
	right: auto;
}

.timeline-badge.primary {
	background-color: #2e6da4 !important;
}

.timeline-badge.success {
	background-color: #3f903f !important;
}

.timeline-badge.warning {
	background-color: #f0ad4e !important;
}

.timeline-badge.danger {
	background-color: #d9534f !important;
}

.timeline-badge.info {
	background-color: #5bc0de !important;
}

.timeline-title {
	margin-top: 0;
	color: inherit;
}

.timeline-body>p, .timeline-body>ul {
	margin-bottom: 0;
}

.timeline-body>p+p {
	margin-top: 5px;
}


</style>
 
</head>
<body>
<div class="container">
  <h2>Question List</h2>
  <button class="btn btn-primary" id="quest_getAll">查詢全部</button>
  <button class="btn btn-info" id="quest_cancel">取消查詢</button>
  <p></p>
  <div id="table1">
  <table class="table table-striped" id="replyAll">
    <thead>
      <tr>
        <th>quest_id</th>
        <th>quest_catalog</th>
        <th>quest_topic</th>
        <th>quest_qtime</th>
        <th>edit</th>
      </tr>
    </thead>
    <tbody></tbody>
  </table>
<!--     <div id="demo1">    -->
<!--     </div> -->
  </div>
</div>
<!-- <div class="container" id="replyOne"> -->
<!-- 		<ul class="timeline"> -->
<!-- 			<li> -->
<!-- 				<div class="timeline-badge"> -->
<!-- 					<i class="glyphicon glyphicon-check"></i> -->
<!-- 				</div> -->
<!-- 				<div class="timeline-panel"> -->
<!-- 					<div class="timeline-heading"> -->
<!-- 						<h4 class="timeline-title">會員提問</h4> -->
<!-- 						<p> -->
<!-- 							<small class="text-muted"> -->
<!-- 							   <i class="glyphicon glyphicon-time"></i> 11 hours ago via Twitter -->
<!-- 							</small> -->
<!-- 						</p> -->
<!-- 					</div> -->
<!-- 					<div class="timeline-body"> -->
<!-- 						<p>Mussum ipsum cacilds, vidis litro abertis. Consetis -->
<!-- 							adipiscings elitis. Pra lá , depois divoltis porris, paradis. -->
<!-- 							Paisis, filhis, espiritis santis. Mé faiz elementum girarzis, -->
<!-- 							nisi eros vermeio, in elementis mé pra quem é amistosis quis leo. -->
<!-- 							Manduma pindureta quium dia nois paga. Sapien in monti palavris -->
<!-- 							qui num significa nadis i pareci latim. Interessantiss quisso -->
<!-- 							pudia ce receita de bolis, mais bolis eu num gostis.</p> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</li> -->
<!-- 			<li class="timeline-inverted"> -->
<!-- 				<div class="timeline-badge warning"> -->
<!-- 					<i class="glyphicon glyphicon-credit-card"></i> -->
<!-- 				</div> -->
<!-- 				<div class="timeline-panel"> -->
<!-- 					<div class="timeline-heading"> -->
<!-- 						<h4 class="timeline-title">管理員回覆</h4> -->
<!-- 						<p> -->
<!-- 							<small class="text-muted"><i -->
<!-- 								class="glyphicon glyphicon-time"></i> 11 hours ago via Twitter</small> -->
<!-- 						</p> -->
<!-- 					</div> -->
<!-- 					<div class="timeline-body"> -->
<!-- 						<p>Mussum ipsum cacilds, vidis litro abertis. Consetis -->
<!-- 							adipiscings elitis. Pra lá , depois divoltis porris, paradis. -->
<!-- 							Paisis, filhis, espiritis santis. Mé faiz elementum girarzis, -->
<!-- 							nisi eros vermeio, in elementis mé pra quem é amistosis quis leo. -->
<!-- 							Manduma pindureta quium dia nois paga. Sapien in monti palavris -->
<!-- 							qui num significa nadis i pareci latim. Interessantiss quisso -->
<!-- 							pudia ce receita de bolis, mais bolis eu num gostis.</p> -->
<!-- 						<p>Suco de cevadiss, é um leite divinis, qui tem lupuliz, -->
<!-- 							matis, aguis e fermentis. Interagi no mé, cursus quis, vehicula -->
<!-- 							ac nisi. Aenean vel dui dui. Nullam leo erat, aliquet quis tempus -->
<!-- 							a, posuere ut mi. Ut scelerisque neque et turpis posuere pulvinar -->
<!-- 							pellentesque nibh ullamcorper. Pharetra in mattis molestie, -->
<!-- 							volutpat elementum justo. Aenean ut ante turpis. Pellentesque -->
<!-- 							laoreet mé vel lectus scelerisque interdum cursus velit auctor. -->
<!-- 							Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ac -->
<!-- 							mauris lectus, non scelerisque augue. Aenean justo massa.</p> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</li> -->
			
<!-- 		</ul> -->
<!-- 	</div> -->
<script src="../js/jquery-3.1.0.min.js"></script>  
	<script type="text/javascript">
	$(function(){
        $('#replyOne').hide();
        $('#replyAll').hide();
	    $("#quest_getAll").click(function() {
		        $('#replyAll').show();
				$('#replyAll>tbody').empty();
				var memUid=1;
		    $.getJSON(("QuestServlet"),{"memUid":memUid,"method":"getAllByUid"},function(data){
			var myBody = $('#replyAll>tbody');
			$.each(data,function(idx,data){
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
			})
			
			$("tr").not(':first').hover(
				    function () {
						$(this).css("background","#FFD9EC");},
					function () {
						$(this).css("background","");});
		})
		

//        $("#replyAll").on('click','.btn-success',function(){
// //             $('#replyOne').show();
//             quest_replyValue=null;
//             $('#replyOne').remove();
// 		    var rowClosest = $(this).closest("tr");              
// 		    var questId = rowClosest.find("td:eq(0)").text();     // Finds the 1st <td> element
// 		    console.log(questId);
// 		    $.get(("QuestServlet"),{"questId":questId,"method":"getOneById"},function(data){
// 			     $.each(data,function(questName,questValue){
// // 		   	   	    console.log(questName+" : "+questValue);
// 				    if(questName=="quest_reply"){
//                         quest_replyValue=questValue;
// 				    	console.log(quest_replyValue);
// 				    }
// 				    if(questName=="quest_quiz"){
// 				    	quest_quizValue=questValue;
// 				    	console.log(quest_quizValue);		
// 				    }
// 				    if(questName=="quest_qtime"){
// 				    	quest_qtimeValue=questValue;
// 				    	console.log(quest_qtimeValue);		
// 				    }
// 				    if(questName=="quest_rtime"){
// 				    	quest_rtimeValue=questValue;
// 				    	console.log(quest_rtimeValue);		
// 				    }

// 			     })
// 			        if(typeof quest_replyValue === "undefined" ||quest_replyValue == null){
// 			        var frag = document.createDocumentFragment();
// 		  			var div_head = document.createElement("div");
// // 		  			div_head.className="container";
// 		  			div_head.setAttribute("id","replyOne");
		  			
// 		  			var ul_afterHead= document.createElement("ul");
// 		  			ul_afterHead.className="timeline";
// 		  			div_head.appendChild(ul_afterHead);
		  			
		  			
// 		  			var li_afterUl=document.createElement("li");
// 		  			ul_afterHead.appendChild(li_afterUl);
		  			
		  			
// 		  			var div_afterLi1 = document.createElement("div");
// 		  			div_afterLi1.className="timeline-badge";
// 		  			li_afterUl.appendChild(div_afterLi1);
		  			
// 		  			var i_afterDiv = document.createElement("i");
// 		  			i_afterDiv.className="glyphicon glyphicon-check";
// 		  			div_afterLi1.appendChild(i_afterDiv);
		  			
// 		  			var div_afterLi2 = document.createElement("div");
// 		  			div_afterLi2.className="timeline-panel";
// 		  			li_afterUl.appendChild(div_afterLi2);
		  			
// 		  			var div_afterDiv2 = document.createElement("div");
// 		  			div_afterDiv2.className="timeline-heading";
// 		  			div_afterLi2.appendChild(div_afterDiv2);
		  			
// 		  			var h4_afterDiv2_1 = document.createElement("h4");
// 		  			h4_afterDiv2_1.className="timeline-title";
// 		  			var txt_h4=document.createTextNode("會員提問");
// 		  			h4_afterDiv2_1.appendChild(txt_h4);
// 		  			div_afterDiv2.appendChild(h4_afterDiv2_1);
		  			
// 		  			var p_afterDiv2_1 = document.createElement("p");
// 		  			div_afterDiv2.appendChild(p_afterDiv2_1);
		  			
// 		  			var small_afterP = document.createElement("small");
// 		  			small_afterP.className="text-muted";
		  			
// 		  			var i_afterP = document.createElement("i");
// 		  			i_afterP.className="glyphicon glyphicon-time";
// 		  			small_afterP.appendChild(i_afterP);
// 		  			small_afterP.setAttribute("style","word-break: break-all");
// 		  			var txt_small=document.createTextNode(quest_qtimeValue);  //須拿提問時間
// 		  			small_afterP.appendChild(txt_small);
// 		  			p_afterDiv2_1.appendChild(small_afterP);
					
// 		  			var div2_afterDiv2 = document.createElement("div");
// 		  			div2_afterDiv2.className="timeline-body";
// 		  			div_afterLi2.appendChild(div2_afterDiv2);
		  			
// 		  			var p_afterDiv2_2 = document.createElement("p");
// 		  			p_afterDiv2_2.setAttribute("style","word-break: break-all");
// 		  			var txt_p=document.createTextNode(quest_quizValue);       //須拿提問內容
// 		  			p_afterDiv2_2.appendChild(txt_p);
// 		  			div2_afterDiv2.appendChild(p_afterDiv2_2);
		  			
// 		  			frag.appendChild(div_head);
		  			
// 		  			rowClosest.after(frag);
// 			        }else{
// 			        	// Question div
// 	                    var frag = document.createDocumentFragment();
// 			  			var div_head = document.createElement("div");
// // 			  			div_head.className="container";
// 			  			div_head.setAttribute("id","replyOne");
			  			
// 			  			var ul_afterHead= document.createElement("ul");
// 			  			ul_afterHead.className="timeline";
// 			  			div_head.appendChild(ul_afterHead);
			  			
			  			
// 			  			var li_afterUl=document.createElement("li");
// 			  			ul_afterHead.appendChild(li_afterUl);
			  			
			  			
// 			  			var div_afterLi1 = document.createElement("div");
// 			  			div_afterLi1.className="timeline-badge";
// 			  			li_afterUl.appendChild(div_afterLi1);
			  			
// 			  			var i_afterDiv = document.createElement("i");
// 			  			i_afterDiv.className="glyphicon glyphicon-check";
// 			  			div_afterLi1.appendChild(i_afterDiv);
			  			
// 			  			var div_afterLi2 = document.createElement("div");
// 			  			div_afterLi2.className="timeline-panel";
// 			  			li_afterUl.appendChild(div_afterLi2);
			  			
// 			  			var div_afterDiv2 = document.createElement("div");
// 			  			div_afterDiv2.className="timeline-heading";
// 			  			div_afterLi2.appendChild(div_afterDiv2);
			  			
// 			  			var h4_afterDiv2_1 = document.createElement("h4");
// 			  			h4_afterDiv2_1.className="timeline-title";
// 			  			var txt_h4=document.createTextNode("會員提問");
// 			  			h4_afterDiv2_1.appendChild(txt_h4);
// 			  			div_afterDiv2.appendChild(h4_afterDiv2_1);
			  			
// 			  			var p_afterDiv2_1 = document.createElement("p");
// 			  			div_afterDiv2.appendChild(p_afterDiv2_1);
			  			
// 			  			var small_afterP = document.createElement("small");
// 			  			small_afterP.className="text-muted";
			  			
// 			  			var i_afterP = document.createElement("i");
// 			  			i_afterP.className="glyphicon glyphicon-time";
// 			  			small_afterP.appendChild(i_afterP);
// 			  			small_afterP.setAttribute("style","word-break: break-all");
// 			  			var txt_small=document.createTextNode(quest_qtimeValue);  //須拿提問時間
// 			  			small_afterP.appendChild(txt_small);
// 			  			p_afterDiv2_1.appendChild(small_afterP);
						
// 			  			var div2_afterDiv2 = document.createElement("div");
// 			  			div2_afterDiv2.className="timeline-body";
// 			  			div_afterLi2.appendChild(div2_afterDiv2);
			  			
// 			  			var p_afterDiv2_2 = document.createElement("p");
// 			  			p_afterDiv2_2.setAttribute("style","word-break: break-all");
// 			  			var txt_p=document.createTextNode(quest_quizValue);       //須拿提問內容
// 			  			p_afterDiv2_2.appendChild(txt_p);
// 			  			div2_afterDiv2.appendChild(p_afterDiv2_2);
			  			
// 			  			// Reply div
// 			  			var li2_afterUl=document.createElement("li");
// 			  			li2_afterUl.className="timeline-inverted";
// 			  			ul_afterHead.appendChild(li2_afterUl);
			  			
// 			  			var div1_afterLi2 = document.createElement("div");
// 			  			div1_afterLi2.className="timeline-badge warning";
// 			  			li2_afterUl.appendChild(div1_afterLi2);
			  			
// 			  			var i2_afterDiv = document.createElement("i");
// 			  			i2_afterDiv.className="glyphicon glyphicon-credit-card";
// 			  			div1_afterLi2.appendChild(i2_afterDiv);
			  			
// 			  			var div2_afterLi2 = document.createElement("div");
// 			  			div2_afterLi2.className="timeline-panel";
// 			  			li2_afterUl.appendChild(div2_afterLi2);
			  			
// 			  			var div_afterLi2Div2 = document.createElement("div");
// 			  			div_afterLi2Div2.className="timeline-heading";
// 			  			div2_afterLi2.appendChild(div_afterLi2Div2);
			  			
// 			  			var h42_afterDiv2_1 = document.createElement("h4");
// 			  			h42_afterDiv2_1.className="timeline-title";
// 			  			var txt_h42=document.createTextNode("管理員回覆");
// 			  			h42_afterDiv2_1.appendChild(txt_h42);
// 			  			div_afterLi2Div2.appendChild(h42_afterDiv2_1);
			  			
// 			  			var p2_afterDiv2_1 = document.createElement("p");
// 	                    div_afterLi2Div2.appendChild(p2_afterDiv2_1);
			  			
// 			  			var small2_afterP = document.createElement("small");
// 			  			small2_afterP.className="text-muted";
			  			
// 			  			var i2_afterP = document.createElement("i");
// 			  			i2_afterP.className="glyphicon glyphicon-time";
// 			  			small2_afterP.appendChild(i_afterP);
// 			  			small2_afterP.setAttribute("style","word-break: break-all");
// 			  			var txt2_small=document.createTextNode(quest_rtimeValue);  //須拿回覆時間
// 			  			small2_afterP.appendChild(txt2_small);
// 			  			p2_afterDiv2_1.appendChild(small2_afterP);
			  			
// 			  			var div2_afterLi2Div2 = document.createElement("div");
// 			  			div2_afterLi2Div2.className="timeline-body";
// 			  			div2_afterLi2.appendChild(div2_afterLi2Div2);
			  			
// 			  			var p2_afterDiv2_2 = document.createElement("p");
// 			  			p2_afterDiv2_2.setAttribute("style","word-break: break-all");
// 			  			var txt2_p=document.createTextNode(quest_replyValue);       //須拿回覆內容
// 			  			p2_afterDiv2_2.appendChild(txt2_p);
// 			  			div2_afterLi2Div2.appendChild(p2_afterDiv2_2);
			  			
// 			  			frag.appendChild(div_head);
			  			
// 			  			rowClosest.after(frag);
			  			
// 			        }
// 		    })
		    
  			
//           $(".btn-warning").click(function() {
// 		     $('#replyOne').remove();
// 	      })
//        })
    
	
// 	   $("#quest_cancel").click(function() {
// 		   $('#replyAll').hide();
// 	   })
	
// // 	   //分页
// // 	   $("#demo1").paginate({
// // 	       count: 10,
// // 	       start: 1,
// // 	       display: 10,
// // 	       border: true,
// // 	       onChange: function(page) {
// // 	    	   location.href = "list.aspx?id=" + page;
// // 	       }
// 	   });
	
	})
	</script>

</body>
</html>


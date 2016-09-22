<%@ page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>TripItem Time Testing</title>
  <style>
  #sortable { list-style-type: none; margin: 0; padding: 0; width: 60%; }
  .divT
  {
  border:solid 1px; 
  padding:5px; 
  vertical-align: middle;
  text-align: left;
  }
  </style>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>


<body>
<!-- name屬性具有可重複性，作未來selector與變數設定的使用 -->
	<div id="start">
		開始時間：<input name="sTime" type="datetime-local" value="2016-09-19T07:00:00"/>
		交通時間：<input name="tTime" type="number" value="30"/>
		<input name="wTime" type="number" value="0" style="display: none"/>
	</div>
	 
	<div class="stbDiv" id="sortable">
	<!-- 	INSERT trip items HERE -->
	</div>
	 
	<input type="button" id="addDiv" value="新增行程">
  
 <script>

// ↓ 依"啟程"的出發時間為基準，初始化基準值  ↓
  var sTime,tTime,wTime,eTime;
  function timeInit()
  {
	  //console.log("initial start...");
	  sTime = new Date($("#start input[name='sTime']").val().replace('T'," ")).getTime();
	  tTime = $("#start input[name='tTime']").val()*60*1000;
	  wTime = $("#start input[name='wTime']").val()*60*1000;
	  eTime = sTime + tTime + wTime;
	  //console.log("initial done.");
  }
// ↑ 依"啟程"的出發時間為基準，初始化基準值   ↑

  
// ----------------------------------------------------------------------------------------------
  
  
// ↓ 模擬創造TripItem DIV的方法  ↓
  function createTripItem(count)
  {
	  	var h3 = $("<h3></h3>").append("<strong>Trip" + count + "</strong>");
		var ipT = $("<input></input>").attr("name","tTime").attr("type","number").val(60);
		var lT = $("<label></label>").text("交通時間：").append(ipT);
		var pS = $("<p></p>").attr("name","sTime").text("起：");
		var pE = $("<p></p>").attr("name","eTime").text("訖：");
		var ipW = $("<input></input>").attr("name","wTime").attr("type","number").val(30);
		var lW = $("<label></label>").text("逗留時間：").append(ipW);
		var div = $("<div/>").addClass("divT").append([h3,lT,pS,pE,lW]);

	//新增__input(目前非hidden)，以便取item內值送至servlet --------------------------------------------
		var startTime = $("<input></input>").attr("type","number").attr("name","tripitem_begin");
		var wonderTime = $("<input></input>").attr("type","number").attr("name","tripitem_staytime");
		var endTime = $("<input></input>").attr("type","number").attr("name","tripitem_end");
		
		var begin = $("<label></label>").text("begin：").append(startTime);
		var staytime = $("<label></label>").text("staytime：").append(wonderTime);
		var endtime = $("<label></label>").text("end：").append(endTime);

		var dataBox = $("<div></div>").append([begin,staytime,endtime]);
		div.append(dataBox);
	//-------------------------------------------------------------------------------啟程初始值請另取值
		
	
		$("#sortable").append(div);
  }
// ↑ 模擬創造TripItem DIV的方法  ↑



// ----------------------------------------------------------------------------------------------



// ↓ 將所有sortable元素的Time帶入值，並準備轉交給下一個item  ↓
  function timeReveal()
  {
	  //console.log("reveal start...");	  
	  $.each($("#sortable").children(),function(idx,div){
		  sTime = eTime;
		  
		  //將　開始時間　帶入tripitem_begin (millisecond)
		  //console.log(new Date(sTime)); //解開註解，可看該毫秒數所指日期。
		  $(this).children('div').children('label').children('input[name=tripitem_begin]').val(sTime);
		  
		  var newSt = new Date(sTime).toTimeString();
		  var sTidx = newSt.indexOf("G");
		  newSt = "起：" + newSt.substr(0,sTidx-4);
		  
		  $(this).children('p[name=sTime]').text(newSt);
		  tTime = $(this).children('label').children('input[name=tTime]').val()*60*1000;
		  wTime = $(this).children('label').children('input[name=wTime]').val();

		  //將　逗留時間　帶入tripitem_staytime
		  $(this).children('div').children('label').children('input[name=tripitem_staytime]').val(wTime);
		  
		  wTime = wTime*60*1000;
		  var newEt = new Date(eTime+wTime).toTimeString();
		  var eTidx = newEt.indexOf("G");
		  newEt = "訖：" + newEt.substr(0,eTidx-4);
		  
		  //將　結束時間　(運算後)帶入tripitem_end (millisecond)
		  //console.log(new Date(eTime+wTime)); //解開註解，可看該毫秒數所指日期。
		  $(this).children('div').children('label').children('input[name=tripitem_end]').val(eTime+wTime);
		  
		  $(this).children('p[name=eTime]').text(newEt);
		  
		  eTime = sTime + tTime + wTime;
	  });
	  //console.log("reveal done.");	  
  }
// ↑ 將所有sortable元素的Time帶入值，並準備轉交給下一個item  ↑



// ----------------------------------------------------------------------------------------------



  $( function() {

	  //_____模擬_____新增行程
	  var count = $("#sortable").children().length;
	  $("#addDiv").off("click").on("click",function(){
		  timeInit();
		  createTripItem(++count);
		  timeReveal();
	  });

	  
	  //監聽每一個input的變化
	  $(document).on("change","input[name$='Time']",function(){
		  console.log("input changed");
		  timeInit();
		  timeReveal();
	  });
	  
	  
	  //監聽排序變化，更新資訊
	$( "#sortable" ).sortable({
		update: function(event,ui){
		 timeInit();
		 timeReveal();
		}
	}).disableSelection();
	  
  });
  
  </script>
</body>
</html>